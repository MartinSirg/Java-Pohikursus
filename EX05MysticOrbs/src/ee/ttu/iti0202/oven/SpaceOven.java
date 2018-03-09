package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.orb.SpaceOrb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven {
    private static final int LIMIT = 25;
    private static final int FRAG_AMOUNT = 15;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public boolean isBroken() {
        return createdOrbsAmount >= LIMIT;
    }

    @Override
    public Optional<Orb> craftOrb() {
        boolean enoughMeteoriteStone = resourceStorage.hasEnoughResource("meteorite stone", 1);
        boolean enoughStarFragment = resourceStorage.hasEnoughResource("star fragment", FRAG_AMOUNT);
        boolean enoughPearl = resourceStorage.hasEnoughResource("pearl", 1);
        boolean enoughSilver = resourceStorage.hasEnoughResource("silver", 1);
        Orb newOrb;

        if (!isBroken() && enoughMeteoriteStone && enoughStarFragment) { // not broken and has enough res. to make S.Orb
            newOrb = new SpaceOrb(name);
            resourceStorage.takeResource("meteorite stone", 1);
            resourceStorage.takeResource("star fragment", FRAG_AMOUNT);
            createdOrbsAmount += 1;
            return Optional.of(newOrb);
        } else if (enoughPearl && enoughSilver) { // enough res. to make regular orb
            newOrb = new Orb(name);
            resourceStorage.takeResource("pearl", 1);
            resourceStorage.takeResource("silver", 1);
            newOrb.charge("silver", 1);
            newOrb.charge("pearl", 1);
            createdOrbsAmount += 1;
            return Optional.of(newOrb);
        } else {
            return Optional.empty();
        }
    }
}
