public class InputChecker {

    // Checks four common cases of saying "yes" to a response and returns true if they match
    public static boolean yes(String userInput) {
        return userInput.equals("Yes") || userInput.equals("yes") || userInput.equals("y") || userInput.equals("Y");
    }
}
