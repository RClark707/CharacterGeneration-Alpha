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
                while (character.getCharClass() == null){
                    System.out.println("What is your Class?");
                    String charClass = scan.nextLine();
                    if (InputChecker.random(charClass)) {
                        charClass = RandCharacterGenerator.randCharClass();
                        character.setCharClass(charClass);
                        System.out.println("You are now a " + character.getCharRace() + " " + character.getCharClass());
                    } else if (ClassValidator.isValidClass(charClass)) {
                        charClass = ClassValidator.capitalizeFirst(charClass);
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
                final int numBackgroundSkills = 2;
                // If a character gets more proficiencies from their race or something, ou can get more random ones here
                System.out.println("How many additional skills do you have (outside of your class)");
                int numBonus = scan.nextInt();
                if (character.getCharClass().equals("Bard")) {
                    numBonus += 3;
                }

                // This makes an array of random skills based on how many you get from background and bonus
                String[] tempArray = RandCharacterGenerator.randSkillArray((numBackgroundSkills + numBonus));
                System.out.print("Here are the skills you gained from your background: ");
                for (int i = 0; i < tempArray.length; ++i) {
                    String s = tempArray[i];
                    if (i != tempArray.length - 1) {
                        System.out.print(s + ", ");
                    } else {
                        System.out.print(s);
                    }
                }
                System.out.print("\n");

                // This grabs the array of extra skills you can pick from based on your class
                // For bards, it is jut three random ones, and they don't geta  choice >:)
                String[] classSkills = RandCharacterGenerator.getClassSkillArray(character.getCharClass());

                // numSKillChoices is how many times they can pick from the list, used later in constructing the final array pf skill proficiencies
                int numSkillChoices = 0;
                // Sort of an oversize array, because it will most often be two Strings and then two empty spots
                String[] choiceArray = new String[4];

                if (!character.getCharClass().equals("Bard")) {
                    System.out.println("Choose " + classSkills[0] + " skills from this list (Use #'s, separated by a space): ");

                    // We get rid of this element in the next step, so we store it in a variable to call back later
                    String numberChoices = classSkills[0];

                    // Prints a numbered list of skills to pick from, so that I don't need more switches
                    for (int i = 0; i < classSkills.length - 1; ++i) {
                        classSkills[i] = classSkills[i + 1];
                        String s = classSkills[i];
                        System.out.println((i + 1) + ". " + s);
                    }

                    // Most classes only let you take wo skills, but both Ranger and Rogue allow for more
                    System.out.print("\nFirst choice: ");
                    int choice1 = scan.nextInt();
                    numSkillChoices++;
                    choiceArray[0] = classSkills[choice1];
                    System.out.print("\nSecond choice: ");
                    int choice2 = scan.nextInt();
                    numSkillChoices++;
                    choiceArray[1] = classSkills[choice2];
                    // this goes back to that variable we assigned to element 0 of classSkills array, it's jus the numSkillChoices but in String form
                    if (numberChoices.equals("three") || numberChoices.equals("four")) {
                        System.out.print("\nThird choice: ");
                        int choice3 = scan.nextInt();
                        numSkillChoices++;
                        choiceArray[2] = classSkills[choice3];
                        if (numberChoices.equals("four")) {
                            System.out.print("\nFourth choice: ");
                            int choice4 = scan.nextInt();
                            numSkillChoices++;
                            choiceArray[3] = classSkills[choice4];
                        }
                    } else {
                        // If we don't have more than two choices, we fill the rest with Empty
                        choiceArray[2] = "Empty";
                        choiceArray[3] = "Empty";
                    }
                }
                // We fill the beginning of the skillArray with the ones gained from background and bonus
                // TODO: Make this a part of the CharacterSheet class!
                String[] skillArray = new String[numSkillChoices + tempArray.length];
                // They recommend using an array copy method, but I don't understand it, so I will not use it
                for (int i = 0; i < tempArray.length; ++i) {
                    skillArray[i] = tempArray[i];
                }

                // now we fill the end of the skillArray with the choices we made based on class, skipping the Empty spots
                if (!character.getCharClass().equals("Bard")) {
                    for (int i = 0; i < choiceArray.length; ++i) {
                        if (!choiceArray[i].equals("Empty")) {
                            skillArray[i + tempArray.length] = choiceArray[i];
                        } else {
                            break;
                        }
                    }
                } // end of Skills?
                // Step 6: Stats
                while (character.retrieveStat(0) == 0) {
                    System.out.println("\nEnter the minimum threshold you want when rolling stats.");
                    System.out.println("Valid threshold options are integers 70-100 inclusive, and any other number for no threshold.");
                    int threshold = scan.nextInt();
                    scan.nextLine();
                    character.rollStats(threshold);
                    character.optimizeStats(character.getCharClass());

                    // Time to print out the character sheet, you'll see why we use a do-while loop at the bottom
                    do {
                        System.out.println("\nHere are your ability scores:");
                        System.out.println("Strength: " + character.retrieveStat(0));
                        System.out.println("Dexterity: " + character.retrieveStat(1));
                        System.out.println("Constitution: " + character.retrieveStat(2));
                        System.out.println("Intelligence: " + character.retrieveStat(3));
                        System.out.println("Wisdom: " + character.retrieveStat(4));
                        System.out.println("Charisma: " + character.retrieveStat(5));

                        System.out.println("\nIf you don't want to swap stats, type \"no\". Otherwise, input the stat you want to swap the score from. ");
                        // Initialized to guarantee that a problem arises if the user inputs something weird
                        String swapFromString = "";
                        int swapFromInt = -1;
                        String swapToString;
                        int swapToInt;

                        if (scan.hasNext()) {
                            // if they entered the name of the stat
                            swapFromString = scan.next();
                            scan.nextLine();
                            if (InputChecker.no(swapFromString)) {
                                System.out.println("\nWhich stat would you like to apply a +2 Racial Modifier to?");
                                String preference = scan.nextLine();
                                character.applyRacialModifier(2,preference);
                                String temporaryPref = preference;
                                System.out.println("\nWhich stat would you like to apply a +1 Racial modifier to?");
                                preference = scan.nextLine();
                                while (temporaryPref.equals(preference)) {
                                    preference = RandCharacterGenerator.randStat();
                                }
                                character.applyRacialModifier(1,preference);
                                // Print the stats again!
                                // TODO:2 create a print method within the CharacterSheet Class that does this same thing
                                System.out.println("\nHere is your character sheet!\n");
                                System.out.println(character.getCharName() + "           " + character.getCharClass());
                                System.out.println(character.getCharBackground() + "          " + character.getCharSubclass());
                                System.out.println("Strength: " + character.retrieveStat(0));
                                System.out.println("Dexterity: " + character.retrieveStat(1));
                                System.out.println("Constitution: " + character.retrieveStat(2));
                                System.out.println("Intelligence: " + character.retrieveStat(3));
                                System.out.println("Wisdom: " + character.retrieveStat(4));
                                System.out.println("Charisma: " + character.retrieveStat(5));
                                System.out.print("\nSkill Proficiencies: ");
                                // This will be fun to add into the CharacterSheet Class!!!
                                for (int i = 0; i < skillArray.length; ++i) {
                                    if (i != skillArray.length - 1) {
                                        System.out.print(skillArray[i] + ", ");
                                    } else {
                                        System.out.print(skillArray[i]);
                                    }
                                }
                                break;
                            }
                        } else if (scan.hasNextInt()) {
                            // if they entered the index of the stat (unlikely)
                            swapFromInt = scan.nextInt();
                        }
                        System.out.println("Now, input the stat you want to swap the score to.");
                        if (scan.hasNext()) {
                            swapToString = scan.next();
                            if (ClassValidator.isValidStat(swapToString) && ClassValidator.isValidStat(swapFromString)) {
                                character.configureStats(swapFromString, swapToString);
                            } else {
                                System.out.println("One or more of the stats you entered was misspelled.");
                            }
                        } else if (scan.hasNextInt()) {
                            swapToInt = scan.nextInt();
                            character.configureStats(swapFromInt, swapToInt);
                        }
                    } while (true); // End of Stats and overall Character Printing
                } // end of Stat swapping and overall Character Printing as well
            } // end of if statement
        } // end of for statement
    } // end of method

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
