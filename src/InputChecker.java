public class InputChecker {

    // Checks four common cases of saying "yes" to a response and returns true if they match
    public static boolean yes(String userInput) {
        return userInput.equals("Yes") || userInput.equals("yes") || userInput.equals("y") || userInput.equals("Y");
    }

    // Maybe an array would work for this, but it might be slower?
    // Currently, this method is only inverted, but I feel that it is necessary to keep it normal, since it may be of use later
    public static boolean no(String userInput) {
        return userInput.equals("No") || userInput.equals("no") || userInput.equals("n") || userInput.equals("N") || userInput.equals("None") || userInput.equals("none");
    }

    public static boolean random(String userInput) {
        return userInput.equals("random") || userInput.equals("rand") || userInput.equals("Random") || userInput.equals("Rand");
    }

    public static boolean fullRandom(String userInput) {
        return userInput.equals("Full random") || userInput.equals("full random") || userInput.equals("Full Random") || userInput.equals("full Random");
    }

    public static boolean options(String userInput) {
        return userInput.equals("Options") || userInput.equals("options");
    }

    public static String shortToLong(String userInput) {
        switch(userInput) {
            case "str", "STR", "Str" -> {
                return "Strength";
            }
            case "dex", "DEX", "Dex" -> {
                return "Dexterity";
            }
            case "con", "CON", "Con" -> {
                return "Constitution";
            }
            case "int", "INT", "Int" -> {
                return "Intelligence";
            }
            case "wis", "WIS", "Wis" -> {
                return "Wisdom";
            }
            case "cha", "CHA", "Cha" -> {
                return "Charisma";
            }
            default -> {
                return userInput;
            }
        }
    }
}
