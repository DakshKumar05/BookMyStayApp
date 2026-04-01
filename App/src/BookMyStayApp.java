import java.util.Scanner;

// Custom exception for invalid booking input
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator class for booking input
class BookingValidator {
    public static void validateRoom(String roomNumber) throws InvalidBookingException {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new InvalidBookingException("Room number cannot be empty.");
        }

        // Room number must start with a letter followed by two digits (e.g., A01, B12)
        if (!roomNumber.matches("^[A-Z]{1}[0-9]{2}$")) {
            throw new InvalidBookingException("Invalid Room Number format. Expected format: A01, B12, etc.");
        }

        System.out.println("Room " + roomNumber + " is valid and booking can proceed.");
    }
}

// Main class for Use Case 9
public class BookMyStayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Room Number to Validate: ");
        String roomNumber = scanner.nextLine();

        try {
            BookingValidator.validateRoom(roomNumber);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        scanner.close();
    }
}