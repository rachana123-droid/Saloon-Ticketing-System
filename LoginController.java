import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox loginBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    private Stage stage;
    private Scene scene;

    // Hardcoded credentials (Modify as needed)
    private final String validUsername = "admin";
    private final String validPassword = "1234";

    @FXML
    public void initialize() {
        // Apply fade-in effect when login page loads
        FadeTransition fadeIn = new FadeTransition(Duration.millis(800), loginBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        // Ensure the login box is dynamically centered
        AnchorPane.setTopAnchor(loginBox, 0.0);
        AnchorPane.setBottomAnchor(loginBox, 0.0);
        AnchorPane.setLeftAnchor(loginBox, 0.0);
        AnchorPane.setRightAnchor(loginBox, 0.0);
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(validUsername) && password.equals(validPassword)) {
            loadBookingPage(event);
        } else {
            showErrorMessage("Invalid Login", "Username or Password is incorrect!");
        }
    }

    private void loadBookingPage(ActionEvent event) {
        try {
            // Load the Booking Page FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/booking.fxml"));
            Parent root = loader.load();

            // Get Current Stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            // Apply Fade-in Animation
            FadeTransition fadeIn = new FadeTransition(Duration.millis(800), root);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();

            // Set Scene
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            showErrorMessage("Error", "Failed to load booking page. Ensure 'booking.fxml' is in 'src/main/resources/'.");
            e.printStackTrace(); // Print error details in console
        }
    }


    private void showErrorMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
