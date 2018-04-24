package ee.ttu.iti0202.depot.cargo;

public abstract class Cargo {
    int dangerLevel = 0;
    public enum Type {HUMANS, WOOD, WATER, FUEL, FIRE}


    public static Cargo of(Type type) {

        switch (type) {
            case WATER: return new Water();
            case FIRE: return  new Fire();
            case FUEL: return new Fuel();
            case WOOD: return new Wood();
            case HUMANS: return new Humans();
            default: return new Humans();
        }
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    @Override
    public String toString() {
        if (this instanceof Fire) {
            return "FIRE cargo. Danger " + dangerLevel;
        } else if (this instanceof Fuel) {
            return "FUEL cargo. Danger " + dangerLevel;
        } else if (this instanceof Wood) {
            return "WOOD cargo. Danger " + dangerLevel;
        } else if (this instanceof Water) {
            return "WATER cargo. Danger " + dangerLevel;
        } else {
            return "HUMANS cargo. Danger " + dangerLevel;
        }

    }
}
