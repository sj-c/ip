package seedu.duke.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        /* print intro */
        System.out.println(" Hello! I'm Chatnius\n"
                + "____________________________________________________________\n");
    }
    public void showGoodbye() {
        /* print quit */
        System.out.println(" Byebye! Use me again PLEASE\n" +
                "____________________________________________________________\n");
    }
    public String readCommand() {
        System.out.print("What you need me for? ");
        return scanner.nextLine();
    }

    public void showLine() { System.out.println("____________________________________________________________"); }

    public void show(String msg) {
        System.out.println(msg);
    }


    public void showError(String msg) {
        System.out.println(msg);
    }

    public void close() {
        scanner.close();
    }

}
