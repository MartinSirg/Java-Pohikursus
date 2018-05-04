package ee.ttu.iti0202.kt4.hotel.rooms;

public class BigRoom extends Room {

    BigRoom(int id, Special special) {
        super(id, 200, special);
        super.type = RoomTypes.BIG;
    }
}
