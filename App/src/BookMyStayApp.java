/****************************************************
 * BookMyStayApp: Concurrent Booking + Persistence
 ****************************************************/

import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

// Inventory class
class HotelInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private int availableRooms;

    public HotelInventory(int rooms) {
        this.availableRooms = rooms;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void decrementRoom() {
        if (availableRooms > 0) {
            availableRooms--;
        }
    }
}

// Persistence service
class FilePersistenceService {
    private final ReentrantLock lock = new ReentrantLock();

    public void saveInventoryToFile(HotelInventory inventory, String fileName) {
        lock.lock();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(inventory);
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public HotelInventory loadInventoryFromFile(String fileName) {
        lock.lock();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (HotelInventory) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return new HotelInventory(5); // default rooms
        } finally {
            lock.unlock();
        }
    }
}

// Booking processor
class ConcurrentBookingProcessor {
    private final HotelInventory inventory;

    public ConcurrentBookingProcessor(HotelInventory inventory) {
        this.inventory = inventory;
    }

    public synchronized void bookRoom(String user) {
        if (inventory.getAvailableRooms() > 0) {
            inventory.decrementRoom();
            System.out.println(user + " booked a room successfully.");
        } else {
            System.out.println(user + " booking failed. No rooms available.");
        }
    }
}

// Main class
public class BookMyStayApp {
    public static void main(String[] args) {
        String fileName = "inventory.dat";
        FilePersistenceService persistence = new FilePersistenceService();

        // Load inventory from file
        HotelInventory inventory = persistence.loadInventoryFromFile(fileName);
        ConcurrentBookingProcessor processor = new ConcurrentBookingProcessor(inventory);

        // Simulate concurrent booking
        Thread t1 = new Thread(() -> processor.bookRoom("User1"));
        Thread t2 = new Thread(() -> processor.bookRoom("User2"));
        Thread t3 = new Thread(() -> processor.bookRoom("User3"));
        Thread t4 = new Thread(() -> processor.bookRoom("User4"));
        Thread t5 = new Thread(() -> processor.bookRoom("User5"));
        Thread t6 = new Thread(() -> processor.bookRoom("User6"));

        t1.start(); t2.start(); t3.start();
        t4.start(); t5.start(); t6.start();

        // Wait for threads to finish
        try {
            t1.join(); t2.join(); t3.join();
            t4.join(); t5.join(); t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Save inventory state
        persistence.saveInventoryToFile(inventory, fileName);
    }
}