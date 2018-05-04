package ee.ttu.iti0202.kt4.hotel.rooms;

public class MediumRoom extends Room {

    MediumRoom(int id, Special special) {
        super(id, 100, special);
        super.type = RoomTypes.MEDIUM;
    }
}
