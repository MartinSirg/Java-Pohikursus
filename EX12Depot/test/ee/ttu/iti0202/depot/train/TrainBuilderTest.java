package ee.ttu.iti0202.depot.train;

import ee.ttu.iti0202.depot.TrainException;
import ee.ttu.iti0202.depot.cargo.Cargo;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TrainBuilderTest {
    private TrainBuilder builder = new TrainBuilder();

    @Test
    void setEngine() {
        builder.setEngine(new Engine(100));
        assertEquals(100, builder.getMaxDanger());
    }

    @Test
    void addCarNormal() {
        builder.setEngine(new Engine(100));
        try {
            builder.addCar(new Car(Cargo.of(Cargo.Type.FIRE)));
        } catch (TrainException e) {
            fail("Got TrainException :" + e.getMessage());
        }
        assertEquals(1, builder.getCars().size());
        assertEquals(7, builder.getCurrentDanger());
    }

    @Test
    void addCarTooMuchCargo() throws TrainException {
        assertThrows(TrainException.class, () -> {
            builder.setEngine(new Engine(10));
            builder.addCar(new Car(Cargo.of(Cargo.Type.FIRE)));
            builder.addCar(new Car(Cargo.of(Cargo.Type.FIRE)));
        });
    }

    @Test
    void createTrainNoEngine() {
        assertEquals(Optional.empty(), builder.createTrain());
    }

    @Test
    void createTrainNormal() {
        builder.setEngine(new Engine(100));
        try {
            builder.addCar(new Car(Cargo.of(Cargo.Type.HUMANS)));
            builder.addCar(new Car(Cargo.of(Cargo.Type.HUMANS)));
        } catch (TrainException e) {
            fail("Shouldnt throw exception. Got: " + e.getMessage());
        }
        if (!builder.createTrain().isPresent()) {
            fail("Didn't create train.");
        }
    }

    @Test
    void canAddCar() {
        assertFalse(builder.canAddCar(Cargo.of(Cargo.Type.FIRE)));
    }
}