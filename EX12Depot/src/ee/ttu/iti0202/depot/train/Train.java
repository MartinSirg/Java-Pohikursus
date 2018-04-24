package ee.ttu.iti0202.depot.train;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private Engine engine;
    private List<Car> cars;
    private int currentDanger;
    private int maxDanger;

    Train(Engine engine, List<Car> cars, int currentDanger, int maxDanger) {
        this.engine = engine;
        this.cars = cars;
        this.currentDanger = currentDanger;
        this.maxDanger = maxDanger;
    }

    public Engine getEngine() {
        return engine;
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getCurrentDanger() {
        return currentDanger;
    }

    public int getMaxDanger() {
        return maxDanger;
    }
}
