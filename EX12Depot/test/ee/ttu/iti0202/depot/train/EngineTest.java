package ee.ttu.iti0202.depot.train;

import ee.ttu.iti0202.depot.cargo.Cargo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {

    @Test
    void getForbiddenCargo() {
        Engine engine = new Engine(100, Cargo.Type.WOOD);
        assertEquals(1, engine.getForbiddenCargo().size());
    }
}