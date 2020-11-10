import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class FiniteAutomaton {
    private List<String> states = new ArrayList<>();
    private List<String> alphabet = new ArrayList<>();
    private List<Transition> transitions = new ArrayList<>();
    private String initial_state;
    private List<String> final_states = new ArrayList<>();

    /**
     * constructor for the FiniteAutomaton class
     * @param filename - a string representing the name of the file from which it reads the finite automaton
     * the format in which the file should be written is:
     * set of states
     *                 letter = "a" | "b" | ... | "z"
     *                 digit = "0" | "1" | "2" | ... | "9"
     *                 state = letter { letter | digit | "_" }
     *                 list_of_states = state , {state}
     * initial state
     *                 initial_state = state
     * final states
     *                 final_states = state {state}
     * alphabet
     *                 special_char = "." | "," | "?" | "!" | ";" | ":" |"*" | "/" | "+" |"-" | "<" | ">" | "^" | "%" | "&"
     *                 alphabet = {letter | digit | special_char}
     * nb of transitions
     *                 nonzero_digit = "1" | "2" | ... | "9"
     *                 nb_of_transitions = nonzero_digit {digit}
     * transitions
     *                 transition = state , state , (letter | digit | special_char)
     *                 list_of_transitions = transition  {transition}
     */
    public FiniteAutomaton(String filename)
    {
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(filename));
            String[] line = null;

            //read states
            line = br.readLine().split("[ ]");
            Collections.addAll(states, line);

            //read initial state
            line = br.readLine().split("[ ]");
            initial_state = line[0];

            //read final states
            line = br.readLine().split("[ ]");
            Collections.addAll(final_states, line);

            //read alphabet
            line = br.readLine().split("[ ]");
            Collections.addAll(alphabet, line);

            //read number of transitions
            line = br.readLine().split("[ ]");
            int nb_transitions = Integer.parseInt(line[0]);

            //read transitions
            for(int i = 1; i <= nb_transitions; i++)
            {
                line = br.readLine().split("[ ]");
                Transition transition = new Transition(line[0], line[1], line[2]);
                transitions.add(transition);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (br != null)
                try {
                    br.close();
                }
                catch (IOException e)
                {
                    System.out.println("Error while closing the file " + e);
                }
        }
    }

    /**
     * this function checks if a given string is valid according to the finite automaton
     * @param token - a string that should be checked if valid according to the finite automaton
     * @return true if is valid, false otherwise
     */
    public boolean checkValidity(String token)
    {
        String list[] = token.split("");
        String current_state = initial_state;
        for (int i = 0; i < list.length; i++)
        {
            boolean ok = false;
            while (!ok)
            {
                for (int j = 0; j < transitions.size(); j++)
                {
                    if(transitions.get(j).getFirst_state().equals(current_state) &&
                            transitions.get(j).getValue().equals(list[i]))
                    {
                        ok = true;
                        current_state = transitions.get(j).getSecond_state();
                        break;
                    }
                    if(j == transitions.size()-1 && !ok) return false;
                }

            }
            if(i == list.length-1 && !final_states.contains(current_state)) return false;
        }
        return true;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(List<String> alphabet) {
        this.alphabet = alphabet;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getInitial_state() {
        return initial_state;
    }

    public void setInitial_state(String initial_state) {
        this.initial_state = initial_state;
    }

    public List<String> getFinal_states() {
        return final_states;
    }

    public void setFinal_states(List<String> final_states) {
        this.final_states = final_states;
    }

    @Override
    public String toString() {
        String s = "FiniteAutomaton \n" +
                "states = " + states +
                "\nalphabet = " + alphabet +
                "\ntransitions = \n";
        for(int i = 0; i < transitions.size(); i++)
            s += transitions.get(i).toString();
        s += "initial_state = " + initial_state  +
                "\nfinal_states = " + final_states;
        return s;
    }
}
