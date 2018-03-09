package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.orb.SpaceOrb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {
    private static final int LIMIT = 25, FRAG_AMOUNT = 15, LIQSIL_AMOUNT = 40, ESS_AMOUNT = 10;
    private int timesFixed = 0;

    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public int getRank() {
        return 3;
    }

    @Override
    public boolean isBroken() {
        return createdOrbsAmount >= LIMIT * (timesFixed + 1) && timesFixed < 5;
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

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }

    @Override
    public void fix() throws CannotFixException {
        boolean enoughLiqSil = resourceStorage.hasEnoughResource("liquid silver", LIQSIL_AMOUNT);
        boolean enoughEss = resourceStorage.hasEnoughResource("star essence", ESS_AMOUNT);

        if (timesFixed == 5) {
            // fixed maximum amount of times
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!isBroken()) {
            // is not broken
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (!enoughLiqSil && !enoughEss) {
            // not enough resources
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        } else if (enoughLiqSil && enoughEss) {
            // fix with "liquid silver"
            timesFixed += 1;
            resourceStorage.takeResource("liquid silver", LIQSIL_AMOUNT);
        } else if (!enoughLiqSil) {
            //fix with "star essence"
            timesFixed += 1;
            resourceStorage.takeResource("star essence", ESS_AMOUNT);
        }
    }
}
