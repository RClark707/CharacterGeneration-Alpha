import java.io.File;
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
                    System.out.println("What is your Race?");
                    String race = scan.nextLine();
                    if (InputChecker.random(race)) {
                        race = RandCharacterGenerator.randRace();
                        character.setCharRace(race);
                    } else if (InputChecker.options(race)) {
                        RandCharacterGenerator.printOptions("Race",null);
                        System.out.println("What is your Race?");
                        race = scan.nextLine();
                        if (InputChecker.random(race)) {
                          race = RandCharacterGenerator.randRace();
                        }
                        race = ClassValidator.capitalizeFirst(race);
                        character.setCharRace(race);
                    } else {
                        race = ClassValidator.capitalizeFirst(race);
                        character.setCharRace(race);
                    }
                    System.out.println("You are now a " + character.getCharRace());
                } // end of Race
                // Step 2: Class
                while (character.getCharClass() == null) {
                    System.out.println("What is your Class?");
                    String charClass = scan.nextLine();
                    charClass = ClassValidator.capitalizeFirst(charClass);
                    if (InputChecker.random(charClass)) {
                        charClass = RandCharacterGenerator.randCharClass();
                        character.setCharClass(charClass);
                        character.setCharHitDieType();
                        System.out.println("You are now a " + character.getCharRace() + " " + character.getCharClass());
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
                            System.out.println("You are now a " + character.getCharRace() + " " + character.getCharClass());
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
                // Step 3: Subclass
                while (character.getCharSubclass() == null) {
                    System.out.println("What is your Subclass?");
                    String charSubclass = scan.nextLine();
                    charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                    if (InputChecker.random(charSubclass)) {
                        charSubclass = RandCharacterGenerator.randCharSubclass(character.getCharClass());
                        character.setCharSubclass(charSubclass);
                        System.out.println("You are now a " + character.getCharRace() + " " + character.getCharSubclass() + " " + character.getCharClass());
                    } else if (ClassValidator.isValidSubclass(charSubclass, character.getCharClass())) {
                        character.setCharSubclass(charSubclass);
                    } else if (InputChecker.options(charSubclass)) {
                        RandCharacterGenerator.printOptions("Subclass",character.getCharClass());
                        System.out.println("What is your Subclass?");
                        charSubclass = scan.nextLine();
                        charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                        if (InputChecker.random(charSubclass)) {
                            charSubclass = RandCharacterGenerator.randCharSubclass(character.getCharClass());
                            character.setCharSubclass(charSubclass);
                            System.out.println("You are now a " + character.getCharRace() + " " + character.getCharSubclass() + " " + character.getCharClass());
                        } else if (ClassValidator.isValidSubclass(charSubclass,character.getCharClass())) {
                            character.setCharClass(charSubclass);
                        } else {
                            System.out.println("Sorry, you either entered an unknown Subclass, or misspelled it.");
                        }
                    } else {
                        System.out.println("Sorry, you either entered an unknown Subclass, or misspelled it.");
                    }
                } // end of Subclass
                // Step 4: Background
                while (character.getCharBackground() == null) {
                    System.out.println("What is your Background?");
                    String background = scan.nextLine();
                    background = ClassValidator.capitalizeFirst(background);
                    if (InputChecker.random(background)) {
                        background = RandCharacterGenerator.randBackground();
                        character.setCharBackground(background);
                        System.out.println("You were a " + character.getCharBackground());
                    } else if (InputChecker.options(background)) {
                        RandCharacterGenerator.printOptions("Background",null);
                        System.out.println("What is your Background?");
                        background = scan.nextLine();
                        if (InputChecker.random(background)) {
                            background = RandCharacterGenerator.randBackground();
                            character.setCharBackground(background);
                            System.out.println("You were a " + character.getCharBackground());
                        } else {
                            background = ClassValidator.capitalizeFirst(background);
                            character.setCharBackground(background);
                        }
                    } else {
                        character.setCharBackground(background);
                    }

                } // end of Background
                // Step 5: Skills! :)
                // Not using a while loop here, for no particular reason
                // If a character gets more proficiencies from their race or something, ou can get more random ones here
                System.out.println("How many additional skills do you have (outside of your class & background)");
                int numBonus = scan.nextInt();
                if (character.getCharClass().equals("Bard")) {
                    numBonus += 3;
                }

                // Already knows we have 2 bonus from the background
                character.createSkillArray(numBonus);
                System.out.print("Here are the skills you gained from your background: \n");
                character.printSkillArray();
                System.out.print("\n\n");

                // This grabs the array of extra skills you can pick from based on your class
                // For bards, it is just three random ones, and they don't get a choice >:)
                if (!character.getCharClass().equals("Bard")) {
                    String[] classSkills = RandCharacterGenerator.getClassSkillArray(character.getCharClass());
                    // the following method checks to see if we are a bard (if so, we skip)
                    character.printClassSkillOptions();
                    System.out.print("\nFirst choice: ");
                    int choice1 = scan.nextInt();
                    // Adds to our master list within the character object
                    assert classSkills != null;
                    character.addSkill(classSkills[choice1]);
                    System.out.print("\nSecond choice: ");
                    int choice2 = scan.nextInt();
                    character.addSkill(classSkills[choice2]);
                    // For now these are hard coded to be Ranger and Rogue, since they are the only classes that get more than two choices for skill proficiencies
                    if (character.getCharClass().equals("Ranger") || character.getCharClass().equals("Rogue")) {
                        System.out.print("\nThird choice: ");
                        int choice3 = scan.nextInt();
                        character.addSkill(classSkills[choice3]);
                        if (character.getCharClass().equals("Rogue")) {
                            System.out.print("\nFourth choice: ");
                            int choice4 = scan.nextInt();
                            character.addSkill(classSkills[choice4]);
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
                        preference = scan.nextLine();
                        preference = InputChecker.shortToLong(preference);
                    } while (!ClassValidator.isValidStat(preference));
                    character.applyRacialModifier(2, preference);
                    do {
                        notUnique = false;
                        String previousPref = preference;
                        System.out.println("\nWhich stat would you like to apply a +1 Racial modifier to?");
                        preference = scan.nextLine();
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
                // it is possible I am a line ahead of where I am supposed to be at this stage, but I will test after skill proficiencies
                String[] statValues = scan.nextLine().split(",");
                for (int i = 0; i < 6; ++i) {
                    returnCharacter(name).setStatValue(i, Integer.parseInt(statValues[i]));
                }
                String skillProficiencies = scan.nextLine();
                skillProficiencies = skillProficiencies.substring(1,skillProficiencies.length()-1);
                String[] skills = skillProficiencies.split(",");
                for (String skill : skills) {
                    returnCharacter(name).addSkill(skill);
                }
                scan.nextLine();
            }
        } while (scan.hasNextLine());
        scan.close();
    }

    public void clear() {party.clear();}

    public void removePartyMember(int index) {party.remove(index);}

    public void fullRandom() {
        String randName = RandCharacterGenerator.randName();
        party.add(PartyMember.characterBuilder(randName));
        for (PartyMember character : party) {
            if (randName.equals(character.getCharName())) {
                character.setCharRace(RandCharacterGenerator.randRace());
                character.setCharClass(RandCharacterGenerator.randCharClass());
                character.setCharSubclass(RandCharacterGenerator.randCharSubclass(character.getCharClass()));
                character.setCharHitDieType();
                character.setCharBackground(RandCharacterGenerator.randBackground());
                character.rollStats(rand.nextInt(70,100));
                int numBonus = 0;
                if (character.getCharClass().equals("Ranger")) {
                    numBonus = 1;
                } else if (character.getCharClass().equals("Rogue")) {
                    numBonus = 2;
                }
                if (character.getCharClass().equals("Bard")) {
                    character.createSkillArray(3);
                } else {
                    character.createSkillArray(0);
                    String[] skills = RandCharacterGenerator.getClassSkillArray(character.getCharClass());
                    for (int i = 0; i < skills.length - 1; ++i) {
                        skills[i] = skills[i +1];
                    }
                    int randomIndex = rand.nextInt(RandCharacterGenerator.getClassSkillArray(character.getCharClass()).length);
                    character.addSkill(RandCharacterGenerator.getClassSkillArray(character.getCharClass())[randomIndex]);
                    int tempHolder = randomIndex;
                    int previousIndex;
                    int otherPreviousIndex;
                    do {
                        randomIndex = rand.nextInt(RandCharacterGenerator.getClassSkillArray(character.getCharClass()).length);
                    } while (randomIndex == tempHolder);
                    character.addSkill(RandCharacterGenerator.getClassSkillArray(character.getCharClass())[randomIndex]);
                    // For Rangers and Rogues:
                    if (numBonus > 0) {
                        previousIndex = randomIndex;
                        do {
                            randomIndex = rand.nextInt(RandCharacterGenerator.getClassSkillArray(character.getCharClass()).length);
                        } while (randomIndex == previousIndex || randomIndex == tempHolder);
                        character.addSkill(RandCharacterGenerator.getClassSkillArray(character.getCharClass())[randomIndex]);
                        // In the case of Rogues:
                        if (numBonus > 1) {
                            otherPreviousIndex = randomIndex;
                            do {
                                randomIndex = rand.nextInt(RandCharacterGenerator.getClassSkillArray(character.getCharClass()).length);
                            } while (randomIndex == previousIndex || randomIndex == tempHolder || randomIndex == otherPreviousIndex);
                            character.addSkill(RandCharacterGenerator.getClassSkillArray(character.getCharClass())[randomIndex]);
                        }
                    } // End of Bonus skills for Rogues and Rangers
                } // End of Skills
                String randStat = RandCharacterGenerator.randStat();
                character.applyRacialModifier(2,randStat);
                String secondStat;
                do {
                    secondStat = RandCharacterGenerator.randStat();
                } while (randStat.equals(secondStat));
                character.applyRacialModifier(1,secondStat);
                character.setCharHitPoints(character.abilityScoreModifier(2));
                character.printAll();
            }
        }
    }
}
