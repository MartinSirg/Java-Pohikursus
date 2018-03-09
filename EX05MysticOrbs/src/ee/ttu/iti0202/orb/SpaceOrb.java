package ee.ttu.iti0202.orb;

public class SpaceOrb extends Orb {
    public SpaceOrb(String creator) {
        super(creator);
        energy = 100;
    }

    @Override
    public void charge(String resource, int amount) {
    }

    @Override
    public String toString() {
        return String.format("SpaceOrb by %s", creator);
    }

    public boolean absorb(Orb orb) {
        if (orb.getEnergy() >= energy) {
            return false;
        } else {
            energy += orb.getEnergy();
            orb.setEnergy(0);
            return true;
        }
    }
}
