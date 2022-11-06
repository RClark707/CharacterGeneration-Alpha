import java.util.ArrayList;
import java.util.Scanner;

public class Party {
// Going to match the Spell Book class, and make everything be based off of methods, rather than cluttering the MAIN
    ArrayList<CharacterSheet> party = new ArrayList<>();
    Scanner scan = new Scanner(System.in);

    public void addCharacter(String charName) {
        party.add(CharacterSheet.characterBuilder(charName));
    }

    public CharacterSheet returnCharacter(String spellName) {
        for (CharacterSheet curCharacter : party) {
            if (spellName.equals(curCharacter.getCharName())) {
                return curCharacter;
            }
        }
        return null;
    }

    public void createCharacter(CharacterSheet characterToCreate) {
        // This method will use returnCharacter method in order to input a CharacterSheet object based on user input
        // We are not combining them for reasons yet to be decided
        for (CharacterSheet character : party) {
            if (characterToCreate.equals(character)) {
                // Step 1: Race
                while (character.getCharRace() == null) {
                    System.out.println("What is your Race?");
                    String race = scan.nextLine();
                    if (InputChecker.random(race)) {
                        race = RandCharacterGenerator.randRace();
                        character.setCharRace(race);
                        System.out.println("You are now a " + character.getCharRace());
                    } else {
                        race = ClassValidator.capitalizeFirst(race);
                        character.setCharRace(race);
                    }
                } // end of Race
                // Step 2: Class
                while (character.getCharClass() == null) {
                    System.out.println("What is your Class?");
                    String charClass = scan.nextLine();
                    charClass = ClassValidator.capitalizeFirst(charClass);
                    if (InputChecker.random(charClass)) {
                        charClass = RandCharacterGenerator.randCharClass();
                        character.setCharClass(charClass);
                        System.out.println("You are now a " + character.getCharRace() + " " + character.getCharClass());
                    } else if (ClassValidator.isValidClass(charClass)) {
                        character.setCharClass(charClass);
                    } else {
                        System.out.println("Sorry, you either entered an unknown Class, or misspelled it.");
                    }
                } // end of Class
                // Step 3: Subclass
                while (character.getCharSubclass() == null) {
                    System.out.println("What is your Subclass?");
                    String charSubclass = scan.nextLine();
                    if (InputChecker.random(charSubclass)) {
                        charSubclass = RandCharacterGenerator.randCharSubclass(character.getCharClass());
                        character.setCharSubclass(charSubclass);
                        System.out.println("You are now a " + character.getCharRace() + " " + character.getCharSubclass() + " " + character.getCharClass());
                    } else if (ClassValidator.isValidSubclass(charSubclass, character.getCharClass())) {
                        charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                        character.setCharSubclass(charSubclass);
                    } else {
                        System.out.println("Sorry, you either entered an unknown Subclass, or misspelled it.");
                    }
                } // end of Subclass
                // Step 4: Background
                while (character.getCharBackground() == null) {
                    System.out.println("What is your Background?");
                    String background = scan.nextLine();
                    background = ClassValidator.capitalizeFirst(background);
                    character.setCharBackground(background);
                } // end of Background
                // Step 5: Skills! :)
                // Not using a while loop here, for no particular reason
                // If a character gets more proficiencies from their race or something, ou can get more random ones here
                System.out.println("How many additional skills do you have (outside of your class)");
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

                        System.out.println("\nIf you want to swap stats, type the name of the stat you want to swap from. Otherwise type no or skip.");
                        String swapFromString;
                        String swapToString;

                        swapFromString = scan.next();
                        scan.nextLine();
                        if (InputChecker.no(swapFromString) || swapFromString.equals("skip")) {
                            break;
                        } else {
                            System.out.println("Now, input the stat you want to swap the score to.");
                            swapToString = scan.next();
                            if (ClassValidator.isValidStat(swapFromString) && ClassValidator.isValidStat(swapToString)) {
                                character.configureStats(swapFromString, swapToString);
                            } else {
                                System.out.println("One or more of the stats you entered was misspelled.");
                            }
                        }
                    } while (true);

                    // Applies Racial Modifiers according to the new 5e changes. +2 to one, and +1 to another
                    // It will not tell you if you misspelled the stat, but you will get to retry entering it.
                    String preference;
                    boolean notUnique;
                    do {
                        System.out.println("\nWhich stat would you like to apply a +2 Racial Modifier to?");
                        preference = scan.nextLine();
                    } while (!ClassValidator.isValidStat(preference));
                    character.applyRacialModifier(2, preference);
                    do {
                        notUnique = false;
                        String previousPref = preference;
                        System.out.println("\nWhich stat would you like to apply a +1 Racial modifier to?");
                        preference = scan.nextLine();
                        if (previousPref.equals(preference)) {
                            notUnique = true;
                            System.out.println("You already boosted that stat... Please pick another one.");
                        }
                    } while (!ClassValidator.isValidStat(preference) || notUnique);
                    character.applyRacialModifier(1, preference);

                }
                // Print out the Character Sheet
                character.printCharacterSheet();
                character.printStatArray();
                character.printSkillArray();
                System.out.println("\n\n");
                break;
            } // End of if statement once we found the correct character
        } // End of for loop to check which character we want to configure
    } // End of method

    public String printParty() {
        // prints the names of each spell in the book
        String partyMembers = "";
        for (int i = 0; i < party.size(); ++i) {
            CharacterSheet curPartyMember = party.get(i);
            if (i != party.size() - 1) {
                partyMembers = partyMembers.concat(curPartyMember.getCharName() + ", ");
            } else {
                partyMembers = partyMembers.concat(curPartyMember.getCharName());
            }
        }
        return partyMembers;
    }
}
