import java.util.*;

// Main App
public class BookMyStayApp {

    // Abstract Room class
    static abstract class Room {
        protected int maxGuests;
        protected int size;
        protected double price;

        public Room(int maxGuests, int size, double price) {
            this.maxGuests = maxGuests;
            this.size = size;
            this.price = price;
        }

        public void displayRoomDetails() {
            System.out.println("Beds: " + maxGuests);
            System.out.println("Size: " + size + " sqft");
            System.out.println("Price per night: " + price);
        }

        public abstract String getType();
    }

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super(1, 250, 1500.0);
        }

        public String getType() {
            return "Single";
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super(2, 400, 2500.0);
        }

        public String getType() {
            return "Double";
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super(4, 750, 5000.0);
        }

        public String getType() {
            return "Suite";
        }
    }

    // Inventory Class (HashMap based)
    static class RoomInventory {

        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            initializeInventory();
        }

        // Initialize availability
        private void initializeInventory() {
            roomAvailability.put("Single", 5);
            roomAvailability.put("Double", 3);
            roomAvailability.put("Suite", 2);
        }

        // Get availability
        public int getAvailableRooms(String type) {
            return roomAvailability.getOrDefault(type, 0);
        }

        // Update availability
        public void updateAvailability(String type, int count) {
            if (roomAvailability.containsKey(type)) {
                roomAvailability.put(type, count);
            }
        }

        // Display full inventory
        public void displayInventory(Room room) {
            System.out.println(room.getType() + " Room:");
            room.displayRoomDetails();
            System.out.println("Available Rooms: " + getAvailableRooms(room.getType()));
            System.out.println();
        }
    }

    // Main Method
    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory(single);
        inventory.displayInventory(dbl);
        inventory.displayInventory(suite);
    }
}