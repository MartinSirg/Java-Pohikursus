package ee.ttu.iti0202.kt1.booking;

public class Airplane {
    private String name;
    private int seats;

    Airplane(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getSeats() {
        return seats;
    }

    void setSeats(int seats) {
        this.seats = seats;
    }
}
