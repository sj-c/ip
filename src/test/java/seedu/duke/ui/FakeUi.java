package seedu.duke.ui;

public class FakeUi extends Ui {
    private String lastShown = "";

    @Override
    public void show(String s) {
        lastShown = s;
    }

    public String getLastShown() {
        return lastShown;
    }
}
