package ee.ttu.iti0202.orb;

public class MagicOrb extends Orb {
    public MagicOrb(String creator) {
        super(creator);
    }

    @Override
    public void charge(String resource, int amount) {
        super.charge(resource, amount * 2);
    }

    @Override
    public String toString() {
        return String.format("MagicOrb by %s", creator);
    }
}
