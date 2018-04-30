package ee.ttu.iti0202.publictransport;


import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class TransportController {

    public DeparturesFromStop getDeparturesFromStop(String stopId) {
        return TransportService.departuresFromStop(stopId);
    }

    public Set<NearbyStop> getNearbyStops(Location location) {
        List<NearbyStop> stopsList = new ArrayList<>(Arrays.asList(
                TransportService.nearbyStops(location.getLatitude(), location.getLongitude())
        ));
        Collections.sort(stopsList);
        return new LinkedHashSet<>(stopsList);
    }

    public Optional<NearbyStop> getNearestStop(Location location) {
        List<NearbyStop> stops = new ArrayList<>(Arrays.asList(
                TransportService.nearbyStops(location.getLatitude(), location.getLongitude())
        ));

        if (stops.size() == 0) {
            return Optional.empty();
        }

        Collections.sort(stops);
        return Optional.of(stops.get(0));
    }

    public Optional<Departure> getNextDepartureFromStop(String stopId) {
        DeparturesFromStop departuresFromStop = TransportService.departuresFromStop(stopId);

        if (departuresFromStop.getStop().getName().equals("")) {
            return Optional.empty();
        }

        List<Departure> departures = new ArrayList<>(departuresFromStop.getDepartures());
        Collections.sort(departures);
        return Optional.of(departures.get(0));
    }

    public static void main(String[] args) {
        TransportController controller = new TransportController();
//        System.out.println(controller.getNearbyStops(new Location(59.3977111, 24.660198)));
//        System.out.println(controller.getNearestStop(new Location(59.3977111,24.660198)));
        System.out.println(controller.getDeparturesFromStop("tal_03504-1"));
    }
}
