package bugs;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScreenController {

    public static void switchScene(Stage stage, String fxmlFile, AbstractController abstractController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource(fxmlFile));
        fxmlLoader.setControllerFactory(c -> {
            return abstractController;
        });

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

}
