package seedu.duke.task;

public class ToDo extends Task {

    public ToDo(String name) {

        super(name);
    }

    /**
     * Creates the type code for deadline.
     *
     * @return the type code  If date and time is in the wrong format
     */
    @Override
    protected String typeCode() {

        return "T";
    }

    /**
     * Prints ToDo's details.
     *
     * @return the details of ToDo
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}