package ee.ttu.iti0202.publictransport;

import java.util.Set;

public class DeparturesFromStop {
    private Stop stop;
    private Set<Departure> departures;

    public Stop getStop() {
        return stop;
    }

    public Set<Departure> getDepartures() {
        return departures;
    }

    @Override
    public String toString() {
        return stop.getName() + "  " + departures;
    }
}
