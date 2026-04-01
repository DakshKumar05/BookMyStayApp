// CODE SNIPPET --------------------------------------------------------------
// BookMyStayApp: Concurrent Booking Simulation
// Demonstrates thread safety and synchronization
// Multiple users booking rooms concurrently
// Prevents race conditions
// ---------------------------------------------------------------------------

class ConcurrentBookingProcessor {
    private static int availableRooms = 5;

    public synchronized void bookRoom(String user) {
        if (availableRooms > 0) {
            System.out.println(user + " booked a room successfully.");
            availableRooms--;
        } else {
            System.out.println(user + " booking failed. No rooms available.");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor();

        Thread t1 = new Thread(() -> processor.bookRoom("User1"));
        Thread t2 = new Thread(() -> processor.bookRoom("User2"));
        Thread t3 = new Thread(() -> processor.bookRoom("User3"));
        Thread t4 = new Thread(() -> processor.bookRoom("User4"));
        Thread t5 = new Thread(() -> processor.bookRoom("User5"));
        Thread t6 = new Thread(() -> processor.bookRoom("User6"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}