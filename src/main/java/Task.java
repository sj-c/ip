class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String toString() {
        if (this.done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    public void setDone(boolean val) {
        this.done = val;
    }

    public String returnTask() {
        return "    ____________________________________________________________\n" +
                " added: " + name + "\n" +
                "    ____________________________________________________________\n";
    }
}