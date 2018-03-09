package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven{
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
        boolean enoguhDust = resourceStorage.hasEnoughResource("dust", 3);

        if (!isBroken() && enoguhDust && enoughGold) {
            if (createdOrbsAmount % 2 == 0) {
                // regular orb
            } else {
                //magic orb
            }
        } else {
            return Optional.empty();
        }
    }
}
