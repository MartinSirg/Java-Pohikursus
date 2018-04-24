package ee.ttu.iti0202.depot.cargo;

public class Fuel extends Cargo {
    private static final int LEVEL = 6;

    Fuel() {
        super();
        super.dangerLevel = LEVEL;
    }
}
