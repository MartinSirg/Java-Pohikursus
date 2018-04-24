package ee.ttu.iti0202.depot.cargo;

public class Fire extends Cargo {
    private final static int LEVEL = 7;

    Fire() {
        super();
        super.dangerLevel = LEVEL;
    }
}
