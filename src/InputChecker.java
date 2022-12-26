public class InputChecker {

    // Checks four common cases of saying "yes" to a response and returns true if they match
    public static boolean yes(String userInput) {
        return userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y");
    }

    // Maybe an array would work for this, but it might be slower?
    // Currently, this method is only inverted, but I feel that it is necessary to keep it normal, since it may be of use later
    public static boolean no(String userInput) {
        return userInput.equalsIgnoreCase("no") || userInput.equalsIgnoreCase("n");
    }

    public static boolean random(String userInput) {
        return userInput.equalsIgnoreCase("random") || userInput.equalsIgnoreCase("rand");
    }

    public static boolean fullRandom(String userInput) {
        return userInput.equalsIgnoreCase("full random") || userInput.equalsIgnoreCase("fullrandom");
    }

    public static boolean options(String userInput) {
        return userInput.equalsIgnoreCase("options") || userInput.equalsIgnoreCase("option");
    }

    public static String shortToLong(String userInput) {
        switch(userInput.toLowerCase()) {
            case "str" -> {
                return "Strength";
            }
            case "dex" -> {
                return "Dexterity";
            }
            case "con" -> {
                return "Constitution";
            }
            case "int" -> {
                return "Intelligence";
            }
            case "wis" -> {
                return "Wisdom";
            }
            case "cha" -> {
                return "Charisma";
            }
            default -> {
                return userInput;
            }
        }
    }
}
