package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.exceptions.CannotFixException;
import ee.ttu.iti0202.orb.MagicOrb;
import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class MagicOven extends Oven implements Fixable {
    private int timesFixed = 0;
    private static final int CLAY_AMOUNT = 25, POWDER_AMOUNT = 100;

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
    }

    @Override
    public int getRank() {
        return 2;
    }

    @Override
    public boolean isBroken() {
        return createdOrbsAmount >= 5 * (timesFixed + 1);
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


    @Override
    public int getTimesFixed() {
        return timesFixed;
    }

    @Override
    public void fix() throws CannotFixException {
        boolean enoughClay = resourceStorage.hasEnoughResource("clay", CLAY_AMOUNT * (timesFixed + 1));
        boolean enoughPwdr = resourceStorage.hasEnoughResource("freezing powder", POWDER_AMOUNT * (timesFixed + 1));

        if (!isBroken()) {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        } else if (timesFixed == 10) {
            // fixed too many times
            throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
        } else if (!enoughClay || !enoughPwdr) {
            // no resources
            throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
        } else {
            timesFixed += 1;
            resourceStorage.takeResource("clay", CLAY_AMOUNT * timesFixed);
            resourceStorage.takeResource("freezing powder", POWDER_AMOUNT * timesFixed);
        }
    }
}
