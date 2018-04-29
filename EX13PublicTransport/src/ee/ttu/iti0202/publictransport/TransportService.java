package ee.ttu.iti0202.publictransport;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class TransportService {
    static final String REGION = "tallinn";
    private static Gson gson = new Gson();

    public static NearbyStop[] nearbyStops(double lat, double lng) throws Exception {
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

    }

    public static DeparturesFromStop departuresFromStop(String stopId) throws Exception {
        String urlString = String.format("https://public-transport-api.herokuapp.com/departures/%s/%s", REGION, stopId);
        URL url = new URL(urlString);

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return gson.fromJson(content.toString(), DeparturesFromStop.class);

    }

    public static void main(String[] args) throws Exception {
        List<Departure> departures = new ArrayList<>(departuresFromStop("tal_03504-1").getDepartures());
        for (Departure departure: departures) {
            System.out.println(departure);
        }

    }
}
