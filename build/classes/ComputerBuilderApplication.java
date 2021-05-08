import au.edu.uts.ap.javafx.*;
import javafx.stage.*;
import javafx.application.Application;
import model.ComputerBuilder;

public class ComputerBuilderApplication extends Application {
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(300);
        ViewLoader.showStage(new ComputerBuilder(), "/view/computerbuilder.fxml", "Guillermo's Computer Store", stage);
    }
}