package ee.ttu.iti0202.orb;

public class Orb {
    protected String creator;
    protected int energy = 0;

    public Orb(String creator) {
        this.creator = creator;
    }

    public void charge(String resource, int amount) {
        String buffer = resource.replace(" ", "");

        if (!resource.toLowerCase().equals("dust") && buffer.length() != 0) {
            energy += resource.length() * amount; // possible viga: kasuta resource.len asemel buffer.len
        }
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return String.format("Orb by %s", creator);
    }
}
