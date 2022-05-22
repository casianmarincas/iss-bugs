package bugs;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Bug;
import service.Service;

import java.io.IOException;
import java.util.List;

public class MyBugsProgrammerController extends AbstractController {

    @FXML
    private FlowPane scrollPane;

    public MyBugsProgrammerController(Service service) {
        super(service);
    }

    @FXML
    protected void initialize() {
        loadBugs();
    }

    private void loadBugs() {
        List<Bug> bugList = service.getBugsForCurrentProgrammer();
        scrollPane.getChildren().clear();
        bugList.forEach(e -> {
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setId("vBoxBug");
            vbox.setAlignment(Pos.TOP_CENTER);

            HBox bugHBox = new HBox();
            bugHBox.setAlignment(Pos.CENTER);
            Text name = new Text(e.getName());
            name.setId("bugTitle");
            bugHBox.getChildren().add(name);


            vbox.getChildren().add(bugHBox);
            Button bugDetailsButton = new Button("Details");
            bugDetailsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    service.setCurrentBug(e);
                    try {
                        ScreenController.switchScene((Stage) scrollPane.getScene().getWindow(), "view_bug.fxml", new ViewBugController(service));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            vbox.getChildren().add(bugDetailsButton);

            scrollPane.getChildren().add(vbox);
        });
    }

    @FXML
    protected void onGoBackButtonPressed() throws IOException {
        ScreenController.switchScene((Stage) scrollPane.getScene().getWindow(), "programmer_main.fxml", new ProgrammerMainController(service));
    }
}
