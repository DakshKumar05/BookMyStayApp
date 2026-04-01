import java.util.*;

class RoomAllocationService {
    private Set<String> assignedRooms = new HashSet<>();
    private Map<String, String> roomTypeMap = new HashMap<>();

    // Allocate a room for a given type
    public void allocateRoom(String roomType) {
        String roomID = generateUniqueRoomID(roomType);

        // Prevent duplicate assignment
        if (!assignedRooms.contains(roomID)) {
            assignedRooms.add(roomID);
            roomTypeMap.put(roomType, roomID);
            System.out.println("Room allocated for " + roomType + ": " + roomID);
        } else {
            System.out.println("Duplicate room ID detected for " + roomType);
        }
    }

    // Generate unique room IDs using UUID
    private String generateUniqueRoomID(String roomType) {
        return roomType + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    // Retrieve all allocations
    public Map<String, String> getAllocations() {
        return roomTypeMap;
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomAllocationService service = new RoomAllocationService();

        // Simulate booking requests
        service.allocateRoom("Deluxe");
        service.allocateRoom("Suite");
        service.allocateRoom("Single");

        // Print final allocations
        System.out.println("Booking confirmed for Hotel: " + service.getAllocations().keySet());
    }
}