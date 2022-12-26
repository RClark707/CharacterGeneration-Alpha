import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PartyMember {
    private String charName;
    private String charRace;
    private String charClass;
    private String charSubclass;
    private String charBackground;
    private int charLevel;
    private int charHitPoints;
    private int charHitDieType;
    private final int[] statArray = new int[6];
    // This array might be final, but we change it with each object, so I doubt it

    private final ArrayList<Weapon> weapons = new ArrayList<>();

    Scanner scan = new Scanner(System.in);
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

    private static final int[] hitDiceByClass = {
            8,
            12,
            8,
            8,
            8,
            10,
            8,
            10,
            10,
            8,
            6,
            8,
            6,
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
    public PartyMember(String charName) {
        this.charName = charName;
        charLevel = 1;
    }

    public PartyMember(String charName, String charBackground, int charLevel, int charHitDieType, int charHitPoints,
                    String charRace, String charClass, String charSubclass) {
        this.charName = charName;
        this.charBackground = charBackground;
        this.charLevel = charLevel;
        this.charHitDieType = charHitDieType;
        this.charHitPoints = charHitPoints;
        this.charRace = charRace;
        this.charClass = charClass;
        this.charSubclass = charSubclass;
    }

    public static PartyMember characterBuilder(String charName) {
        return new PartyMember(charName);
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
    public void configureStats() {
        int indexFrom = -1;
        int indexTo = -1;

        System.out.println("Which stat would you like to swap from?");
        String swapFrom = scan.nextLine();
        System.out.println("Which stat would you like to swap the score to?");
        String swapTo = scan.nextLine();
        swapFrom = InputChecker.shortToLong(swapFrom);
        swapTo = InputChecker.shortToLong(swapTo);
        // Find a way to make these both not run in unison, but to pick one
        swapFrom = ClassValidator.capitalizeFirst(swapFrom);
        swapTo = ClassValidator.capitalizeFirst(swapTo);
        if (ClassValidator.isValidStat(swapFrom) && ClassValidator.isValidStat(swapTo)) {
            for (int i = 0; i < statArray.length; ++i) {
                if (swapFrom.equals(statNames[i])) {
                    indexFrom = i;
                }
                if (swapTo.equals(statNames[i])) {
                    indexTo = i;
                }
                if ((indexFrom != -1 && indexTo != -1)) {
                    break;
                }
            }
            int tempHolder = statArray[indexTo];
            statArray[indexTo] = statArray[indexFrom];
            statArray[indexFrom] = tempHolder;
        } else {
            System.out.println("One or more of the stats you entered is either invalid or misspelled.");
        }
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
        // automatically makes Constitution a non-negative stat by taking the smallest stat in the array and swapping it to Constitution.
        if (statArray[2] < 10) {
            int minNum = statArray[0];
            for (int i = 0; i < statArray.length; ++i) {
                if (statArray[i] < minNum && statArray[i] >= 10) {
                    minNum = statArray[i];
                    indexFrom = i;
                }
            }
            tempHolder = statArray[2];
            statArray[2] = minNum;
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
        statArray[index] = statArray[index] + racialModifier;
    }

    public void setStatValue(int index, int abilityScoreValue) {
        statArray[index] = abilityScoreValue;
    }

    public void printStatArray() {
        System.out.printf("Strength: %d (%+d)\n", statArray[0],DiceRoller.computeAbilityModifier(statArray[0]));
        System.out.printf("Dexterity: %d (%+d)\n", statArray[1],DiceRoller.computeAbilityModifier(statArray[1]));
        System.out.printf("Constitution: %d (%+d)\n", statArray[2],DiceRoller.computeAbilityModifier(statArray[2]));
        System.out.printf("Intelligence: %d (%+d)\n", statArray[3],DiceRoller.computeAbilityModifier(statArray[3]));
        System.out.printf("Wisdom: %d (%+d)\n", statArray[4],DiceRoller.computeAbilityModifier(statArray[4]));
        System.out.printf("Charisma: %d (%+d)\n", statArray[5],DiceRoller.computeAbilityModifier(statArray[5]));
    }

    public void addSkill(String skillName) {
        skillArray.add(skillName);
    }

    public void createSkillArray(int numBonusSkills) {
        // We add two, because the character gets 2 from their background automatically.
        numBonusSkills += 2;
        // this line will guarantee Bards get their 3 skills from anywhere.
        skillArray.addAll(RandCharacterGenerator.createRandomSkillArray(numBonusSkills,false, charClass));
    }
    // these two methods are eerily similar, but for now I will keep them separate because they are used for either creating a character
    // as a user OR for the full random creation
    public void setSkillArray(ArrayList<String> skills) {
        skillArray.addAll(skills);
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
            assert skillList != null;
            String numChoices = skillList[0];
            System.out.println("Choose " + numChoices + " additional skills from this list (Enter the number): ");
            for (int i = 0; i < skillList.length - 1; ++i) {
                skillList[i] = skillList[i + 1];
                String s = skillList[i];
                System.out.println((i + 1) + ". " + s);
            }
        }
    }

    public void printAll() {
        System.out.println("\nHere is your character sheet!\n\n\n");
        System.out.printf("%-8s %s\n", charName + ", the", charBackground);
        System.out.println("Maximum Hitpoints: " + charHitPoints);
        System.out.printf("Level %2d %s\n",charLevel, charClass);
        System.out.printf("%-8s %s\n", charRace, charSubclass);
        // this may need to be adjusted to make the subclass and class line up better, but would require more printf practice
        printStatArray();
        printSkillArray();
        printWeapons();
        System.out.println("\n\n");
    }

    @Override
    public String toString() {
        return charName + "\n" +
                charBackground + "\n" +
                charLevel + "\n" +
                charHitDieType + "\n" +
                charHitPoints + "\n" +
                charRace + "\n" +
                charClass + "\n" +
                charSubclass + "\n" +
                statArray[0] + "," + statArray[1] + "," +  statArray[2] + "," +  statArray[3] + "," +  statArray[4] + "," +  statArray[5] + "\n" +
                skillArray + "\n";
    }

    // might make a separate method for printing the skillArray?

    public void printWeaponArray(PrintWriter printer) {
        for (Weapon w : weapons) {
            printer.print(w + ", ");
        }
    }

    public void nextLevel() {
        charLevel++;
        charHitPoints += DiceRoller.rollDice(charHitDieType,1) + abilityScoreModifier(2);
    }

    public void setCharHitPoints(int constitutionModifier) {
        charHitPoints = DiceRoller.rollDice(charHitDieType,charLevel-1);
        charHitPoints += charHitDieType + constitutionModifier;
    }

    public int abilityScoreModifier(int indexStat) {
        int abilityScoreModifier;
        if (statArray[indexStat] < 10) {
            abilityScoreModifier =  Math.floorDiv((statArray[indexStat]-10), 2);
        } else {
            abilityScoreModifier =  (statArray[indexStat]-10) / 2;
        }
        return abilityScoreModifier;
    }

    public void setCharHitDieType() {
        for (int i = 0; i < classArray.length; ++i) {
            if (charClass.equals(classArray[i])) {
                charHitDieType = hitDiceByClass[i];
                break;
            }
        }
    }

    // we use this method in the Party class, so I made it protected/package private
    protected void addWeapon(Weapon weapon) {
        weapons.add(weapon);
    }

    private void printWeapons() {
        System.out.print("\n");
        for (int i = 0; i < weapons.size(); ++i) {
            Weapon curWeapon = weapons.get(i);
            System.out.println((i+1) + ". " + ClassValidator.capitalizeFirst(curWeapon.getWeaponName()) + " (" +
                    curWeapon.getNumDamageDice() + "d" + curWeapon.getDamageDieSides() + ")");
        }
        System.out.print("\n");
    }

}
