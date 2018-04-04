package ee.ttu.iti0202.kt1.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Company {

    public static void main(String[] args) {
        Company company = new Company();
        Airplane a1 = new Airplane("Boeing 900", 20);
        Airplane a2 = new Airplane("TU 100", 10);
        Airplane a3 = new Airplane("Sinilind 2.0", 2);

        company.addAirplane(a1);
        company.addAirplane(a2);

        Optional<Booking> bookingOptional = company.getNextBooking(a1);
        System.out.println(bookingOptional.get()); // Boeing 900-A1
        bookingOptional = company.getNextBooking(a2);
        System.out.println(bookingOptional.get()); // TU 100-A1
        bookingOptional = company.getNextBooking(a3);
        System.out.println(bookingOptional); // Optional.empty

        for (int i = 0; i < 9; i++) {
            bookingOptional = company.getNextBooking(a2);
            System.out.println(bookingOptional.get()); // TU 100-A2 ... TU 100-A10
        }
        System.out.println(company.getNextBooking(a2)); // Optional.empty
    }


    List<Airplane> airPlanes = new ArrayList<>();
    HashMap<Airplane, List<Booking>> map = new HashMap<>();

    void addAirplane(Airplane airpane) {
        if (!airPlanes.contains(airpane)) {
            airPlanes.add(airpane);
            map.put(airpane, new ArrayList<Booking>());
        }
    }

    Optional<Booking> getNextBooking(Airplane airplane) {
        if (!airPlanes.contains(airplane)) {
            return Optional.empty();
        } else if (map.containsKey(airplane) && map.get(airplane).size() >= airplane.getSeats()) {
            return Optional.empty();
        } else {
            String num = String.format("A%d", map.get(airplane).size() + 1);
            Booking booking = new Booking(airplane, num);
            map.get(airplane).add(booking);
            return Optional.of(booking);
        }

    }

    List<Airplane> getAirPlanes() {
        return airPlanes;
    }

    List<Booking> getBookings(Airplane airplane) {
        return map.get(airplane);
    }
}
