package seedu.duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class MainWindow {

    @FXML private ScrollPane scrollPane;
    @FXML private VBox dialogContainer;
    @FXML private TextField userInput;
    @FXML private Button sendButton;

    private static final String HELP_MESSAGE = String.join("\n",
            "👋 Hello! I'm Chatnius.",
            "",
            "Here are the commands you can use:",
            "  todo <task>                 - Add a Todo",
            "  deadline <task> /by <time>  - Add a Deadline",
            "  event <task> /from <start> /to <end> - Add an Event",
            "  list                        - Show all tasks",
            "  mark <number>               - Mark a task as done",
            "  unmark <number>             - Unmark a task",
            "  delete <number>             - Delete a task",
            "  find <keyword>              - Search tasks",
            "  bye                         - Exit the chatbot"
    );


    private Chatnius chat;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/BossBaby.png"));
    private final Image dukeImage =
            new Image(this.getClass().getResourceAsStream("/images/Spongebob.png"));

    @FXML
    public void initialize() {
        // Auto-scroll to bottom when messages grow
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Make child nodes stretch to the VBox width inside the ScrollPane
        dialogContainer.prefWidthProperty()
                .bind(scrollPane.widthProperty().subtract(16)); // subtract scrollbar/padding
        scrollPane.setFitToWidth(true);
        dialogContainer.setFillWidth(true);
        dialogContainer.setSpacing(8);
        
        userInput.setOnAction(event -> handleUserInput());
    }


    public void setDuke(Chatnius d) {
        this.chat = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(HELP_MESSAGE, dukeImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input == null || input.isBlank()) return;

        String response = chat.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
