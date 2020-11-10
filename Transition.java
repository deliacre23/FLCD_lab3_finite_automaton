public class Transition {
    private String first_state;
    private String second_state;
    private String value;

    /**
     * constructor for the Transition class
     * @param first_state - a string that represents the first state of a transition
     * @param second_state - a string that represents the second state of a transition
     * @param value -  a string that represents the value of a transition
     */
    public Transition(String first_state, String second_state, String value) {
        this.first_state = first_state;
        this.second_state = second_state;
        this.value = value;
    }

    public String getFirst_state() {
        return first_state;
    }

    public void setFirst_state(String first_state) {
        this.first_state = first_state;
    }

    public String getSecond_state() {
        return second_state;
    }

    public void setSecond_state(String second_state) {
        this.second_state = second_state;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return first_state + "---" + value + "-->" + second_state + "\n";
    }
}
