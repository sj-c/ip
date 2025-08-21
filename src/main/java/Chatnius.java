public class Chatnius {

    public static String intro() {
        return "____________________________________________________________\n"
                + " Hello! I'm Chatnius\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
    }

    public static String quit() {
        return " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
    }

    public static void main(String[] args) {
        System.out.print(Chatnius.intro());
        System.out.print(Chatnius.quit());
    }
}
