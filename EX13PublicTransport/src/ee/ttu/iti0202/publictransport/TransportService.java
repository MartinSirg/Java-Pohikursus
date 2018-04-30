package ee.ttu.iti0202.publictransport;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TransportService {
    static final String REGION = "tallinn";
    private static Gson gson = new Gson();

    public static NearbyStop[] nearbyStops(double lat, double lng) {
        try {
            String urlString = String.format("https://public-transport-api.herokuapp.com/stops/%.6f/%.6f", lat, lng);
            URL url = new URL(urlString);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return gson.fromJson(content.toString(), NearbyStop[].class);
        } catch (Exception e) {
            return new NearbyStop[0];
        }
    }

    public static DeparturesFromStop departuresFromStop(String stopId) {
        try {
            String urlString = String.format("https://public-transport-api.herokuapp.com/departures/%s/%s",
                    REGION, stopId);
            URL url = new URL(urlString);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return gson.fromJson(content.toString(), DeparturesFromStop.class);
        } catch (Exception e) {
            return new DeparturesFromStop();
        }

    }

    public static void main(String[] args) throws Exception {
//        System.out.println(List.of(nearbyStops(1,1)));
    }
}
