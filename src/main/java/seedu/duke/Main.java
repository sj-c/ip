package seedu.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX entry point for the Chatnius application.
 * Loads the main window and injects a {@link Chatnius} instance.
 */
public class Main extends Application {

    /** Chatnius instance shared with the GUI. */
    private final Chatnius chat = new Chatnius("data/duke.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = fxmlLoader.load();

            Scene scene = new Scene(root);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setDuke(chat);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
