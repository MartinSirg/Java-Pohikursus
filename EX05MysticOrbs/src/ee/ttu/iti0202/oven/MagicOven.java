package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.MagicOrb;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven {

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return createdOrbsAmount >= 5;
    }

    @Override
    public Optional<Orb> craftOrb() {
        boolean enoughGold = resourceStorage.hasEnoughResource("gold", 1);
        boolean enoughDust = resourceStorage.hasEnoughResource("dust", 3);

        if (!isBroken() && enoughDust && enoughGold) {
            Orb newOrb;
            if (createdOrbsAmount % 2 == 0) {
                newOrb = new Orb(name);
            } else {
                newOrb = new MagicOrb(name);
            }
            resourceStorage.takeResource("gold", 1);
            resourceStorage.takeResource("dust", 3);
            newOrb.charge("gold", 1); // dusti ei charge'i, kuna selllega ei saa laadida
            createdOrbsAmount += 1;
            return Optional.of(newOrb);
        } else {
            return Optional.empty();
        }
    }
}
