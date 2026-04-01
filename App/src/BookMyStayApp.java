import java.util.*;

public class BookMyStayApp {

    // ---------------- ROOM ----------------
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

    // ---------------- INVENTORY ----------------
    static class RoomInventory {
        private Map<String, Integer> availability = new HashMap<>();

        public RoomInventory() {
            availability.put("Single", 5);
            availability.put("Double", 3);
            availability.put("Suite", 2);
        }

        public int getAvailableRooms(String type) {
            return availability.getOrDefault(type, 0);
        }

        public Map<String, Integer> getAllAvailability() {
            return availability;
        }
    }

    // ---------------- SEARCH (READ ONLY) ----------------
    static class RoomSearchService {

        public void search(RoomInventory inventory) {
            Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

            System.out.println("\nRoom Search:\n");

            for (Room room : rooms) {
                int available = inventory.getAvailableRooms(room.getType());

                if (available > 0) {
                    System.out.println(room.getType() + " Room:");
                    room.displayRoomDetails();
                    System.out.println("Available: " + available);
                    System.out.println();
                }
            }
        }
    }

    // ---------------- RESERVATION ----------------
    static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    // ---------------- QUEUE (FCFS) ----------------
    static class BookingRequestQueue {
        private Queue<Reservation> queue;

        public BookingRequestQueue() {
            queue = new LinkedList<>();
        }

        public void addRequest(Reservation r) {
            queue.offer(r);
        }

        public Reservation getNextRequest() {
            return queue.poll();
        }

        public boolean hasRequests() {
            return !queue.isEmpty();
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        System.out.println("Booking Request Queue\n");

        // Inventory + Search
        RoomInventory inventory = new RoomInventory();
        RoomSearchService search = new RoomSearchService();
        search.search(inventory);

        // Queue
        BookingRequestQueue queue = new BookingRequestQueue();

        // Sample requests
        queue.addRequest(new Reservation("Aditi", "Single"));
        queue.addRequest(new Reservation("Rahul", "Double"));
        queue.addRequest(new Reservation("Yamunath", "Suite"));

        // Process requests (FCFS)
        System.out.println("Processing Bookings:\n");

        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();

            System.out.println("Processing booking for Guest: "
                    + r.getGuestName() + ", Room Type: " + r.getRoomType());
        }
    }
}