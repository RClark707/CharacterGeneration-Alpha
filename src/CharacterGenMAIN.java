import java.util.Scanner;

public class CharacterGenMAIN {


    public static void main(String[] args) {
        int option; // This is what option the user selects in the main menu
        String doMore; // Used inside the switch for each option to determine if the user wants to iterate again
        Scanner scan = new Scanner(System.in); // Takes user input
        String charClass; // Temporary placeholder to pass in to methods as a parameter
        int i;
        SpellBook Grimoire = new SpellBook();

        // Here is our main menu, we use a switch to decide what to do with the user choice
        do {
            System.out.println("\nWhat would you like to do? (Enter a number 1-8)");
            System.out.println("1. Create a Character!");
            System.out.println("2. Random Subclass!");
            System.out.println("3. Animate Objects!");
            System.out.println("4. Roll my Stats!");
            System.out.println("5. Roll Dice!");
            System.out.println("6. Read your Spell Book");
            System.out.println("7. Test!");
            System.out.println("8. Exit");
            // Collect the user input and use a switch to identify what we should do
            option = scan.nextInt();
            scan.nextLine(); // This brings us to the next Line in which the User can/will enter input.
            boolean invalid;

            switch(option) {
                // Character Creation
                case 1:
                    do {
                        // We will make a character
                        // Step 1: Name
                        System.out.println("\nWhat is your Character's Name?");
                        String charName = scan.nextLine();
                        if (InputChecker.random(charName)) {
                            charName = RandCharacterGenerator.randName();
                        }
                        CharacterSheet current = new CharacterSheet(charName);

                        // Step 1.5: Race
                        System.out.println("What is your Race?");
                        String race = scan.nextLine();
                        if (InputChecker.random(race)) {
                            race = RandCharacterGenerator.randRace();
                        }
                        race = ClassValidator.capitalizeFirst(race);
                        current.setCharRace(race);

                        // Step 2: Class
                        do {
                            invalid = false;
                            System.out.println("What is your Class?");
                            charClass = scan.nextLine();
                            if (ClassValidator.isValidClass(charClass)) {
                                if (InputChecker.random(charClass)) {
                                    charClass = RandCharacterGenerator.randCharClass();
                                }
                                charClass = ClassValidator.capitalizeFirst(charClass);
                                current.setCharClass(charClass);
                            } else {
                                System.out.println("Sorry, you either entered an unknown Class, or misspelled it.");
                                invalid = true;
                            }
                        } while (invalid);
                        // Step 3: Subclass
                        do {
                            invalid = false;
                            System.out.println("What is your Subclass?");
                            String charSubclass = scan.nextLine();
                            if (ClassValidator.isValidSubclass(charSubclass, charClass)) {
                                if (InputChecker.random(charSubclass)) {
                                    charSubclass = RandCharacterGenerator.randCharSubclass(charClass);
                                }
                                charSubclass = ClassValidator.capitalizeFirst(charSubclass);
                                current.setCharSubclass(charSubclass);
                            } else {
                                System.out.println("Sorry, you either entered an unknown Subclass, or misspelled it.");
                                invalid = true;
                            }
                        } while (invalid);
                        // Step 4: Background
                        System.out.println("What is your Background?");
                        String background = scan.nextLine();
                        background = ClassValidator.capitalizeFirst(background);
                        current.setCharBackground(background);

                        // Step 4.5 Skills!
                        // This is just the default number of skills characters will get from a background
                        // We are just giving them two random ones, because that is easier than making more arrays
                        final int numBackgroundSkills = 2;
                        // If a character gets more proficiencies from their race or something, ou can get more random ones here
                        System.out.println("How many additional skills do you have (outside of your class)");
                        int numBonus = scan.nextInt();
                        if (charClass.equals("Bard")) {
                            numBonus += 3;
                        }

                        // This makes an array of random skills based on how many you get from background and bonus
                        String[] tempArray = RandCharacterGenerator.randSkillArray((numBackgroundSkills + numBonus));
                        System.out.print("Here are the skills you gained from your background: ");
                        for (i = 0; i < tempArray.length; ++i) {
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
                        String[] classSkills = RandCharacterGenerator.getClassSkillArray(charClass);

                        // numSKillChoices is how many times they can pick from the list, used later in constructing the final array pf skill proficiencies
                        int numSkillChoices = 0;
                        // Sort of an oversize array, because it will most often be two Strings and then two empty spots
                        String[] choiceArray = new String[4];

                        if (!charClass.equals("Bard")) {
                            System.out.println("Choose " + classSkills[0] + " skills from this list (Use #'s, separated by a space): ");

                            // We get rid of this element in the next step, so we store it in a variable to call back later
                            String numberChoices = classSkills[0];

                            // Prints a numbered list of skills to pick from, so that I don't need more switches
                            for (i = 0; i < classSkills.length - 1; ++i) {
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
                        String[] skillArray = new String[numSkillChoices + tempArray.length];
                        for (i = 0; i < tempArray.length; ++i) {
                            skillArray[i] = tempArray[i];
                        }

                        // now we fill the end of the skillArray with the choices we made based on class, skipping the Empty spots
                        if (!charClass.equals("Bard")) {
                            for (i = 0; i < choiceArray.length; ++i) {
                                if (!choiceArray[i].equals("Empty")) {
                                    skillArray[i + tempArray.length] = choiceArray[i];
                                } else {
                                    break;
                                }
                            }
                        }

                        // Step 5: Stats!
                        System.out.println("\nEnter the minimum threshold you want when rolling stats.");
                        System.out.println("Valid threshold options are integers 70-100 inclusive, and any other number for no threshold.");
                        int threshold = scan.nextInt();
                        scan.nextLine();
                        current.rollStats(threshold);
                        current.optimizeStats(charClass);

                        // Time to print out the character sheet, you'll see why we use a do-while loop at the bottom
                        do {
                            System.out.println("\nHere are your ability scores:");
                            System.out.println("Strength: " + current.retrieveStat(0));
                            System.out.println("Dexterity: " + current.retrieveStat(1));
                            System.out.println("Constitution: " + current.retrieveStat(2));
                            System.out.println("Intelligence: " + current.retrieveStat(3));
                            System.out.println("Wisdom: " + current.retrieveStat(4));
                            System.out.println("Charisma: " + current.retrieveStat(5));

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
                                if (swapFromString.equals("no")) {
                                    System.out.println("\nWhich stat would you like to apply a +2 Racial Modifier to?");
                                    String preference = scan.nextLine();
                                    current.applyRacialModifier(2,preference);
                                    System.out.println("\nWhich stat would you like to apply a +1 Racial modifier to?");
                                    preference = scan.nextLine();
                                    current.applyRacialModifier(1,preference);
                                    // Print the stats again!
                                    System.out.println("\nHere is your character sheet!");
                                    System.out.println(current.getCharName() + "           " + current.getCharClass());
                                    System.out.println(current.getCharBackground() + "          " + current.getCharSubclass());
                                    System.out.println("Strength: " + current.retrieveStat(0));
                                    System.out.println("Dexterity: " + current.retrieveStat(1));
                                    System.out.println("Constitution: " + current.retrieveStat(2));
                                    System.out.println("Intelligence: " + current.retrieveStat(3));
                                    System.out.println("Wisdom: " + current.retrieveStat(4));
                                    System.out.println("Charisma: " + current.retrieveStat(5));
                                    System.out.print("Skill Proficiencies: ");
                                    for (i = 0; i < skillArray.length; ++i) {
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
                                    current.configureStats(swapFromString, swapToString);
                                } else {
                                    System.out.println("One or more of the stats you entered was misspelled.");
                                }
                            } else if (scan.hasNextInt()) {
                                swapToInt = scan.nextInt();
                                current.configureStats(swapFromInt, swapToInt);
                            }

                        } while (true);

                        System.out.println("\n\nEnter Y for another Character, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                    // Random Subclass and/or class
                case 2:
                    do {
                        System.out.println("\nWhat is your Character's class? (Enter Random for a random class)");
                        charClass = scan.nextLine();

                        // Time to see if your input was a recognized class or not (or Random)
                        if (ClassValidator.isValidClass(charClass)) {

                            // If-statement can be replaced with a switch? Yes, I will keep this change.
                            switch (charClass) {
                                case "Random" -> {
                                    charClass = RandCharacterGenerator.randCharClass();
                                    System.out.println("You are a " + charClass);
                                    System.out.println("Good luck!");
                                }
                                // Outputs a nice phrase based on your choice ;)
                                case "Ranger" -> System.out.println("Terrible choice! Good luck losing!");
                                case "Bard" -> System.out.println("Excellent choice!");
                                case "Warlock" -> System.out.println("Interesting choice...");
                                case "Wizard" -> System.out.println("I love d6 hit dice too!");
                                default -> System.out.println("Great choice!");
                            }
                            //  Here we will print the Random Character Subclass based on the above input
                            System.out.print("Your subclass is the ");
                            System.out.print(RandCharacterGenerator.randCharSubclass(charClass));
                            System.out.println(".");

                            // If your input was bad, we say this
                        } else if (!(ClassValidator.isValidClass(charClass))) {
                            System.out.println("Sorry, that Class is unsupported.");
                        }

                        System.out.println("\nEnter Y for another Character, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                    // Animate Objects casting
                case 3:
                    do {
                        System.out.println("\nWhat level spell is your Animate Objects?");

                        int spellLevel = scan.nextInt();
                        scan.nextLine();

                        if (spellLevel >= 5) {
                            System.out.println("What armor class are we aiming for?");

                           int  armorClass = scan.nextInt();
                            scan.nextLine();

                            System.out.print("Your " + spellLevel + "th level Animate Objects dealt ");
                            System.out.println(SpellDamageRoller.animateObjects(spellLevel, armorClass) + " damage!");

                        } else {
                            System.out.println("That spell level is, frankly, just not possible. It needs to be greater than 4.");
                            System.out.println("Would you like to try again?");
                        }
                        System.out.println("\nEnter Y to attack again, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));

                    break;
                    // Stat Rolling
                case 4:
                    do {
                        System.out.println("\nDo you want to roll stats normally, or with a minimum threshold?");
                        String quality = scan.nextLine();

                        if (quality.equals("normally")|| quality.equals("normal") || quality.equals("Normal") || quality.equals("Normally")) {
                            // do stuff for normal
                            System.out.println("Your stats are: " + DiceRoller.rollAllStats(true));

                        } else if (quality.equals("threshold") || quality.equals("Threshold") || quality.equals("minimum") ||
                                quality.equals("Minimum") || quality.equals("minimum threshold") ||
                                quality.equals("Minimum Threshold") || quality.equals("min") || quality.equals("Min")) {
                            int threshold;
                            System.out.println("What is your threshold for the total score?");
                            threshold = scan.nextInt();
                            scan.nextLine(); // to get rid of any unused text?
                            System.out.println("Your good stats are: " + DiceRoller.rollGoodStats(threshold));
                        }

                        System.out.println("\nEnter Y to roll more Stats, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));

                    break;
                    // Dice Rolling
                case 5:
                    do {
                        System.out.println("\nWhat kind of dice are you rolling?");
                        String diceType = scan.nextLine();

                        if (Character.isLetter(diceType.charAt(0))) {
                            diceType = diceType.substring(1);
                        }

                        System.out.println("How many dice do you want to roll?");
                        int numDice = scan.nextInt();
                        scan.nextLine();

                        System.out.println("Result: " + DiceRoller.rollDice(Integer.parseInt(diceType), numDice));

                        System.out.println("\nEnter Y to roll more dice, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                // Spell Casting
                case 6:
                    do {
                        // This variable is used to track the user's input for what spell to interact with
                        String spellName;
                        // this variable is used similarly to doMore, but it's just a boolean instead of a string that tracks user input
                        boolean configureMore;
                        do {
                            // Asking if we should add some spells, if no we skip the next part and go straight to printing the spell book
                            System.out.println("\nType the name of a Spell to add to your Spell Book (Enter no to stop).");
                            spellName = scan.nextLine();
                            if (!InputChecker.no(spellName)) {
                                spellName = ClassValidator.capitalizeFirst(spellName);
                                Grimoire.addSpell(spellName);
                            }
                        } while (!InputChecker.no(spellName));
                        // Now, we print out the contents of the spell book (Everything that the user just entered)
                        do {
                            System.out.println("\nYour Spell Book contains the following spells:");
                            System.out.println(Grimoire.printSpellBook());

                            // Configuring a spell means to modify the internal attributes such that you can
                            // actually cast the spell using the spell.castSpell method, called by the returnSpell.castSpell most often
                            System.out.println("\nWhich spell do you want to configure? (Enter a number to skip)");
                            // I'd like to rewrite this, but I haven't decided if there is a faster way to check for an integer vs a string
                            if (scan.hasNextInt()) {
                                // So if there is a number, we skip configuration
                                configureMore = false;
                                scan.nextLine();
                                // Alternatively could take the int value of a string and use that instead of this weird int checker
                            } else {
                                // now that we know it isn't a number (at least, it's not an integer)
                                // we can run the configureSpellEffects method to set all the relevant attributes of the given spell
                                spellName = scan.nextLine();
                                spellName = ClassValidator.capitalizeFirst(spellName);
                                Grimoire.configureSpellEffects(Grimoire.returnSpell(spellName));
                                // Using the returnSpell method is key, because user input is either a String or an integer,
                                // and we can't input a String as a Spell object to a method that wants a spell object input
                                // maybe it's worth putting a String -> Spell function within these methods, but right now that is
                                // not a priority
                                configureMore = true;
                                // Since they configured a spell, let's reprint the book and ask if they want to make more changes.
                            }
                        } while (configureMore);
                        do {
                            System.out.println("\nWould you like to cast a spell?");
                            doMore = scan.nextLine();
                            if (InputChecker.yes(doMore)) {
                                System.out.println("Which spell do you want to cast?");
                                spellName = scan.nextLine();
                                spellName = ClassValidator.capitalizeFirst(spellName);
                                if (InputChecker.random(spellName)) {
                                    spellName = Grimoire.randSpellName();
                                    System.out.println("\nYou are casting " + spellName);
                                }
                                System.out.println("\nWhat level are you casting this spell at?");
                                int castingLevel = scan.nextInt();
                                String level;
                                scan.nextLine();
                                switch (castingLevel) {
                                    case 1 -> level = castingLevel + "st";
                                    case 2 -> level = castingLevel + "nd";
                                    case 3 -> level = castingLevel + "rd";
                                    default -> level = castingLevel + "th";
                                    // Keep in mind that we will never see a casting level greater than 9
                                }
                                System.out.println("You are casting " + spellName + " at " + level + " level.");
                                System.out.println("Prepare to be amazed.");
                                System.out.println("\n" + Grimoire.returnSpell(spellName).castSpell(castingLevel));
                            }
                        } while (InputChecker.yes(doMore));

                        System.out.println("\nEnter Y to read your book some more, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                // Testing
                case 7:
                    do {

                        System.out.println("\nEnter Y to try again, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                case 8:
                    break;
                default:
                    // I don't remember why I include this scan.nextLine() piece, but it probably is good
                    // to wipe to the next line in case someone entered a number <0 or greater than the switch index
                    scan.nextLine();
                    break;
            } //switch ends
        }while (option != 8);
        System.out.println("Sorry to see you go, hope you're back soon.");
        scan.close();
    }
}
