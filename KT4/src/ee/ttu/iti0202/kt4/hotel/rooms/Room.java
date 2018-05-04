package ee.ttu.iti0202.kt4.hotel.rooms;

import ee.ttu.iti0202.kt4.hotel.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Room {
    private int id, price;
    private Special special;
    RoomTypes type;
    private List<Booking> bookings;


    enum RoomTypes { BIG, SMALL, MEDIUM }

    enum Special { PARTY, MEGA_PARTY, NO_SPECIAL }

    Room(int id, int price, Special special) {
        this.id = id;
        if (special == Special.PARTY) this.price = price * 2;
        if (special == Special.MEGA_PARTY) this.price = price * 3;
        if (special == Special.NO_SPECIAL) this.price = price;
        this.special = special;
        this.bookings = new ArrayList<>();
    }

    public static Optional<Room> of(RoomTypes type, int id, Special special) {
        if (id < 0) return Optional.empty();
        switch (type) {
            case BIG: return Optional.of(new BigRoom(id, special));
            case SMALL: return Optional.of(new SmallRoom(id));
            case MEDIUM: return Optional.of(new MediumRoom(id, special));
            default: return Optional.empty();
        }
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public Special getSpecial() {
        return special;
    }
}
