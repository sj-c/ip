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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(intro());

        boolean running = true;

        while (running) {
            System.out.print("What you need me for? ");
            String input = scanner.nextLine().trim();
            String lowerInput = input.toLowerCase();

            if (lowerInput.equals("list")) {
                System.out.print(Task.list());
            } else if (lowerInput.equals("bye")) {
                System.out.print(quit());
                running = false;
            } else if (input.startsWith("mark ")) {
                try {
                    int num = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (num >= 0 && num < Task.getLsSize()) {
                        System.out.print(Task.isDone(num));
                    } else {
                        System.out.println("Invalid task number!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid number after 'mark'");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int num = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (num >= 0 && num < Task.getLsSize()) {
                        System.out.print(Task.unDone(num));
                    } else {
                        System.out.println("Invalid task number!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please provide a valid number after 'unmark'");
                }
            } else if (input.startsWith("deadline ")) {
                String content = input.substring(9).trim();
                String[] parts = content.split(" /by ");

                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String by = parts[1].trim();
                    Task tsk = new Deadline(name, by);
                    System.out.print(tsk.outputInsert());
                } else {
                    System.out.println("Invalid deadline format! Use: deadline <task> /by <time>");
                }
            } else if (input.startsWith("event ")) {
                String content = input.substring(6).trim();
                String[] parts = content.split(" /from | /to ");

                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    Task tsk = new Event(name, from, to);
                    System.out.print(tsk.outputInsert());
                } else {
                    System.out.println("Invalid event format! Use: event <task> /from <start> /to <end>");
                }
            } else {
                Task tsk = new ToDo(input);
                System.out.print(tsk.returnTask());
            }

            if (running && !input.equalsIgnoreCase("bye")) {
                System.out.print("What you need more for bro? ");
            }
        }
        scanner.close();
    }
}