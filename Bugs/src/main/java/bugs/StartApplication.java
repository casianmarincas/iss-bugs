package bugs;

import javafx.application.Application;
import javafx.stage.Stage;
import repository.ProgrammersRepository;
import repository.TestersRepository;
import service.ProgrammersService;
import service.Service;
import service.TestersService;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class StartApplication extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Properties properties = new Properties();
        try {
            Reader r = new FileReader("bd.config");
            properties.load(r);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProgrammersRepository programmersRepository = new ProgrammersRepository(properties);
        TestersRepository testersRepository = new TestersRepository(properties);

        ProgrammersService programmersService = new ProgrammersService(programmersRepository);
        TestersService testersService = new TestersService(testersRepository);

        Service service = new Service(programmersService, testersService);
        Stage firstStage = new Stage();
        firstStage.setResizable(false);
        ScreenController.switchScene(firstStage, "login-view.fxml", new LoginController(service));
    }

    public static void main(String[] args) {
        launch();
    }

}
