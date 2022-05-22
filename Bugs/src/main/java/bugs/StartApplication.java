package bugs;

import javafx.application.Application;
import javafx.stage.Stage;
import repository.BugsAssignmentsRepository;
import repository.BugsRepository;
import repository.ProgrammersRepository;
import repository.TestersRepository;
import service.*;

public class StartApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProgrammersRepository programmersRepository = new ProgrammersRepository();
        TestersRepository testersRepository = new TestersRepository();
        ProgrammersService programmersService = new ProgrammersService(programmersRepository);
        BugsRepository bugsRepository = new BugsRepository();
        BugsAssignmentsRepository bugsAssignmentsRepository = new BugsAssignmentsRepository();

        TestersService testersService = new TestersService(testersRepository);
        BugsService bugsService = new BugsService(bugsRepository);
        BugsAssignmentsService bugsAssignmentsService = new BugsAssignmentsService(bugsAssignmentsRepository);

        Service service = new Service(programmersService, testersService, bugsService, bugsAssignmentsService);
        Stage firstStage = new Stage();
        firstStage.setResizable(false);
        ScreenController.switchScene(firstStage, "login-view.fxml", new LoginController(service));
    }

    public static void main(String[] args) {
        launch();
    }

}
