package ee.ttu.iti0202.publictransport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

public class Departure {
    private String name;
    private String time;
    private String destination;

    public int getMinutesFromNow() {
        //if (LocalDateTime.now().isAfter(getTime())) return 0;
        Duration duration = Duration.between(LocalDateTime.now(), getTime());
        //TODO : duration.toDaysPart() * 1440 -> int + return
        return duration.toHoursPart() * 60 + duration.toMinutesPart();
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
        return String.format("\n{Bussinumber: %s\nVäljub %s (%d min pärast)\n Sihtpunkt: %s}", name, time, getMinutesFromNow(), destination);
    }

    public static void main(String[] args) {

    }
}
