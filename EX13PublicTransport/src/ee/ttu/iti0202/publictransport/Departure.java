package ee.ttu.iti0202.publictransport;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Departure implements Comparable<Departure> {
    private String name;
    private String time;
    private String destination;
    private static final int MINUTES_IN_HOUR = 60;

    public int getMinutesFromNow() {
        //if (LocalDateTime.now().isAfter(getTime())) return 0;
        Instant current = Instant.now();
        Instant departs = Instant.parse(time);
        return Math.toIntExact(ChronoUnit.MINUTES.between(departs, current));
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return LocalDateTime.parse(time.replace("Z", ""));
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return String.format("\n{Bussinumber: %s\nVäljub %s (%d min pärast)\n Sihtpunkt: %s}",
                name, time, getMinutesFromNow(), destination);
    }

    @Override
    public int compareTo(Departure o) {
        return Integer.compare(getMinutesFromNow(), o.getMinutesFromNow());
    }
}
