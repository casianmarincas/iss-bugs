package bugs;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import service.Service;

import java.io.IOException;

public class AddBugController extends AbstractController {

    public AddBugController(Service service) {
        super(service);
    }

    @FXML
    private TextField projectText;

    @FXML
    private TextArea descriptionText;

    @FXML
    private TextField nameText;

    @FXML
    protected void onAddButtonPressed() throws IOException {
        String name = nameText.getText();
        String projectName = projectText.getText();
        String description = descriptionText.getText();

        service.addBug(name, projectName, description);
        ScreenController.switchScene((Stage) projectText.getScene().getWindow(), "tester_main.fxml", new TesterMainController(service));
    }

    @FXML
    protected void onGoBackButtonPressed() throws IOException {
        ScreenController.switchScene((Stage) projectText.getScene().getWindow(), "tester_main.fxml", new TesterMainController(service));
    }
}
