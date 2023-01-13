import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Party {
// Going to match the Spell Book class, and make everything be based off of methods, rather than cluttering the MAIN
    ArrayList<PartyMember> party = new ArrayList<>();
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();

    public void addCharacter(String charName) {
        party.add(PartyMember.characterBuilder(charName));
    }

    public PartyMember returnCharacter(String charName) {
        for (PartyMember curCharacter : party) {
            if (charName.equals(curCharacter.getCharName())) {
                return curCharacter;
            }
        }
        return null;
    }

    public void createCharacter(PartyMember characterToCreate) {
        // This method will use returnCharacter method in order to input a PartyMember object based on user input
        // We are not combining them for reasons yet to be decided
        for (PartyMember character : party) {
            if (characterToCreate.equals(character)) {
                // Step 1: Race
                while (character.getCharRace() == null) {
                    System.out.println("\nWhat is your Race?");
                    String race = scan.nextLine().toLowerCase();
                    if (InputChecker.random(race)) {
                        race = RandCharacterGenerator.randRace();
                        character.setCharRace(race);
                    } else if (InputChecker.options(race)) {
                        RandCharacterGenerator.printOptions("Race",null);
                        System.out.println("What is your Race?");
                        race = scan.nextLine().toLowerCase();
                        if (InputChecker.random(race)) {
                          race = RandCharacterGenerator.randRace();
                        }
                        race = ClassValidator.capitalizeFirst(race);
                        character.setCharRace(race);
                    } else {
                        race = ClassValidator.capitalizeFirst(race);
                        character.setCharRace(race);
                    }
                } // end of Race
                System.out.println("You are now a " + character.getCharRace());
                // Step 2: Class
                while (character.getCharClass() == null) {
                    System.out.println("What is your Class?");
                    String charClass = scan.nextLine().toLowerCase();
                    charClass = ClassValidator.capitalizeFirst(charClass);
                    if (InputChecker.random(charClass)) {
                        charClass = RandCharacterGenerator.randCharClass();
                        character.setCharClass(charClass);
                        character.setCharHitDieType();

                    } else if (ClassValidator.isValidClass(charClass)) {
                        character.setCharClass(charClass);
                        character.setCharHitDieType();
                    } else if (InputChecker.options(charClass)) {
                        RandCharacterGenerator.printOptions("Class",null);
                        System.out.println("What is your Class?");
                        charClass = scan.nextLine();
                        charClass = ClassValidator.capitalizeFirst(charClass);
                        if (InputChecker.random(charClass)) {
                            charClass = RandCharacterGenerator.randCharClass();
                            character.setCharClass(charClass);
                        } else if (ClassValidator.isValidClass(charClass)) {
                            character.setCharClass(charClass);
                            character.setCharHitDieType();
                        } else {
                            System.out.println("Sorry, you either entered an unknown Class, or misspelled it.");
                        }
                    } else {
                        System.out.println("Sorry, you either entered an unknown Class, or misspelled it.");
                    }
                } // end of Class
                System.out.println("You are now a " + character.getCharRace() + " " + character.getCharClass());
                // Step 3: Subclass
                while (character.getCharSubclass() == null) {
                    System.out.println("What is your Subclass?");
                    String charSubclass = scan.nextLine().toLowerCase();
                    charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                    if (InputChecker.random(charSubclass)) {
                        charSubclass = RandCharacterGenerator.randCharSubclass(character.getCharClass());
                        charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                        character.setCharSubclass(charSubclass);
                    } else if (ClassValidator.isValidSubclass(charSubclass, character.getCharClass())) {
                        charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                        character.setCharSubclass(charSubclass);
                    } else if (InputChecker.options(charSubclass)) {
                        RandCharacterGenerator.printOptions("Subclass",character.getCharClass());
                        System.out.println("What is your Subclass?");
                        charSubclass = scan.nextLine().toLowerCase();
                        charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                        if (InputChecker.random(charSubclass)) {
                            charSubclass = RandCharacterGenerator.randCharSubclass(character.getCharClass());
                            character.setCharSubclass(charSubclass);
                        } else if (ClassValidator.isValidSubclass(charSubclass,character.getCharClass())) {
                            charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                            character.setCharClass(charSubclass);
                        } else {
                            System.out.println("Sorry, you either entered an unknown Subclass, or misspelled it.");
                        }
                    } else {
                        System.out.println("Sorry, you either entered an unknown Subclass, or misspelled it.");
                    }
                } // end of Subclass
                System.out.println("You are now a " + character.getCharRace() + " " + character.getCharSubclass() + " " + character.getCharClass());
                // Step 4: Background
                while (character.getCharBackground() == null) {
                    System.out.println("What is your Background?");
                    String background = scan.nextLine().toLowerCase();
                    background = ClassValidator.capitalizeFirst(background);
                    background = ClassValidator.capitalizeFirst(background);
                    if (InputChecker.random(background)) {
                        background = RandCharacterGenerator.randBackground();
                        character.setCharBackground(background);

                    } else if (InputChecker.options(background)) {
                        RandCharacterGenerator.printOptions("Background",null);
                        System.out.println("What is your Background?");
                        background = scan.nextLine().toLowerCase();
                        if (InputChecker.random(background)) {
                            background = RandCharacterGenerator.randBackground();
                            character.setCharBackground(background);
                        } else {
                            background = ClassValidator.capitalizeFirst(background);
                            character.setCharBackground(background);
                        }
                    } else {
                        background = ClassValidator.capitalizeFirst(background);
                        character.setCharBackground(background);
                    }
                } // end of Background
                System.out.println("You were a " + character.getCharBackground());
                // Step 5: Skills! :)
                // Not using a while loop here, for no particular reason
                // If a character gets more proficiencies from their race or something, ou can get more random ones here
                System.out.println("How many additional skills do you have (outside of your class & background)");
                System.out.println("This is often just zero. (Enter a number)");
                int numBonus = scan.nextInt();

                // Already knows we have 2 bonus from the background
                character.buildProficiencies(numBonus,false);
                System.out.print("\nHere are the skills you gained from your background: ");
                character.printSkillProficiencies();
                System.out.print("\n\n");

                // This grabs the array of extra skills you can pick from based on your class
                // For bards, it is just three random ones, and they don't get a choice >:)
                if (!character.getCharClass().equals("Bard")) {
                    String[] classSkills = RandCharacterGenerator.getClassSkillArray(character.getCharClass());
                    // the following method checks to see if we are a bard (if so, we skip)
                    character.printClassSkillOptions();
                    System.out.print("\nFirst choice (Enter a number): ");
                    int choice1 = scan.nextInt();
                    // Adds to our master list within the character object
                    // TODO: make a list of their choices and add all at once?
                    assert classSkills != null;
                    character.addProficiency(classSkills[choice1]);
                    System.out.print("\nSecond choice: ");
                    int choice2 = scan.nextInt();
                    character.addProficiency(classSkills[choice2]);
                    // For now these are hard coded to be Ranger and Rogue, since they are the only classes that get more than two choices for skill proficiencies
                    if (character.getCharClass().equals("Ranger") || character.getCharClass().equals("Rogue")) {
                        System.out.print("\nThird choice: ");
                        int choice3 = scan.nextInt();
                        character.addProficiency(classSkills[choice3]);
                        if (character.getCharClass().equals("Rogue")) {
                            System.out.print("\nFourth choice: ");
                            int choice4 = scan.nextInt();
                            character.addProficiency(classSkills[choice4]);
                        }
                    }
                }

                // Step 6: Stats
                while (character.retrieveStat(0) == 0) {
                    System.out.println("\nEnter the minimum threshold you want when rolling stats.");
                    System.out.println("Valid threshold options are integers 70-100 inclusive, and any other number for no threshold.");
                    int threshold = scan.nextInt();
                    scan.nextLine();
                    character.rollStats(threshold);
                    character.optimizeStats(character.getCharClass());

                    // This does while true loop is kind of weird, but it basically will keep asking you to enter a stat to swap until you skip or say no
                    do {
                        System.out.println("\nHere are your ability scores:");
                        character.printStatArray();

                        // Would you like to swap stats, if yes, execute method
                        System.out.println("\nWould you like to swap any ability scores around?");
                        String swapScores = scan.nextLine();
                        if (InputChecker.yes(swapScores)) {
                            character.configureStats();
                        } else {
                            break;
                        }

                    } while (true);

                    // Applies Racial Modifiers according to the new 5e changes. +2 to one, and +1 to another
                    // It will not tell you if you misspelled the stat, but you will get to retry entering it.
                    String preference;
                    boolean notUnique;
                    do {
                        System.out.println("\nWhich stat would you like to apply a +2 Racial Modifier to?");
                        preference = scan.nextLine().toLowerCase();
                        preference = InputChecker.shortToLong(preference);
                    } while (!ClassValidator.isValidStat(preference));
                    character.applyRacialModifier(2, preference);
                    do {
                        notUnique = false;
                        String previousPref = preference;
                        System.out.println("\nWhich stat would you like to apply a +1 Racial modifier to?");
                        preference = scan.nextLine().toLowerCase();
                        preference = InputChecker.shortToLong(preference);
                        if (previousPref.equals(preference)) {
                            notUnique = true;
                            System.out.println("You already boosted that stat... Please pick another one.");
                        }
                    } while (!ClassValidator.isValidStat(preference) || notUnique);
                    character.applyRacialModifier(1, preference);
                }
                // Rolls hit points now that we have sufficiently manipulated the stats
                character.setCharHitPoints(character.abilityScoreModifier(2));
                // make and add weapons
                makeWeapon(character,scan);
                // Print out the Character Sheet
                character.printAll();
                break;
            } // End of if statement once we found the correct character
        } // End of for loop to check which character we want to configure
    } // End of method

    public String printParty() {
        // prints the names of each spell in the book
        String partyMembers = "";
        for (int i = 0; i < party.size(); ++i) {
            PartyMember curPartyMember = party.get(i);
            if (i != party.size() - 1) {
                partyMembers = partyMembers.concat(curPartyMember.getCharName() + ", ");
            } else {
                partyMembers = partyMembers.concat(curPartyMember.getCharName());
            }
        }
        return partyMembers;
    }

    public void printPartyOptions() {
        for (int i = 0; i < party.size(); ++i) {
            PartyMember p = party.get(i);
            System.out.println((i+1) + ". " + p.getCharName());
        }
    }

    public int getPartySize() {
        return party.size();
    }

    public PartyMember getPartyMember(int partyIndex) {
        return party.get(partyIndex);
    }

    public void saveParty(String fileToSaveTo) throws FileNotFoundException {
        // alternatively can use the FileOutputStream in conjunction with the print writer
        try (PrintWriter printer = new PrintWriter(fileToSaveTo)) {
            for (PartyMember p : party) {
                printer.print(p.toString());
                p.printProficienciesToFile(printer);
                // weapons are saved as @weaponDamageDie, @weaponName, @magicalModifier, @finesse
                p.printWeaponArrayToFile(printer);
                p.printSpellBookToFile(printer);
                printer.println("\n------");
            }
        }
    }

    public int countLines(String fileToSaveTo) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(fileToSaveTo));
        int numLines = 0;
        while (scan.hasNextLine()) {
            scan.nextLine();
            numLines++;
        }
        return numLines;
    }

    public void initializeParty(String fileToSaveTo) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(fileToSaveTo));
        Scanner defaultScanner = new Scanner(new FileInputStream("SpellFile"));
        do {
            if (scan.hasNextLine()) {
                String name = scan.nextLine();
                String background = scan.nextLine();
                int level = Integer.parseInt(scan.nextLine());
                int hitDieType = Integer.parseInt(scan.nextLine());
                int hitPoints = Integer.parseInt(scan.nextLine());
                String race = scan.nextLine();
                String charClass = scan.nextLine();
                String subclass = scan.nextLine();
                party.add(new PartyMember(name,background,level,hitDieType,hitPoints,race,charClass,subclass));
                PartyMember curCharacter = returnCharacter(name);
                String[] statValues = scan.nextLine().split(",");
                for (int i = 0; i < 6; ++i) {
                    curCharacter.setStatValue(i, Integer.parseInt(statValues[i]));
                }
                String skillProficiencies = scan.nextLine();
                String[] skills = skillProficiencies.split(", ");
                for (String skill : skills) {
                    curCharacter.addProficiency(skill);
                }
                String[] weaponArray = scan.nextLine().split(", ");
                // might be easier if the weapons were saved as a two-dimensional array
                for (int i = 0; i < weaponArray.length; i = i + 4) {
                    curCharacter.addWeapon(new Weapon(Integer.parseInt(weaponArray[i]), weaponArray[i+1], Integer.parseInt(weaponArray[i+2]), Boolean.getBoolean(weaponArray[i+3])));
                }
                // checks to see if there are spells to be loaded. An "&" will be present in the line if spells were saved to the character
                String spellLine = scan.nextLine();
                if (spellLine.contains(" & ")) {
                    String[] spellArray = spellLine.split(" & ");
                    for (String s : spellArray) {
                        String[] curSpell = s.split(", ");
                        // matches each element of the curSpell array with the part of the constructor required for a spell, then adds the spell to the character's individual spellbook.
                        if (curSpell.length < 2) {
                            break;
                        }
                        curCharacter.addSpell(new Spell(curSpell[0], Integer.parseInt(curSpell[1]), Integer.parseInt(curSpell[2]), Integer.parseInt(curSpell[3]), Integer.parseInt(curSpell[4]), Integer.parseInt(curSpell[5]),
                                Integer.parseInt(curSpell[6]), Boolean.parseBoolean(curSpell[7]), curSpell[8], Boolean.parseBoolean(curSpell[9]), curSpell[10], Boolean.parseBoolean(curSpell[11]), Integer.parseInt(curSpell[12]),
                                Integer.parseInt(curSpell[13]), Integer.parseInt(curSpell[14]), Boolean.parseBoolean(curSpell[15]), curSpell[16], curSpell[17]));
                    }
                }
                scan.nextLine();
            }
        } while (scan.hasNextLine());
        scan.close();
        defaultScanner.close();
    }

    public void clear() {party.clear();}

    public void removePartyMember(int index) {party.remove(index);}

    public void fullRandom() {
        boolean notUniqueName;
        String randName;
        do {
            notUniqueName = false;
            randName = RandCharacterGenerator.randName();
            for (PartyMember p : party) {
                if (p.getCharName().equals(randName)) {
                    notUniqueName = true;
                    break;
                }
            }
        } while (notUniqueName);
        party.add(PartyMember.characterBuilder(randName));
        for (PartyMember character : party) {
            if (randName.equals(character.getCharName())) {
                character.setCharRace(RandCharacterGenerator.randRace());
                character.setCharClass(RandCharacterGenerator.randCharClass());
                character.setCharSubclass(RandCharacterGenerator.randCharSubclass(character.getCharClass()));
                character.setCharHitDieType();
                character.setCharBackground(RandCharacterGenerator.randBackground());
                character.rollStats(rand.nextInt(70,100));
                String randStat = RandCharacterGenerator.randStat();
                // Write a new method to optimize racial modifiers, perhaps based on ability modifier too, but mostly on class choice
                character.applyRacialModifier(2,randStat);
                String secondStat;
                do {
                    secondStat = RandCharacterGenerator.randStat();
                } while (randStat.equals(secondStat));
                character.applyRacialModifier(1,secondStat);
                character.optimizeStats(character.getCharClass());
                character.setCharHitPoints(character.abilityScoreModifier(2));
                // start of skills
                character.buildProficiencies(0,true);
                character.addWeapon( RandCharacterGenerator.getClassWeaponArray(character.getCharClass())[rand.nextInt(RandCharacterGenerator.getClassWeaponArray(character.getCharClass()).length)] );
                character.addWeapon( RandCharacterGenerator.getClassWeaponArray(character.getCharClass())[rand.nextInt(RandCharacterGenerator.getClassWeaponArray(character.getCharClass()).length)] );
                // add an array of spells to choose from randomly or purposefully
                character.addSpell( RandCharacterGenerator.zeroLevel[0]);
                character.addSpell( RandCharacterGenerator.firstLevel[0]);
                character.printAll();
            }
        }
    }

    public void makeWeapon(PartyMember curChar, Scanner scan) {
        boolean finesse = false;
        int magicalModifier = 0;
        System.out.println("How many weapons does your character wield");
        int weaponCount = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < weaponCount; ++i) {
            System.out.println("What is " + curChar.getCharName() + "'s weapon?");
            String weaponName = scan.nextLine();
            if (InputChecker.options(weaponName)) {
                RandCharacterGenerator.printOptions("Weapons",curChar.getCharClass());
                System.out.println("What is " + curChar.getCharName() + "'s weapon?");
                weaponName = scan.nextLine();
            }
            boolean preset = false;
            for (int j = 0; j < RandCharacterGenerator.allWeapons.length; ++j) {
                if (weaponName.equalsIgnoreCase(RandCharacterGenerator.allWeapons[j].weaponName)) {
                    curChar.addWeapon(RandCharacterGenerator.allWeapons[j]);
                    preset = true;
                    break;
                }
            }
            if (!preset) {
                System.out.println("How many sides do the damage dice of your weapon have?");
                int weaponDice = scan.nextInt();
                scan.nextLine();
                System.out.println("Is a " + weaponName + " a finesse weapon (Does it use Dexterity instead of Strength)?");
                if (InputChecker.yes(scan.nextLine())) {
                    finesse = true;
                }
                System.out.println("Is this weapon magical?");
                if (InputChecker.yes(scan.nextLine())) {
                    System.out.println("Does this weapon give you a:");
                    System.out.println("1. +1 Bonus");
                    System.out.println("2. +2 Bonus");
                    System.out.println("3. +3 Bonus");
                    switch (scan.nextInt()) {
                        case 2 -> magicalModifier = 2;
                        case 3 -> magicalModifier = 3;
                        default -> magicalModifier = 1;
                    }
                    scan.nextLine();
                }
                curChar.addWeapon(new Weapon(weaponDice, weaponName, magicalModifier, finesse));
            }
        }
    }

    public boolean playCharacter(PartyMember curChar) {
        curChar.printAll();
        boolean keepPlaying;
        boolean newCharacter = false;
        do {
            keepPlaying = false;
            int numMainMenuOptions = 5;
            int option;
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Attack with Weapons");
            System.out.println("2. Roll an Ability Check");
            System.out.println("3. Roll a Saving Throw");
            if (curChar.spellBook.size() != 0) {
                System.out.println("4. Cast a Spell");
                numMainMenuOptions = 6;
            }
            System.out.println((numMainMenuOptions - 1) + ". Pick Another Character");
            System.out.println(numMainMenuOptions + ". Exit to Main Menu");
            option = Integer.parseInt(scan.nextLine());
            switch (option) {
                case 1 -> {
                    boolean advantage = false;
                    boolean disadvantage = false;
                    System.out.println("\nWhich weapon would you like to attack with? (Enter a number)");
                    curChar.printWeaponOptions();
                    System.out.println((curChar.weapons.size() + 1) + ". Go back");
                    option = Integer.parseInt(scan.nextLine());
                    if (option <= curChar.weapons.size()) {
                        System.out.println("How many attacks are you making?");
                        int numAttacks = Integer.parseInt(scan.nextLine());
                        System.out.println("Do you have advantage or disadvantage on the attack(s)?");
                        if (InputChecker.yes(scan.nextLine())) {
                            System.out.println("Which one?");
                            if (InputChecker.advantage(scan.nextLine())) {
                                advantage = true;
                            } else {
                                disadvantage = true;
                            }
                        }
                        DiceRoller.rollWeaponAttack(curChar,curChar.weapons.get(option - 1),advantage, disadvantage, numAttacks);
                    }
                    keepPlaying = true;
                }
                case 2 -> {
                    System.out.println("\nWhat is the name of the skill you are rolling? (Enter a basic stat if there is no name)");
                    String skillName = ClassValidator.capitalizeFirst(scan.nextLine().toLowerCase());
                    if (ClassValidator.isValidSkill(skillName)) {
                        System.out.println("You rolled a " + DiceRoller.rollSkillCheck(skillName, curChar) + " on the check.");
                    } else {
                        System.out.println("Sorry, that skill is invalid.");
                    }
                    keepPlaying = true;
                }
                case 3 -> {
                    System.out.println("\nWhat is the name of the Saving throw you are rolling? (Enter a basic stat name or abbreviation)");
                    String statName = ClassValidator.capitalizeFirst(scan.nextLine().toLowerCase());
                    if (ClassValidator.isValidStat(statName)) {
                        System.out.println("You rolled a " + DiceRoller.rollSavingThrow(statName,curChar) + " on the saving throw.");
                    } else {
                        System.out.println("Sorry, that stat is invalid.");
                    }
                    keepPlaying = true;
                }
                case 4,5,6 -> {
                    if (numMainMenuOptions == 6) {
                        if (option == 4) {
                            // takes input until a valid spell is selected
                            // We only do this for a select few options, but one day maybe I will check for valid input for every response.
                            boolean isValidOption = true;
                            while (isValidOption) {
                                System.out.println("\nWhich spell would you like to cast? (Enter a number)");
                                curChar.printSpellOptions();
                                System.out.println((curChar.spellBook.size() + 1) + ". Go back");
                                option = Integer.parseInt(scan.nextLine());
                                int spellBookSize = curChar.spellBook.size();
                                if (option <= spellBookSize) {
                                    System.out.print("\n");
                                    curChar.spellBook.get(option - 1).printSpellCard();
                                    System.out.println("\nWhat level slot are you expending to cast this spell? (Enter a number)");
                                    System.out.println(curChar.spellBook.get(option - 1).castSpell(Integer.parseInt(scan.nextLine())));
                                } else {
                                    isValidOption= false;
                                    // this is a misleading term, but the go back option is treated as an invalid spell option, so it is set to false, and we quit.
                                    if (option > spellBookSize + 1) {
                                        System.out.println("\nSorry, that option doesn't exit.\n");
                                    }
                                }
                            }
                            keepPlaying = true;
                        } else if (option == 5) {
                            newCharacter = true;
                        }
                    } else if (option == 4) {
                        newCharacter = true;
                    }
                }
            }
        } while (keepPlaying);
        return newCharacter;
    }

}
