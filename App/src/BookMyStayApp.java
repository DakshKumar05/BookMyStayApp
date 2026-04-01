public class BookMyStayApp {

    // Abstract Room class
    static abstract class Room {
        protected int maxGuests;
        protected int size;
        protected double price;
        protected boolean available;

        public Room(int maxGuests, int size, double price) {
            this.maxGuests = maxGuests;
            this.size = size;
            this.price = price;
            this.available = true;
        }

        public void displayRoomDetails() {
            System.out.println("Beds: " + maxGuests);
            System.out.println("Size: " + size + " sqft");
            System.out.println("Price per night: " + price);
            System.out.println("Available: " + available);
            System.out.println();
        }
    }

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super(1, 250, 1500.0);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super(2, 400, 2500.0);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super(4, 750, 5000.0);
        }
    }

    // Main Method
    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization\n");

        // Create rooms
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display details
        System.out.println("Single Room:");
        single.displayRoomDetails();

        System.out.println("Double Room:");
        dbl.displayRoomDetails();

        System.out.println("Suite Room:");
        suite.displayRoomDetails();
    }
}