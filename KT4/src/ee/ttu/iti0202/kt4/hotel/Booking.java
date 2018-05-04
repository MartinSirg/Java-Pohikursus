package ee.ttu.iti0202.kt4.hotel;

import ee.ttu.iti0202.kt4.hotel.rooms.Room;

public class Booking {
    private int startDate;
    private int endDate;
    private Room room;

    Booking(int start, int end, Room room) {
        this.startDate = start;
        this.endDate = end;
        this.room = room;
        room.addBooking(this);
    }
}
