package meals;

import java.util.List;

public class Pizza {
    public enum Components { BEEF, EXTRA_CHEESE, PEPPERONI, BACON }
    private int diameter;
    private int slices;
    private String name;
    private List<Components> components;

    Pizza(int diameter, int slices, String name, List<Components> components) {
        this.diameter = diameter;
        this.slices = slices;
        this.name = name;
        this.components = components;
    }
}
