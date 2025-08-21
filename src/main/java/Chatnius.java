import java.util.Scanner;

public class Chatnius {

    public static String intro() {
        return "____________________________________________________________\n"
                + " Hello! I'm Chatnius\n"
                + " What you need me for?\n"
                + "____________________________________________________________\n";
    }

    public static String quit() {
        return "____________________________________________________________\n" +
                " Byebye! Use me again PLEASE\n" +
                "____________________________________________________________\n";
    }

    public static String list() {
        return "    ____________________________________________________________\n" +
                "     list\n" +
                "    ____________________________________________________________\n";
    }

    public static String blah() {
        return "    ____________________________________________________________\n" +
                "     blah\n" +
                "    ____________________________________________________________\n";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(intro());

        boolean running = true;

        while (running) {
            System.out.print("What you need me for? ");
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "list":
                    System.out.print(list());
                    break;

                case "blah":
                    System.out.print(blah());
                    break;

                case "bye":
                    System.out.print(quit());
                    running = false;
                    break;

                default:
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     I don't understand: " + input);
                    System.out.println("    ____________________________________________________________");
                    break;
            }

            if (running && !input.equals("bye")) {
                System.out.print("What you need more for bro? ");
            }
        }

        scanner.close();
    }
}