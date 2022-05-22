package bugs;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Bug;
import service.Service;

import java.io.IOException;

public class ViewBugController extends AbstractController {

    @FXML
    private Text titleText;

    @FXML
    private TextFlow projectTextFlow;

    @FXML
    private TextFlow descriptionTextFlow;

    @FXML
    private TextFlow statusTextFlow;

    @FXML
    private Button actionButton;


    protected ViewBugController(Service service) {
        super(service);
    }

    @FXML
    protected void initialize() {
        Bug currentBug = service.getCurrentBug();
        titleText.setText(currentBug.getName());
        Text project = new Text(currentBug.getProject());
        Text description = new Text(currentBug.getDescription());
        Text status = new Text(currentBug.getStatus());

        project.setId("bugTitle");
        description.setId("bugTitle");
        status.setId("bugTitle");

        projectTextFlow.getChildren().add(project);
        descriptionTextFlow.getChildren().add(description);
        statusTextFlow.getChildren().add(status);

        if (service.getCurrentProgrammer() != null) {
            if (currentBug.getStatus().equals("unresolved")) {
                actionButton.setText("Assign this bug");
                actionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        service.assignCurrentBug();
                        try {
                            ScreenController.switchScene((Stage) projectTextFlow.getScene().getWindow(), "programmer_main.fxml", new ProgrammerMainController(service));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else if (service.currentProgrammerHasTheCurrentBug() && currentBug.getStatus().equals("assigned")) {
                actionButton.setText("Mark as resolved");
                actionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        service.markCurrentBugAsResolved();
                        try {
                            ScreenController.switchScene((Stage) projectTextFlow.getScene().getWindow(), "programmer_main.fxml", new ProgrammerMainController(service));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else if (currentBug.getStatus().equals("solved")) {
                actionButton.setVisible(false);
            } else {
                actionButton.setText("Already taken!");
            }
        } else {
            if (currentBug.getTester().equals(service.getCurrentTester()) && currentBug.getStatus().equals("solved")) {
                actionButton.setText("Approve");
                actionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        service.approveCurrentBug();
                        try {
                            ScreenController.switchScene((Stage) projectTextFlow.getScene().getWindow(), "tester_main.fxml", new TesterMainController(service));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                actionButton.setVisible(false);
            }

        }

    }

    @FXML
    protected void onGoBackButtonPressed() throws IOException {
        if (service.getCurrentProgrammer() != null) {
            ScreenController.switchScene((Stage) projectTextFlow.getScene().getWindow(), "programmer_main.fxml", new ProgrammerMainController(service));
        } else {
            ScreenController.switchScene((Stage) projectTextFlow.getScene().getWindow(), "tester_main.fxml", new TesterMainController(service));
        }
    }
}
