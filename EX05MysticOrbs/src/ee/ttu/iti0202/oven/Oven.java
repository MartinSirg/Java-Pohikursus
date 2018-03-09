package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class Oven implements Comparable<Oven> {
    String name;
    ResourceStorage resourceStorage;
    int createdOrbsAmount;
    private static final int LIMIT = 15;


    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.resourceStorage = resourceStorage;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return resourceStorage;
    }

    public int getCreatedOrbsAmount() {
        return createdOrbsAmount;
    }

    public int getRank() {
        return 1;
    }

    public boolean isBroken() {
        return createdOrbsAmount >= LIMIT;
    }

    public Optional<Orb> craftOrb() {
        boolean enoughPearl = resourceStorage.hasEnoughResource("pearl", 1);
        boolean enoughSilver = resourceStorage.hasEnoughResource("silver", 1);

        if (!isBroken() && enoughPearl && enoughSilver) {
            Orb newOrb = new Orb(name);
            resourceStorage.takeResource("silver", 1);
            resourceStorage.takeResource("pearl", 1);
            newOrb.charge("silver", 1);
            newOrb.charge("pearl", 1);
            createdOrbsAmount += 1;
            return Optional.of(newOrb);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int compareTo(Oven o) {
        // getRank returns : spaceOven => 3, magicOven => 2, oven => 1
        // brokenness
        if (isBroken() && !o.isBroken()) { return -1; }
        if (!isBroken() && o.isBroken()) { return 1; }
        if (isBroken() == o.isBroken()) {   // both oven's broken state is the same
            if (getRank() > o.getRank()) { return 1; }
            if (getRank() < o.getRank()) { return -1; }
            if (getRank() == 2 && o.getRank() == 2) { // both are magical
                if (this.getCreatedOrbsAmount() % 2 == 1 && o.getCreatedOrbsAmount() % 2 == 0) { return 1; }
                if (this.getCreatedOrbsAmount() % 2 == 0 && o.getCreatedOrbsAmount() % 2 == 1) { return -1; }
                if (this.getCreatedOrbsAmount() == o.getCreatedOrbsAmount()) {
                    if (this instanceof InfinityMagicOven && !(o instanceof InfinityMagicOven)) { return 1; }
                    if (!(this instanceof InfinityMagicOven) && o instanceof InfinityMagicOven) { return -1; }
                }
            }
            if (this.getCreatedOrbsAmount() < o.getCreatedOrbsAmount()) { return 1; }
            if (this.getCreatedOrbsAmount() > o.getCreatedOrbsAmount()) { return -1; }
        }
        return name.compareTo(o.getName());
    }
}
