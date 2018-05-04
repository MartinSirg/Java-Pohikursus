package ee.ttu.iti0202.kt4.hotel;


import ee.ttu.iti0202.kt4.hotel.rooms.Room;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hotel {

    List<Room> rooms = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();

    public void addRoom(Room roomToAdd) {
        for (Room room: rooms) { // kontrolli et ei eksisteeriks sellise id-ga tuba
            if (room.getId() == roomToAdd.getId()) return;
        }
        rooms.add(roomToAdd);
    }

    public Optional<Booking>  makeBooking(Room.RoomTypes type, int start, int end) {
        if (end < start) return Optional.empty();
        Room roomToBeBooked = null;
        for (Room room: rooms) {
            List<Integer> daysOccupied = new ArrayList<>();
            boolean isAvailable = true;

            for (Booking booking: room.getBookings()) {
                for (int i = booking.getStartDate(); i < booking.getEndDate(); i++) {
                    daysOccupied.add(i);
                }
            }

            for (int i = start; i < end; i++) {
                if (daysOccupied.contains(i)) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                roomToBeBooked = room;
                break;
            }

        }
        if (roomToBeBooked != null) {
            Booking booking = new Booking(start, end, roomToBeBooked);
            bookings.add(booking);
            return Optional.of(booking);
        } else {
            return Optional.empty();
        }
    }

    public int getProfit() {
        int result = 0;
        for (Booking booking: bookings) {
            int days = booking.getEndDate() - booking.getStartDate();
            result += days * booking.getRoom().getPrice();
        }
        return result;
    }

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.addRoom(Room.of(Room.RoomTypes.BIG, 1, Room.Special.MEGA_PARTY).get());
        hotel.addRoom(Room.of(Room.RoomTypes.BIG, 1, Room.Special.MEGA_PARTY).get());
        hotel.addRoom(Room.of(Room.RoomTypes.SMALL, 2, Room.Special.NO_SPECIAL).get());
        System.out.println(hotel.rooms.size());
    }
}
