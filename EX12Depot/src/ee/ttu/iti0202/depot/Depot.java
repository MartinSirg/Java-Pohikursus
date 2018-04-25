package ee.ttu.iti0202.depot;

import ee.ttu.iti0202.depot.cargo.Cargo;
import ee.ttu.iti0202.depot.train.Car;
import ee.ttu.iti0202.depot.train.Engine;
import ee.ttu.iti0202.depot.train.Train;
import ee.ttu.iti0202.depot.train.TrainBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Depot {
    private List<Engine> engines = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    private List<Train> trains = new ArrayList<>();

    public Optional<Train> makeTrain(Cargo.Type... cargoItems) throws TrainException {
        List<Cargo.Type> items = List.of(cargoItems);

        // check if there is conflicting cargo -------------------------------------------------------------------------
        if (items.size() > 0) {
            if (items.contains(Cargo.Type.HUMANS)) {
                if (items.contains(Cargo.Type.FUEL) || items.contains(Cargo.Type.FIRE)) {
                    throw new TrainException(TrainException.ExType.COLLIDING_CARGO);
                }
            }
            if (items.contains(Cargo.Type.WOOD)) {
                if (items.contains(Cargo.Type.WATER) || items.contains(Cargo.Type.FIRE)) {
                    throw new TrainException(TrainException.ExType.COLLIDING_CARGO);
                }
            }
            if (items.contains(Cargo.Type.FUEL)) {
                if (items.contains(Cargo.Type.FIRE) || items.contains(Cargo.Type.WATER)) {
                    throw new TrainException(TrainException.ExType.COLLIDING_CARGO);
                }
            }
            if (items.contains(Cargo.Type.FIRE) && items.contains(Cargo.Type.WATER)) {
                throw new TrainException(TrainException.ExType.COLLIDING_CARGO);
            }
        }

        // check if there is engine forbidden cargo---------------------------------------------------------------------
        Engine engineToBeUsed = getEngine();
        if (engineToBeUsed.getForbiddenCargo().size() != 0) {
            for (Cargo.Type forbiddenType: engineToBeUsed.getForbiddenCargo()) {
                if (items.contains(forbiddenType)) throw new TrainException(TrainException.ExType.COLLIDING_CARGO);
            }
        }

        //add engine ---------------------------------------------------------------------------------------------------


        TrainBuilder builder = new TrainBuilder();

        builder.setEngine(useEngine(engineToBeUsed));

        //add cargo ----------------------------------------------------------------------------------------------------

        for (Cargo.Type item: items) {
            Optional<Car> car = getCar();
            if (!car.isPresent()) break; // break if there are no cars left

            Cargo cargoObject = Cargo.of(item);
            if (builder.canAddCar(cargoObject)) { //if danger level allows adding car
                Car carWithCargo = car.get().setCargo(cargoObject);
                builder.addCar(useCar(carWithCargo)); // add car to train, remove from cars
            }

        }
        Optional<Train> result =  builder.createTrain();
        result.ifPresent(train -> trains.add(train));
        return result;
    }

    public Engine getEngine() throws TrainException {
        if (engines.size() == 0)  {
            throw new TrainException(TrainException.ExType.NO_ENGINE);
        }
        Engine selected = engines.get(0);
        for (Engine engine: engines) {
            if (engine.getMaxDanger() > selected.getMaxDanger()) selected = engine;
        }
        return selected;
    }


    public Optional<Car> getCar() {
        if (cars.size() == 0) return Optional.empty();
        return Optional.of(cars.get(0));
    }

    public Engine useEngine(Engine engine) {
        engines.remove(engine);
        return engine;
    }

    public Car useCar(Car car) {
        cars.remove(car);
        return car;
    }

    public Depot addEngine(Engine engine) {
        engines.add(engine);
        return this;
    }

    public Depot addCar(Car car) {
        cars.add(car);
        return this;
    }

    public List<Engine> getEngines() {
        return engines;
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public static void main(String[] args) throws TrainException {

        TrainBuilder builder = new TrainBuilder();
        builder.addCar(new Car(Cargo.of(Cargo.Type.FIRE)));
//
//        //lisatakse depoosse vedureid ja vaguneid
//        Depot depot = new Depot();
//        depot.addCar(new Car()).addCar(new Car());
//        depot.addEngine(new Engine(10)).addEngine(new Engine(20));
//        System.out.println(depot.getCars().size()); // 2
//        System.out.println(depot.getEngines().size()); // 2
//        System.out.println(depot.getEngines().get(1).getMaxDanger()); // 20
//
//        //proovitakse teha rongi, kus riskitase ületab veduri lubatud taseme
//        depot.makeTrain(Cargo.Type.FIRE, Cargo.Type.FIRE);
//        System.out.println(depot.getEngines().size()); // 1
//        System.out.println(depot.getCars().size()); // 1
//        System.out.println(depot.getTrains().size()); // 1
//        System.out.println(depot.getTrains().get(0).getCars().size()); // 1
//
//        depot.getEngines().clear();
//        depot.getTrains().clear();
//
//        //proovitakse teha rongi nii, et vedurit pole
//        depot.makeTrain(Cargo.Type.FIRE);
//        System.out.println(depot.getTrains().size()); // 0
//        System.out.println(depot.getCars().size()); // 1
//
//        depot.getCars().clear();
//        //proovitakse teha rongi nii, et vaguneid pole
//
//        depot.addEngine(new Engine(100));
//        depot.makeTrain(Cargo.Type.WATER);
//        System.out.println(depot.getTrains().size()); // 1
//        System.out.println(depot.getTrains().get(0).getCars().size()); //0
//        //proovitakse teha rongi, kus osa kaupu ei sobi omavahel.
//        depot.getTrains().clear();
//        depot.addEngine(new Engine(100));
//        depot.addCar(new Car()).addCar(new Car());
//        depot.makeTrain(Cargo.Type.FUEL, Cargo.Type.HUMANS);
//        System.out.println(depot.getTrains().size()); //0
//        System.out.println(depot.getCars().size()); // 2
//        System.out.println(depot.getEngines().size()); // 1
    }
}
