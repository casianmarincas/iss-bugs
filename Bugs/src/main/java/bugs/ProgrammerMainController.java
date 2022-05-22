package bugs;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProgrammerMainController extends AbstractController {

    @FXML
    private Text nameText;

    @FXML
    private Text usernameText;

    @FXML
    private FlowPane scrollPane;

    @FXML
    private TextField search;

    public ProgrammerMainController(Service service) {
        super(service);
    }

    @FXML
    protected void initialize() {
        nameText.setText(service.getCurrentProgrammer().getFullName());
        usernameText.setText(service.getCurrentProgrammer().getUsername());
        search.textProperty().addListener(o -> loadBugs());
        loadBugs();
    }

    private void loadBugs() {
        Predicate<Bug> bugPredicate = e -> e.getName().toLowerCase(Locale.ROOT).contains(search.getText().toLowerCase(Locale.ROOT));

        List<Bug> bugList = service.getAllBugs().stream().filter(bugPredicate).collect(Collectors.toList());
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
                        ScreenController.switchScene((Stage) search.getScene().getWindow(), "view_bug.fxml", new ViewBugController(service));
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

    @FXML
    protected void onMyBugsButtonPressed() throws IOException {
        ScreenController.switchScene((Stage) usernameText.getScene().getWindow(), "my_bugs.fxml", new MyBugsProgrammerController(service));
    }
}
