package ee.ttu.iti0202.depot.train;

import ee.ttu.iti0202.depot.TrainException;
import ee.ttu.iti0202.depot.cargo.Cargo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainBuilder {
    private Engine engine;
    private List<Car> cars = new ArrayList<>();
    private int currentDanger = 0;
    private int maxDanger;

    public void setEngine(Engine engine) {
        this.engine = engine;
        this.maxDanger = engine.getMaxDanger();
    }

    public void addCar(Car car) throws TrainException{
        if (car.getCargo().getDangerLevel() + currentDanger > maxDanger) {
            throw new TrainException(TrainException.ExType.DANGER_OVERLOAD);
        } else {
            currentDanger += car.getCargo().getDangerLevel();
            cars.add(car);
        }
    }

    public Optional<Train> createTrain() {
        if (engine == null || currentDanger > maxDanger) return Optional.empty();
        return Optional.of(new Train(engine, cars, currentDanger, maxDanger));
    }

    public boolean canAddCar(Cargo cargo) {
        return cargo.getDangerLevel() + currentDanger <= maxDanger;
    }

    public Engine getEngine() {
        return engine;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public int getCurrentDanger() {
        return currentDanger;
    }

    public void setCurrentDanger(int currentDanger) {
        this.currentDanger = currentDanger;
    }

    public int getMaxDanger() {
        return maxDanger;
    }

    public void setMaxDanger(int maxDanger) {
        this.maxDanger = maxDanger;
    }
}