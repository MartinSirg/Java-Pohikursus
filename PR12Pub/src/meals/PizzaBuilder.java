package meals;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private static final int DEFAULT_DIAMTER = 20, DEFAULT_SLICES = 8;
    private int diameter = DEFAULT_DIAMTER;
    private int slices = DEFAULT_SLICES;
    private String name;
    private List<Pizza.Components> components = new ArrayList<>();

    public PizzaBuilder setDiameter(int diameter) {
        this.diameter = diameter;
        return this;
    }

    public PizzaBuilder setSlices(int slices) {
        this.slices = slices;
        return this;
    }

    public PizzaBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PizzaBuilder setComponents(List<Pizza.Components> components) {
        this.components = components;
        return this;
    }

    public PizzaBuilder addComponents(Pizza.Components component) {
        this.components.add(component);
        return this;
    }

    public Pizza createPizza() throws Exception {
        if (name == null) throw new Exception();
        return new Pizza(diameter, slices, name, components);
    }
}
