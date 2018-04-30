package ee.ttu.iti0202.publictransport;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TransportController {

    public DeparturesFromStop getDeparturesFromStop(String stopId) throws Exception {
        return TransportService.departuresFromStop(stopId);
    }

    public Set<NearbyStop> getNearbyStops(Location location) throws Exception {
        List<NearbyStop> stopsList = new ArrayList<>(List.of(
                TransportService.nearbyStops(location.getLatitude(), location.getLongitude())
        ));
        Collections.sort(stopsList);
        return new LinkedHashSet<>(stopsList);
    }

    public Optional<NearbyStop> getNearestStop(Location location) throws Exception {
        List<NearbyStop> stops = new ArrayList<>(List.of(
                TransportService.nearbyStops(location.getLatitude(), location.getLongitude())
        ));

        if (stops.size() == 0) {
            return Optional.empty();
        }

        Collections.sort(stops);
        return Optional.of(stops.get(0));
    }

    public Optional<Departure> getNextDepartureFromStop(String stopId) throws Exception {
        DeparturesFromStop departuresFromStop = TransportService.departuresFromStop(stopId);

        if (departuresFromStop.getStop().getName().equals("")) {
            return Optional.empty();
        }

        List<Departure> departures = new ArrayList<>(departuresFromStop.getDepartures());
        Collections.sort(departures);
        return Optional.of(departures.get(0));
    }

    public static void main(String[] args) throws Exception {
//        TransportController controller = new TransportController();
//        //controller.getNearbyStops(new Location(59.3977111, 24.660198));
//        System.out.println(controller.getNearestStop(new Location(59.3977111,24.660198)));
    }
}
