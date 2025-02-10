public class Ticket {
    private int ticketID;
    private String customerName;
    private String services;
    private int totalAmount;

    public Ticket(int ticketID, String customerName, String services, int totalAmount) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.services = services;
        this.totalAmount = totalAmount;
    }

    public int getTicketID() { return ticketID; }
    public String getCustomerName() { return customerName; }
    public String getServices() { return services; }
    public int getTotalAmount() { return totalAmount; }
}
