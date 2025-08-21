import java.util.Scanner;
import java.util.ArrayList;

public class Chatnius {

    private static ArrayList<Task> ls = new ArrayList<>();

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
        StringBuilder result = new StringBuilder();
        result.append("    ____________________________________________________________\n");

        if (ls.isEmpty()) {
            result.append("     No items in the list\n");
        } else {
            for (int i = 0; i < ls.size(); i++) {
                result.append("     ").append(i + 1).append(". ").append(ls.get(i).toString()).append("\n");
            }
        }

        result.append("    ____________________________________________________________\n");
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(intro());

        boolean running = true;

        while (running) {
            System.out.print("What you need me for? ");
            String input = scanner.nextLine().trim();
            String lowerInput = input.toLowerCase();

            if (lowerInput.equals("list")) {
                System.out.print(list());
            } else if (lowerInput.equals("bye")) {
                System.out.print(quit());
                running = false;
            } else if (input.startsWith("mark ")) {
                try {
                    int num = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (num >= 0 && num < ls.size()) {
                        ls.get(num).setDone(true);
                        System.out.println("____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("     " + (num + 1) + ". " + ls.get(num).toString());
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid number after 'mark'");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int num = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (num >= 0 && num < ls.size()) {
                        ls.get(num).setDone(false);
                        System.out.println("____________________________________________________________");
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("     " + (num + 1) + ". " + ls.get(num).toString());
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid number after 'unmark'");
                }
            } else {
                Task tsk = new Task(input);
                ls.add(tsk);
                System.out.print(tsk.returnTask());
            }

            if (running && !input.equalsIgnoreCase("bye")) {
                System.out.print("What you need more for bro? ");
            }
        }

        scanner.close();
    }
}