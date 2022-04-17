package bugs;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import service.Service;

public class ManageAccountController extends AbstractController {

    @FXML
    public PasswordField oldPasswordText;

    @FXML
    public PasswordField newPasswordText;

    @FXML
    public PasswordField newPasswordAgainText;

    protected ManageAccountController(Service service) {
        super(service);
    }


    @FXML
    protected void onSaveButtonPressed() {
        String oldPassword = oldPasswordText.getText();
        String newPassword = newPasswordText.getText();
        String newPasswordAgain = newPasswordAgainText.getText();

        if (! newPassword.equals(newPasswordAgain)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords do not match");
            alert.show();
        } else {
            try {
                service.changePassword(oldPassword, newPassword);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changed!");
                alert.show();
                ((Stage) newPasswordText.getScene().getWindow()).close();
            } catch (RuntimeException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                alert.show();
            }
        }


    }
}
