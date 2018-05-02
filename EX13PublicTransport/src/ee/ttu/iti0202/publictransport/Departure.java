package ee.ttu.iti0202.publictransport;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class Departure implements Comparable<Departure> {
    private String name;
    private String time;
    private String destination;
    private static final int MINUTES_IN_HOUR = 60;

    public int getMinutesFromNow() {
        //if (LocalDateTime.now().isAfter(getTime())) return 0;
        return Math.toIntExact(ChronoUnit.MINUTES.between(LocalDateTime.now(), getTime()));
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        Instant departs = Instant.parse(time);
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(departs, zoneId);
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return String.format("\n{Bussinumber: %s\nVäljub %s (%d min pärast)\n Sihtpunkt: %s}",
                name, getTime(), getMinutesFromNow(), destination);
    }

    @Override
    public int compareTo(Departure o) {
        return Integer.compare(getMinutesFromNow(), o.getMinutesFromNow());
    }
}
