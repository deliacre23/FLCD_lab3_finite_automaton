import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FiniteAutomaton finiteAutomaton = new FiniteAutomaton("src/identifier.in");

        System.out.println(finiteAutomaton.toString());

        Scanner console = new Scanner(System.in);
        System.out.println("Give token to check or ~ to stop");
        while(true)
        {
            String token = console.next();
            if(token.equals("~")) break;
            if(finiteAutomaton.checkValidity(token)) System.out.println("Valid");
            else System.out.println("Not valid");

        }
    }
}
