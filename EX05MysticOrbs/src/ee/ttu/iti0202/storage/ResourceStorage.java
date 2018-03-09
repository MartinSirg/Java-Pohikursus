package ee.ttu.iti0202.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {
    private Map<String, Integer> resources = new HashMap<>();

    public boolean isEmpty() {
        if (!resources.isEmpty()) {
            for (Integer value : resources.values()) {
                if (value > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addResource(String resource, int amount) {
        if (!resource.equals("") && !resources.containsKey(resource)) {
            resources.put(resource, amount);
        } else if (!resource.equals("") && resources.containsKey(resource)) {
            resources.put(resource, resources.get(resource) + amount);
        }
    }

    public int getResourceAmount(String resource) {
        return resources.getOrDefault(resource, 0);
    }

    public boolean hasEnoughResource(String resource, int amount) {
        return resources.containsKey(resource) && amount >= 1 && resources.get(resource) >= amount;
    }

    public boolean takeResource(String resource, int amount) {
        if (hasEnoughResource(resource, amount)) {
            resources.put(resource, resources.get(resource) - amount);
            return true;
        } else {
            return false;
        }
    }


}
