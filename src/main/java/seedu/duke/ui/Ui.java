package seedu.duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    // buffer to collect messages for GUI
    private final StringBuilder buffer = new StringBuilder();

    private void addToBuffer(String msg) {
        buffer.append(msg).append(System.lineSeparator());
    }

    /** Get all accumulated output and clear the buffer */
    public String flush() {
        String out = buffer.toString();
        buffer.setLength(0); // clear
        return out;
    }

    public void showWelcome() {
        String msg = " Hello! I'm Chatnius\n";
        System.out.println(msg);
        addToBuffer(msg);
    }

    public void showGoodbye() {
        String msg = " Byebye! Use me again PLEASE\n";
        System.out.println(msg);
        addToBuffer(msg);
    }

    public String readCommand() {
        System.out.print("What you need me for? ");
        return scanner.nextLine();
    }


    public void show(String msg) {
        System.out.println(msg);
        addToBuffer(msg);
    }

    public void showError(String msg) {
        System.out.println(msg);
        addToBuffer(msg);
    }

    public void close() {
        scanner.close();
    }
}
