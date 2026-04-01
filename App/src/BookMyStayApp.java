class BookingHistory {
    private String reservationId;
    private String customerName;
    private String roomType;
    private String bookingDate;
    private String status;

    public BookingHistory(String reservationId, String customerName, String roomType, String bookingDate, String status) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public void displayHistory() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Room Type: " + roomType);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Status: " + status);
    }
}

class BookingReportService {
    public void generateReport(BookingHistory history) {
        System.out.println("\n--- Booking Report ---");
        history.displayHistory();
    }
}

public class BookMyStayApp_UseCase8 {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory("B001", "Alice", "Deluxe Suite", "2024-02-15", "Confirmed");
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}