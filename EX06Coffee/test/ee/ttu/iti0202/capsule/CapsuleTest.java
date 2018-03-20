package ee.ttu.iti0202.capsule;

import ee.ttu.iti0202.drinks.Drink;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CapsuleTest {
    private Capsule capsule;

    @Before
    public void init() {
        capsule = new Capsule(Drink.Drinks.COFFEE);

    }
    @Test
    public void useCapsule() {
        assertEquals(Drink.Drinks.COFFEE, capsule.useCapsule());
        assertEquals(Drink.Drinks.WATER, capsule.useCapsule());
    }

    @Test
    public void waterNeeded() {
        int needed = 0;
        try {
            needed = capsule.waterNeeded();
        } catch (Exception e) {
            fail("No exception expected. Got: " + e.getMessage());
        }
        assertEquals(200, needed);
    }
}