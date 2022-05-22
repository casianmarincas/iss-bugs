package bugs;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginController extends AbstractController{

    @FXML
    private RadioButton programmer;

    @FXML
    private RadioButton tester;

    @FXML
    private TextField usernameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private ToggleGroup toggleGroup;

    protected LoginController(Service service) {
        super(service);
    }

    @FXML
    protected void initialize() {
        toggleGroup = new ToggleGroup();
        programmer.setToggleGroup(toggleGroup);
        tester.setToggleGroup(toggleGroup);
    }

    @FXML
    protected void onLoginButtonPressed() throws IOException {
        try {
            String username = usernameText.getText();
            String password = passwordText.getText();

            if (programmer.isSelected()) {
                service.loginProgrammer(username, password);
                ScreenController.switchScene((Stage) tester.getScene().getWindow(), "programmer_main.fxml", new ProgrammerMainController(service));
            } else if (tester.isSelected()) {
                service.loginTester(username, password);
                ScreenController.switchScene((Stage) tester.getScene().getWindow(), "tester_main.fxml", new TesterMainController(service));
            }
        } catch (RuntimeException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
            alert.show();
        }
    }
}
