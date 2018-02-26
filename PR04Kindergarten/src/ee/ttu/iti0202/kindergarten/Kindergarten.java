package ee.ttu.iti0202.kindergarten;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Kindergarten {
    ArrayList<String> children = new ArrayList<>();    //list where full name is in one string

    public Kindergarten() {
    }

    public void addChild(String name) {
        children.add(name);
    }

    public ArrayList getChildrenList() {
        return children;
    }

    public String returnChild(int index) {
        if (index >= children.size()) {
            return "";
        }
        String name = children.get(index);
        children.remove(index);
        return name;
    }

    public void summerBreak() {
        children.clear();
    }

    public HashMap<String, Integer> getAllFirstNameAmounts() {
        HashMap<String, Integer> firstNames = new HashMap<>();

        for (int i = 0; i < children.size(); i++) {
            String[] fullName = children.get(i).split(" ");
            String firstname = fullName[0];

            if (!firstNames.containsKey(firstname)) {
                firstNames.put(firstname, 1);
            } else {
                firstNames.put(firstname, firstNames.get(firstname) + 1);
            }
        }

        return firstNames;
    }

    public  int getChildrenWithFirstName(String name) {
        HashMap map = getAllFirstNameAmounts();
        if (map.containsKey(name)) {
            return (int) map.get(name);
        } else {
            return -1;
        }
    }

    public long getMatchingLastNameAmount() {
        Map<String, Integer> lastNames = new HashMap<>();

        for (String name : children) {
            String[] fullName = name.split(" ");
            String lastName = fullName[fullName.length - 1];

            if (!lastNames.containsKey(lastName)) {
                lastNames.put(lastName, 1);
            } else {
                lastNames.put(lastName, lastNames.get(lastName) + 1);
            }
        }

        long count = 0;
        for (Integer i : lastNames.values()) {
            if (i > 1) count = count + 1;
        }
        return count;
    }
}
