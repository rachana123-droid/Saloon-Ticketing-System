import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.sql.*;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class BookingController {

    @FXML
    private TextField customerNameField;
    @FXML
    private CheckBox haircutCheckBox;
    @FXML
    private CheckBox spaCheckBox;
    @FXML
    private CheckBox facialCheckBox;
    @FXML
    private Label ticketDetailsLabel;

    @FXML
    private TableView<Ticket> ticketTable;
    @FXML
    private TableColumn<Ticket, Integer> colTicketID;
    @FXML
    private TableColumn<Ticket, String> colCustomerName;
    @FXML
    private TableColumn<Ticket, String> colServices;
    @FXML
    private TableColumn<Ticket, Integer> colTotalAmount;
    @FXML
    private VBox mainContent;

    @FXML
    private VBox ticketContainer;
    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane bookingPane; // Make sure your FXML has fx:id="bookingPane"

    @FXML
    public void initialize() {
        // Map TableView columns to Ticket properties
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), bookingPane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();
        // Center Ticket Table Block
        AnchorPane.setTopAnchor(bookingPane, 0.0);
        AnchorPane.setBottomAnchor(bookingPane, 0.0);
        AnchorPane.setLeftAnchor(bookingPane, 0.0);
        AnchorPane.setRightAnchor(bookingPane, 0.0);
        bookingPane.setStyle("-fx-alignment: center;");

        colTicketID.setCellValueFactory(new PropertyValueFactory<>("ticketID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colServices.setCellValueFactory(new PropertyValueFactory<>("services"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        loadTickets(); // Load existing tickets when screen opens
    }

    public void generateTicket() {
        String customerName = customerNameField.getText();
        if (customerName.isEmpty()) {
            ticketDetailsLabel.setText("Please enter the customer's name.");
            return;
        }

        StringBuilder services = new StringBuilder();
        int totalAmount = 0;
        int ticketID = new Random().nextInt(1000) + 1;

        if (haircutCheckBox.isSelected()) {
            services.append("Haircut (₹300), ");
            totalAmount += 300;
        }
        if (spaCheckBox.isSelected()) {
            services.append("Spa (₹500), ");
            totalAmount += 500;
        }
        if (facialCheckBox.isSelected()) {
            services.append("Facial (₹350), ");
            totalAmount += 350;
        }

        if (totalAmount == 0) {
            ticketDetailsLabel.setText("Please select at least one service.");
            return;
        }

        String serviceList = services.toString().trim();
        if (serviceList.endsWith(",")) {
            serviceList = serviceList.substring(0, serviceList.length() - 1);
        }

        // Save ticket details to MySQL database
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO tickets (ticket_id, customer_name, services, total_amount) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, ticketID);
            pstmt.setString(2, customerName);
            pstmt.setString(3, serviceList);
            pstmt.setInt(4, totalAmount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            ticketDetailsLabel.setText("Error saving ticket to database.");
            return;
        }

        // Show ticket details
        ticketDetailsLabel.setText(
                "Ticket ID: " + ticketID + "\n" +
                        "Customer: " + customerName + "\n" +
                        "Services: " + serviceList + "\n" +
                        "Total: ₹" + totalAmount
        );

        loadTickets(); // Refresh TableView with the new ticket
    }

    public void loadTickets() {
        ticketList.clear();
        try (Connection conn = DatabaseConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tickets");

            while (rs.next()) {
                ticketList.add(new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("customer_name"),
                        rs.getString("services"),
                        rs.getInt("total_amount")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ticketTable.setItems(ticketList);
    }
}
