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

    // Room Types
    static class SingleRoom extends Room {
        public SingleRoom() { super(1, 250, 1500.0); }
        public String getType() { return "Single"; }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() { super(2, 400, 2500.0); }
        public String getType() { return "Double"; }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() { super(4, 750, 5000.0); }
        public String getType() { return "Suite"; }
    }

    // Inventory (centralized)
    static class RoomInventory {
        private Map<String, Integer> roomAvailability;

        public RoomInventory() {
            roomAvailability = new HashMap<>();
            roomAvailability.put("Single", 5);
            roomAvailability.put("Double", 3);
            roomAvailability.put("Suite", 2);
        }

        public Map<String, Integer> getAllAvailability() {
            return roomAvailability; // read-only usage expected
        }
    }

    // Search Service (READ ONLY)
    static class RoomSearchService {

        public void searchAvailableRooms(RoomInventory inventory) {

            Room single = new SingleRoom();
            Room dbl = new DoubleRoom();
            Room suite = new SuiteRoom();

            Map<String, Integer> availability = inventory.getAllAvailability();

            System.out.println("Room Search\n");

            // Single
            if (availability.get("Single") > 0) {
                System.out.println("Single Room:");
                single.displayRoomDetails();
                System.out.println("Available: " + availability.get("Single"));
                System.out.println();
            }

            // Double
            if (availability.get("Double") > 0) {
                System.out.println("Double Room:");
                dbl.displayRoomDetails();
                System.out.println("Available: " + availability.get("Double"));
                System.out.println();
            }

            // Suite
            if (availability.get("Suite") > 0) {
                System.out.println("Suite Room:");
                suite.displayRoomDetails();
                System.out.println("Available: " + availability.get("Suite"));
                System.out.println();
            }
        }
    }

    // Main Method
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService();

        searchService.searchAvailableRooms(inventory);
    }
}