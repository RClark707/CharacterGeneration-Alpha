public class CharacterSheet {
    private String charName;
    private String charClass;
    private String charSubclass;
    private String charBackground;
    private String charRace;
    private final int[] statArray = new int[6];
    // This array might be final, but we change it with each object, so I doubt it

    private static final String[] classArray = {
            "Artificer",
            "Barbarian",
            "Bard",
            "Cleric",
            "Druid",
            "Fighter",
            "Monk",
            "Paladin",
            "Ranger",
            "Rogue",
            "Sorcerer",
            "Warlock",
            "Wizard",
    };

    /*
    private static final String[][] optimizedStatArray = {
            {"Artificer", "Intelligence"},
            {"Barbarian", "Strength",},
            {"Bard", "Charisma",},
            {"Cleric", "Wisdom",},
            {"Druid", "Wisdom",},
            {"Fighter", "Strength",},
            {"Monk", "Dexterity",},
            {"Paladin", "Strength",},
            {"Ranger", "Dexterity",},
            {"Rogue", "Dexterity",},
            {"Sorcerer", "Charisma",},
            {"Warlock", "Charisma",},
            {"Wizard", "Intelligence",},
    };

     */

    private static final int[] indexOfOptimizedStatsByClassArray = {
            3,
            0,
            5,
            4,
            4,
            0,
            1,
            0,
            1,
            1,
            5,
            5,
            4,
    };

    private final String[] statNames = {
            "Strength",
            "Dexterity",
            "Constitution",
            "Intelligence",
            "Wisdom",
            "Charisma",
    };



    // Constructs a character sheet based on the name attribute
    public CharacterSheet(String charName) {
        this.charName = charName;
    }

    public void changeCharName(String charName) {
        this.charName = charName;
    }

    public String getCharName() {
        return charName;
    }

    // validates the input and sets the class attribute
    public void setCharClass(String charClass) {
        // These checks are here in case the class is changed later on (I know we check in creation)
            if (ClassValidator.isValidClass(charClass)) {
                if (!(Character.isUpperCase(charClass.charAt(0)))) {
                    charClass = charClass.substring(0, 1).toUpperCase() + charClass.substring(1);
                }
                this.charClass = charClass;
            }
    }

    // Returns the current value of the class attribute for the object
    public String getCharClass()  {
        return charClass;
    }

    // Will validate and set the value for the subclass attribute for the object
    public void setCharSubclass(String charSubclass) {
        if (ClassValidator.isValidSubclass(charSubclass, charClass)) {
            this.charSubclass = charSubclass;
        }
    }

    // Returns the current value of the subclass attribute for the object
    public String getCharSubclass() {
        return charSubclass;
    }

    // This sets and gets the array of six stats after rolling them
    // If you want a threshold you enter a number between 70 and 100
    public void rollStats(int threshold) {
        int total;
        if (threshold <= 100 && threshold >= 70) {
            do {
                total = 0;
                for (int i = 0; i < statArray.length; ++i) {
                    statArray[i] = DiceRoller.rollStat();
                    total += statArray[i];
                }
            } while (total <= threshold);
        } else {
            for (int i = 0; i < statArray.length; ++i) {
                    statArray[i] = DiceRoller.rollStat();
                }
            }
    }

    // Returns the score (numerical value) for the given stat
    public int retrieveStat(int index) {
        return statArray[index];
    }

    // Returns the given stat score (numerical value)
    public int retrieveStat(String statName) {
        int index = -1;

        for (int i = 0; i < statNames.length; ++i) {
            if (statName.equals(statNames[i])) {
                index = i;
                break;
            }
        }
        // If there was no matching stat Name, we'll just say the score is -1 so that we know there was a problem
        if (index == -1) {
            return index;
        }
        return statArray[index];
    }

    // Lets us swap two different stat locations, assuming the input is a name of a stat, not an index
    public void configureStats(String swapFrom, String swapTo) {
        int indexFrom = -1;
        int indexTo = -1;

        for (int i = 0; i < statNames.length; ++i) {
            if (swapFrom.equals(statNames[i])) {
                indexFrom = i;
            }
            if (swapTo.equals(statNames[i])) {
                indexTo = i;
            }
            // Could also use a while loop around the above if-statements, and it will check this condition too
            if ((indexFrom != -1) && (indexTo != -1)) {
                 break;
            }
        }
        // Swaps the two locations, and holds one value so that they can switch at the "same" time
        int tempHolder = statArray[indexTo];
        statArray[indexTo] = statArray[indexFrom];
        statArray[indexFrom] = tempHolder;
    }

    public void optimizeStats(String charClass) {
        int maximumStatVal = statArray[0];
        int indexFrom = 0;
        int indexTo = 0;

        for (int i = 0; i < statArray.length; ++i) {
            if (statArray[i] > maximumStatVal) {
                maximumStatVal = statArray[i];
                indexFrom = i;
            }
        }

        for (int i = 0; i < classArray.length; ++i) {
            if (charClass.equals(classArray[i])) {
                indexTo = indexOfOptimizedStatsByClassArray[i];
                break;
            }
        }

        int tempHolder = statArray[indexTo];
        statArray[indexTo] = statArray[indexFrom];
        statArray[indexFrom] = tempHolder;
    }

    // Lets us just take in the index rather than stat name for the above function, kind of optional
    public void configureStats(int indexFrom, int indexTo) {
        if ( ((indexFrom >= 0 ) && (indexTo >= 0)) && (indexTo != indexFrom) ) {
            int tempHolder = statArray[indexTo];
            statArray[indexTo] = statArray[indexFrom];
            statArray[indexFrom] = tempHolder;
        }
    }

    public void applyRacialModifier(int racialModifier, String statName) {
        int index = 0;
        for (int i = 0; i < statNames.length; ++i) {
            if (statName.equals(statNames[i])) {
                index = i;
                break;
            }
        }
        // Does this need to be outside the for loop when I am not returning anything?
        statArray[index] = statArray[index] + racialModifier;
    }

    // Sets the character's background, does not require any checks for validity
    public void setCharBackground(String charBackground) {
        this.charBackground = charBackground;
    }

    // Returns the value of the background attribute
    public String getCharBackground() {
        return charBackground;
    }

    public String getCharRace() {
        return charRace;
    }

    public void setCharRace(String charRace) {
        this.charRace = charRace;
    }
}
