import java.util.*;

// Payment class to handle transactions
class Payment {
    private String paymentId;
    private double amount;
    private String method;
    private String status;

    public Payment(String paymentId, double amount, String method) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.status = "Pending";
    }

    public void processPayment() {
        // Simulate payment success
        this.status = "Completed";
        System.out.println("Payment ID: " + paymentId + " processed successfully via " + method);
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }
}

// Invoice class to generate booking invoices
class Invoice {
    private String invoiceId;
    private String reservationId;
    private double totalAmount;
    private Date issueDate;

    public Invoice(String invoiceId, String reservationId, double totalAmount) {
        this.invoiceId = invoiceId;
        this.reservationId = reservationId;
        this.totalAmount = totalAmount;
        this.issueDate = new Date();
    }

    public void displayInvoice() {
        System.out.println("\n--- Invoice ---");
        System.out.println("Invoice ID: " + invoiceId);
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Issue Date: " + issueDate);
    }
}

// Main class for Use Case 10
public class BookMyStayApp {
    public static void main(String[] args) {
        // Simulate a reservation payment
        Payment payment = new Payment("P001", 270.0, "Credit Card");
        payment.processPayment();

        // Generate invoice if payment successful
        if (payment.getStatus().equals("Completed")) {
            Invoice invoice = new Invoice("INV001", "R001", payment.getAmount());
            invoice.displayInvoice();
        } else {
            System.out.println("Payment failed. Invoice not generated.");
        }
    }
}