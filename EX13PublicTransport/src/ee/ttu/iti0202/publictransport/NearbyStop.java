package ee.ttu.iti0202.publictransport;

import java.util.Set;

public class NearbyStop extends Stop implements Comparable<NearbyStop> {

    private int distance;
    private Set<String> transportations;

    public Set<String> getTransportations() {
        return transportations;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public int compareTo(NearbyStop otherStop) {
        return Integer.compare(this.getDistance(), otherStop.getDistance());
    }

    @Override
    public String toString() {
        return String.format("%13s   (%11s)->%23s - %dm -> Bussid: ",
                getName(), getId(), getDirection(), getDistance()) + getTransportations();
    }
}
