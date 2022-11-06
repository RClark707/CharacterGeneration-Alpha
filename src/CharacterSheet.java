import java.util.ArrayList;
import java.util.Arrays;

public class CharacterSheet {
    private String charName;
    private String charRace;
    private String charClass;
    private String charSubclass;
    private String charBackground;
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
     fake news: private static final String[][] optimizedStatArray = {
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

    private final ArrayList<String> skillArray = new ArrayList<>();
    // Constructs a character sheet based on the name attribute
    public CharacterSheet(String charName) {
        this.charName = charName;
        // Do we need to initialize the statArray, doubtful, we also don't really need to initialize the Strings either...
    }

    public static CharacterSheet characterBuilder(String charName) {
        return new CharacterSheet(charName);
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

    // Lets us swap two different stat locations, assuming the input is a name of a stat, not an index
    public void configureStats(String swapFrom, String swapTo) {
        int indexFrom = -1;
        int indexTo = -1;

        swapFrom = ClassValidator.capitalizeFirst(swapFrom);
        swapTo = ClassValidator.capitalizeFirst(swapTo);

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

    public void newConfigureStats() {



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
        statName = ClassValidator.capitalizeFirst(statName);
        for (int i = 0; i < statNames.length; ++i) {
            if (statName.equals(statNames[i])) {
                index = i;
                break;
            }
        }
        // Does this need to be outside the for loop when I am not returning anything?
        statArray[index] = statArray[index] + racialModifier;
    }

    public void printStatArray() {
        System.out.println("Strength: " + statArray[0]);
        System.out.println("Dexterity: " + statArray[1]);
        System.out.println("Constitution: " + statArray[2]);
        System.out.println("Intelligence: " + statArray[3]);
        System.out.println("Wisdom: " + statArray[4]);
        System.out.println("Charisma: " + statArray[5]);
    }

    public void addSkill(String skillName) {
        skillArray.add(skillName);
    }

    public void createSkillArray(int numBonusSkills) {
        // We add two, because the character gets 2 from their background automatically
        numBonusSkills += 2;
        String[] skillList = RandCharacterGenerator.randSkillArray(numBonusSkills);
        skillArray.addAll(Arrays.asList(skillList));
        // I'm pretty sure I understand what this is doing, but I know that I can use a for loop to add each element that was generated to the actual skill list.
    }

    public void printSkillArray() {
        System.out.print("\nSkill Proficiencies: ");
        for (int i = 0; i < skillArray.size(); ++i) {
            if (i != skillArray.size() - 1) {
                System.out.print(skillArray.get(i) + ", ");
            } else {
                System.out.print(skillArray.get(i));
            }
        }
    }

    public void printClassSkillOptions() {
        if (!charClass.equals("Bard")) {
            String[] skillList = RandCharacterGenerator.getClassSkillArray(charClass);
            String numChoices = skillList[0];
            System.out.println("Choose " + numChoices + " additional skills from this list: ");
            for (int i = 0; i < skillList.length - 1; ++i) {
                skillList[i] = skillList[i + 1];
                String s = skillList[i];
                System.out.println((i + 1) + ". " + s);
            }
        }
    }

    public void printCharacterSheet() {
        System.out.println("\nHere is your character sheet!\n\n\n");
        System.out.println(charName + "           " + charClass);
        System.out.println(charBackground + "          " + charSubclass);
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
