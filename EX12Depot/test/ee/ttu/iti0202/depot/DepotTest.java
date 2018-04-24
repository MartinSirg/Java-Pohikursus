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
    void makeTrain() {
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
            fail("SHouldnt throw: " + e.getMessage());
        }

        //proovitakse teha rongi nii, et vedurit pole
        try {
            depot.makeTrain(Cargo.Type.HUMANS);
            fail("Shouln't make train because no engines left.");
        } catch (TrainException e) {
            assertEquals("No engine found in depot.", e.getMessage());
        }

        //proovitakse teha rongi nii, et vaguneid pole
        depot.getCars().clear();
        depot.addEngine(new Engine(100));
        try {
            depot.makeTrain(Cargo.Type.FIRE, Cargo.Type.FIRE, Cargo.Type.FIRE);
            assertEquals(2, depot.getTrains().size());
            assertEquals(0, depot.getTrains().get(1).getCars().size());
        } catch (TrainException e) {
            fail("Got: " + e.getMessage());
        }

        //proovitakse teha rongi, kus osa kaupu ei sobi omavahel.
        depot.getTrains().clear();
        depot.getCars().clear();
        depot.getEngines().clear();

        depot.addEngine(new Engine(100));
        depot.addCar(new Car()).addCar(new Car());
        try {
            depot.makeTrain(Cargo.Type.FUEL, Cargo.Type.HUMANS);
            fail("Should throw exception.");
        } catch (TrainException e) {
            assertEquals("Incompatible cargo input. No train was created.", e.getMessage());
        }

        depot.getEngines().clear();
        depot.addEngine(new Engine(100, Cargo.Type.WOOD));
        try {
            System.out.println(depot.getEngine().getForbiddenCargo());
            depot.makeTrain(Cargo.Type.WOOD, Cargo.Type.HUMANS);
            fail("Should throw exception.");
        } catch (TrainException e) {
            assertEquals("Incompatible cargo input. No train was created.", e.getMessage());
        }

        //normal case
        depot.getTrains().clear();
        depot.getCars().clear();
        depot.getEngines().clear();

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
    void getEngine() {
        try {
            depot.getEngine();
            fail("Should throw Exception");
        } catch (TrainException e) {
            assertEquals("No engine found in depot.", e.getMessage());
        }
        depot.addEngine(new Engine(10)).addEngine(new Engine(100)).addEngine(new Engine(99));
        try {
            assertEquals(100, depot.getEngine().getMaxDanger());
        } catch (TrainException e) {
            fail("Shouldn't throw exception");
        }
    }

    @Test
    void getCar() {
        if (depot.getCar().isPresent()) {
            fail("Shouldn't find a car, since list is empty.");
        }
        depot.addCar(new Car());
        if (!depot.getCar().isPresent()) {
            fail("Didn't find car");
        }
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