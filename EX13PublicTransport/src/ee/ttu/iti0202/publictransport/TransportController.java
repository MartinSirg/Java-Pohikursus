package ee.ttu.iti0202.publictransport;


import java.io.IOException;
import java.util.*;

public class TransportController {

    public DeparturesFromStop getDeparturesFromStop(String stopId) {
        return null;
    }

    public Set<NearbyStop> getNearbyStops(Location location) throws Exception {
        List<NearbyStop> stopsList = new ArrayList<>(List.of(
                TransportService.nearbyStops(location.getLatitude(), location.getLongitude())));
        Collections.sort(stopsList);
        return new LinkedHashSet<>(stopsList);
    }

    public Optional<NearbyStop> getNearestStop(Location location) {
        return Optional.empty();
    }

    public Optional<Departure> getNextDepartureFromStop(String stopId) {
        return Optional.empty();
    }

    public static void main(String[] args) throws Exception {
        TransportController controller = new TransportController();
        controller.getNearbyStops(new Location(59.3977111, 24.660198));
    }
}
