package ee.ttu.iti0202.kt4.hotel.rooms;

public class SmallRoom extends Room {

    SmallRoom(int id) {
        super(id, 50, Special.NO_SPECIAL);
        super.type = RoomTypes.SMALL;
    }
}
