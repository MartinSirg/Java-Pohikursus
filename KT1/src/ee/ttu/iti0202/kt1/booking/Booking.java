package ee.ttu.iti0202.kt1.booking;

public class Booking {

    private Airplane airplane;
    private String seatNumber;

    public Booking(Airplane airplane, String seatNumber) {
        this.seatNumber = seatNumber;
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", airplane.getName(), seatNumber);
    }
}
