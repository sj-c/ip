package seedu.duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML private Label dialog;
    @FXML private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);

        // === NEW: bubble readability & avatar size ===
        setSpacing(8);                       // gap between avatar and bubble
        dialog.setWrapText(true);            // wrap long messages
        dialog.setMaxWidth(420);             // cap bubble width (tweak as you like)

        displayPicture.setFitWidth(56);      // smaller, cleaner avatar
        displayPicture.setFitHeight(56);
        displayPicture.setPreserveRatio(true);
    }

    /** image LEFT, bubble RIGHT (Duke) */
    private void layoutLeft() {
        getChildren().setAll(displayPicture, dialog);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");  // your rounded-corner style for replies
    }

    /** bubble LEFT, image RIGHT (You) */
    private void layoutRight() {
        getChildren().setAll(dialog, displayPicture);
        setAlignment(Pos.TOP_RIGHT);
        dialog.getStyleClass().remove("reply-label");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.layoutRight();              // you on the RIGHT: bubble left, picture right
        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.layoutLeft();               // duke on the LEFT: picture left, bubble right
        return db;
    }
}

