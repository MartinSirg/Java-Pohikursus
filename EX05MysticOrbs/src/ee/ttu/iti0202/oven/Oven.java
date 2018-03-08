package ee.ttu.iti0202.oven;

import ee.ttu.iti0202.orb.Orb;
import ee.ttu.iti0202.storage.ResourceStorage;

import java.util.Optional;

public class Oven {
    private String name;
    private ResourceStorage resourceStorage;
    private int createdOrbsAmount;
    private boolean broken = false;


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

    public boolean isBroken() {
        return broken;
    }

    public Optional<Orb> craftOrb() {
        boolean enoughPearl = resourceStorage.hasEnoughResource("pearl", 1);
        boolean enoughSilver = resourceStorage.hasEnoughResource("silver", 1);

        if (!broken && enoughPearl && enoughSilver) {
            Orb newOrb = new Orb(name);
            resourceStorage.takeResource("silver", 1);
            resourceStorage.takeResource("pearl", 1);
            newOrb.charge("silver", 1);
            newOrb.charge("pearl", 1);
            return Optional.of(newOrb);
        } else {
            return Optional.empty();
        }
    }
}
