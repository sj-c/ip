import java.util.Scanner;
import java.util.ArrayList;

public class Chatnius {

    private static ArrayList<String> ls = new ArrayList<>();
    private static int id = 1;

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

    public static String readBook() {
        ls.add("read book");
        return "____________________________________________________________\n" +
                " added: read book\n" +
                "____________________________________________________________\n";
    }

    public static String returnBook() {
        ls.add("return book");
        return "____________________________________________________________\n" +
                " added: return book\n" +
                "____________________________________________________________\n";
    }

    public static String list() {
        StringBuilder result = new StringBuilder();
        result.append("    ____________________________________________________________\n");

        if (ls.isEmpty()) {
            result.append("     No items in the list\n");
        } else {
            for (int i = 0; i < ls.size(); i++) {
                result.append("     ").append(i + 1).append(". ").append(ls.get(i)).append("\n");
            }
        }

        result.append("    ____________________________________________________________\n");
        return result.toString();
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

                case "read book":
                    System.out.print(readBook());
                    break;

                case "return book":
                    System.out.print(returnBook());
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