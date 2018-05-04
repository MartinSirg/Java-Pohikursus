package ee.ttu.iti0202.kt4.hotel;

import ee.ttu.iti0202.kt4.hotel.rooms.Room;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    List<Room> rooms = new ArrayList<>();

    public void addRoom(Room roomToAdd) {
        for (Room room: rooms) { // kontrolli et
            if (room.getId() == roomToAdd.getId()) return;
        }
        rooms.add(roomToAdd)
    }
}
