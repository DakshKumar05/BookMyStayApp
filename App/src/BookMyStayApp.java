import java.util.*;

// -------------------- Use Case 6 --------------------
class RoomAllocationService {
    private Set<String> assignedRooms = new HashSet<>();
    private Map<String, String> roomTypeMap = new HashMap<>();

    public void allocateRoom(String roomType) {
        String roomID = generateUniqueRoomID(roomType);

        if (!assignedRooms.contains(roomID)) {
            assignedRooms.add(roomID);
            roomTypeMap.put(roomType, roomID);
            System.out.println("Room allocated for " + roomType + ": " + roomID);
        } else {
            System.out.println("Duplicate room ID detected for " + roomType);
        }
    }

    private String generateUniqueRoomID(String roomType) {
        return roomType + "_" + UUID.randomUUID().toString().substring(0, 8);
    }

    public Map<String, String> getAllocations() {
        return roomTypeMap;
    }
}

// -------------------- Use Case 7 --------------------
class AddOnService {
    private String serviceName;
    private double serviceCost;

    public AddOnService(String name, double cost) {
        this.serviceName = name;
        this.serviceCost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getServiceCost() {
        return serviceCost;
    }
}

class SpaService extends AddOnService {
    private int duration;

    public SpaService(String name, double cost, int duration) {
        super(name, cost);
        this.duration = duration;
    }

    public void bookSpaService() {
        System.out.println("Spa service booked for " + duration + " minutes.");
    }

    public int getDuration() {
        return duration;
    }
}

class Reservation {
    private String reservationID;
    private List<AddOnService> selectedServices;
    private double totalCost;

    public Reservation(String id, double baseCost) {
        this.reservationID = id;
        this.totalCost = baseCost;
        selectedServices = new ArrayList<>();
    }

    public void addService(AddOnService service) {
        selectedServices.add(service);
        totalCost += service.getServiceCost();
    }

    public void displayReservationDetails() {
        System.out.println("Reservation ID: " + reservationID);
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("Selected Services:");
        for (AddOnService service : selectedServices) {
            System.out.println("- " + service.getServiceName());
        }
    }
}

// -------------------- Main Class --------------------
public class BookMyStayApp {
    public static void main(String[] args) {
        // Use Case 6: Room Allocation
        RoomAllocationService roomService = new RoomAllocationService();
        roomService.allocateRoom("Deluxe");
        roomService.allocateRoom("Suite");
        roomService.allocateRoom("Single");

        System.out.println("Final Room Allocations: " + roomService.getAllocations());

        // Use Case 7: Add-On Service Selection
        Reservation reservation = new Reservation("R001", 200.0);

        AddOnService spa = new SpaService("Spa Treatment", 50.0, 60);
        AddOnService breakfast = new AddOnService("Breakfast Buffet", 20.0);

        reservation.addService(spa);
        reservation.addService(breakfast);

        reservation.displayReservationDetails();
    }
}