package ee.ttu.iti0202.lotr;

public class Person {
    private String race;
    private String name;
    private Ring ring;

    public Person(String race, String name, Ring ring){
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this(race, name, null);
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }

    public String isSauron() {
        if (ring.equals(null)) {
            return "No";
        }
        if("Sauron".equals(name)) {
            if (!"The one".equals(ring.getType())){
                return "No, but he's claiming to be";
            }
            else if (!"gold".equals(ring.getMaterial())) {
                return "No, the ring is fake!";
            } else {
                return "Affirmative";
            }

        }
        if ("The one".equals(getRing().getType()) && "gold".equals(ring.getMaterial()) && !"Sauron".equals(name)) {
            return "No, he just stole the ring";
        } else {
            return "No";
        }
    }

    public static void main(String[] args) {
    }
}
