public final class Parser {

    public static Command parse(String line) throws DukeException {
        String s = (line == null) ? "" : line.trim();
        if (s.isEmpty()) throw new DukeException("Please enter a command.");

        // split first word vs the rest (args)
        int sp = s.indexOf(' ');
        String cmd = (sp == -1) ? s : s.substring(0, sp);
        String args = (sp == -1) ? "" : s.substring(sp + 1).trim();

        switch (cmd.toLowerCase()) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "todo":
                if (args.isEmpty()) throw new DukeException("Todo description cannot be empty!");
                return new AddTodoCommand(args);

            case "deadline": {
                if (args.isEmpty()) {
                    throw new DukeException("Invalid deadline format! Use: deadline <task> /by <time>");
                }
                // look for literal " /by "
                int pos = args.indexOf(" /by ");
                if (pos < 0) throw new DukeException("Invalid deadline format! Use: deadline <task> /by <time>");
                String name = args.substring(0, pos).trim();
                String by   = args.substring(pos + " /by ".length()).trim();
                if (name.isEmpty() || by.isEmpty()) {
                    throw new DukeException("Invalid deadline format! Use: deadline <task> /by <time>");
                }
                return new AddDeadlineCommand(name, by);
            }

            case "event": {
                if (args.isEmpty()) {
                    throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>");
                }
                // find " /from " then " /to " after it
                int posFrom = args.indexOf(" /from ");
                if (posFrom < 0) throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>");
                int posTo = args.indexOf(" /to ", posFrom + " /from ".length());
                if (posTo < 0) throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>");

                String name = args.substring(0, posFrom).trim();
                String from = args.substring(posFrom + " /from ".length(), posTo).trim();
                String to   = args.substring(posTo + " /to ".length()).trim();

                if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                    throw new DukeException("Invalid event format! Use: event <task> /from <start> /to <end>");
                }
                return new AddEventCommand(name, from, to);
            }

            case "mark":
                if (args.isEmpty()) throw new DukeException("Please provide a valid number after 'mark'");
                return new MarkCommand(parseIndex(args, "mark"));

            case "unmark":
                if (args.isEmpty()) throw new DukeException("Please provide a valid number after 'unmark'");
                return new UnmarkCommand(parseIndex(args, "unmark"));

            case "delete":
                if (args.isEmpty()) throw new DukeException("Please provide a valid number after 'delete'");
                return new DeleteCommand(parseIndex(args, "delete"));

            default:
                throw new DukeException(" idk about this function ");
        }
    }

    private static int parseIndex(String arg, String which) throws DukeException {
        try {
            return Integer.parseInt(arg.trim()); // 1-based index
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid number after '" + which + "'");
        }
    }
}
