package ee.ttu.iti0202.depot;

import ee.ttu.iti0202.depot.cargo.Cargo;
import ee.ttu.iti0202.depot.train.Car;
import ee.ttu.iti0202.depot.train.Engine;
import ee.ttu.iti0202.depot.train.Train;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepotTest {
    Depot depot = new Depot();

    @Test
    void makeTrainWithTooDangerousCargoSum() {
        //proovitakse teha rongi, kus riskitase ületab veduri lubatud taseme
        depot.addCar(new Car()).addCar(new Car()).addCar(new Car());
        depot.addEngine(new Engine(10));
        try {
            depot.makeTrain(Cargo.Type.FIRE, Cargo.Type.FIRE);
            assertEquals(1, depot.getTrains().size());
            assertEquals(0, depot.getEngines().size());
            assertEquals(2, depot.getCars().size());
            assertEquals(1, depot.getTrains().get(0).getCars().size());
            assertEquals(7, depot.getTrains().get(0).getCurrentDanger());
        } catch (TrainException e) {
            fail("Shouldnt throw: " + e.getMessage());
        }
    }

    @Test
    void makeTrainNormalCase() {
        //normal case

        depot.addEngine(new Engine(100));
        for(int i = 0; i < 20; i++) {
            depot.addCar(new Car());
        }
        try {
            depot.makeTrain(Cargo.Type.HUMANS, Cargo.Type.HUMANS, Cargo.Type.HUMANS, Cargo.Type.WATER, Cargo.Type.WATER);
            assertEquals(1, depot.getTrains().size());
            assertEquals(5, depot.getTrains().get(0).getCars().size());
        } catch (TrainException e) {
            fail("Shouldn't throw Exception");
        }
    }

    @Test
    void makeTrainIncompatibleEngineAndCargo() {
        depot.getEngines().clear();
        depot.addEngine(new Engine(100, Cargo.Type.WOOD));
        assertThrows(TrainException.class, () -> depot.makeTrain(Cargo.Type.WOOD, Cargo.Type.HUMANS));
    }

    @Test
    void makeTrainWithNoEngine() {
        //proovitakse teha rongi nii, et vedurit pole
        assertThrows(TrainException.class,() -> depot.makeTrain(Cargo.Type.HUMANS));

    }

    @Test
    void makeTrainNoCars() {
        //proovitakse teha rongi nii, et vaguneid pole
        depot.addEngine(new Engine(100));
        try {
            depot.makeTrain(Cargo.Type.FIRE, Cargo.Type.FIRE, Cargo.Type.FIRE);
            assertEquals(1, depot.getTrains().size());
            assertEquals(0, depot.getTrains().get(0).getCars().size());
        } catch (TrainException e) {
            fail("Got: " + e.getMessage());
        }
    }

    @Test
    void makeTrainIncompatibleCargo() {
        //proovitakse teha rongi, kus osa kaup ei sobi omavahel.

        depot.addEngine(new Engine(100));
        depot.addCar(new Car()).addCar(new Car());
        assertThrows(TrainException.class,() -> depot.makeTrain(Cargo.Type.FUEL, Cargo.Type.HUMANS));
    }


    @Test
    void getEngineNormal() {
        depot.addEngine(new Engine(10)).addEngine(new Engine(100)).addEngine(new Engine(99));
        try {
            assertEquals(100, depot.getEngine().getMaxDanger());
        } catch (TrainException e) {
            fail("Shouldn't throw exception");
        }
    }
    @Test
    void getEngineNoEngine() {
        depot.getEngines().clear();
        assertThrows(TrainException.class, () -> depot.getEngine());
    }

    @Test
    void getCar() {
        depot.addCar(new Car());
        if (!depot.getCar().isPresent()) {
            fail("Didn't find car");
        }
    }

    @Test
    void getCarNoCars() {
        depot.getCars().clear();
        if (depot.getCar().isPresent()) fail("Shouldn't find car");
    }

    @Test
    void useEngine() {
        Engine engine = new Engine(100);
        depot.addEngine(engine);
        assertEquals(1, depot.getEngines().size());
        depot.useEngine(engine);
        assertEquals(0, depot.getEngines().size());
    }

    @Test
    void useCar() {
        Car car = new Car();
        depot.addCar(car);
        assertEquals(1, depot.getCars().size());
        depot.useCar(car);
        assertEquals(0, depot.getCars().size());
    }

    @Test
    void addEngine() {
        depot.addEngine(new Engine(100));
        assertEquals(1, depot.getEngines().size());
    }

    @Test
    void addCar() {
        depot.addCar(new Car());
        assertEquals(1, depot.getCars().size());
    }
}