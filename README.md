# # BookMyStayApp

## Overview
BookMyStayApp is a Java-based simulation of a hotel booking system designed to demonstrate **concurrency control** and **data persistence**. It showcases how multiple users can attempt to book rooms simultaneously without causing race conditions, and how the system can persist its state to recover after unexpected shutdowns.

This project combines:
- **Use Case 11: Concurrent Booking Simulation (Thread Safety)**
- **Use Case 12: Data Persistence & System Recovery**

---

## Features
- **Concurrent Booking Simulation**
  - Multiple threads simulate users booking rooms at the same time.
  - Thread safety is ensured using `synchronized` methods.
  - Prevents race conditions and inconsistent room allocation.

- **Data Persistence & Recovery**
  - Inventory state is saved to a file (`inventory.dat`) after operations.
  - On restart, the system restores the last saved state.
  - Handles missing or corrupted persistence files gracefully.

---

## Project Structure
- `HotelInventory`  
  Represents the hotel’s available rooms and supports serialization.

- `ConcurrentBookingProcessor`  
  Handles booking requests with thread safety.

- `FilePersistenceService`  
  Provides methods to save and load inventory state from a file.

- `BookMyStayApp` (Main Class)  
  Entry point that loads inventory, simulates concurrent bookings, and saves state.

---

## Example Execution
```bash
javac BookMyStayApp.java
java BookMyStayApp
