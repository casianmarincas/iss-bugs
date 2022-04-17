package bugs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class ProgrammerMainController extends AbstractController {

    @FXML
    private Text nameText;

    @FXML
    private Text usernameText;

    public ProgrammerMainController(Service service) {
        super(service);
    }

    @FXML
    protected void initialize() {
        nameText.setText(service.getCurrentProgrammer().getFullName());
        usernameText.setText(service.getCurrentProgrammer().getUsername());
    }

    @FXML
    protected void onManageAccountButtonPressed() throws IOException {
        Stage manageAccountStage = new Stage();
        manageAccountStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("manage_account.fxml"));
        fxmlLoader.setControllerFactory(c -> {
            return new ManageAccountController(service);
        });

        Scene scene = new Scene(fxmlLoader.load());
        manageAccountStage.setScene(scene);
        manageAccountStage.show();
    }

    @FXML
    protected void onLogoutButtonPressed() throws IOException {
        service.logoutProgrammer();
        ScreenController.switchScene((Stage) usernameText.getScene().getWindow(), "login-view.fxml", new LoginController(service));
    }
}
