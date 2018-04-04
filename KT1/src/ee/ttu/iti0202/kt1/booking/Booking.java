package ee.ttu.iti0202.kt1.booking;

public class Booking {
    Airplane airplane;
    String seatNumber;

    Booking(Airplane airplane, String seatNumber) {
        this.seatNumber = seatNumber;
        this.airplane = airplane;
    }

    Airplane getAirplane() {
        return airplane;
    }

    void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    String getSeatNumber() {
        return seatNumber;
    }

    void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", airplane.getName(), seatNumber);
    }
}
