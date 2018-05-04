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

    public boolean overLaps(int start, int end, Room room) {
        // 1-5, 2-3
        if (start > startDate && end < endDate ) return false;
        // 3-7, 1-4
        if (end < endDate && end > startDate) return false;
        if (start >= startDate && start < endDate) return false;
        return true;
    }

    public int getStartDate() {
        return startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public Room getRoom() {
        return room;
    }
}
