import java.util.Scanner;

public class CharacterGenMAIN {

    public static void main(String[] args) {
        int option; // This is what option the user selects in the main menu
        int subOption; // used in case 2
        String doMore; // Used inside the switch for each option to determine if the user wants to iterate again
        Scanner scan = new Scanner(System.in); // Takes user input
        String charClass; // Temporary placeholder to pass in to methods as a parameter
        SpellBook Grimoire = new SpellBook();
        Party Party = new Party();

        // Here is our main menu, we use a switch to decide what to do with the user choice
        do {
            System.out.println("\nWhat would you like to do? (Enter a number 1-9)");
            System.out.println("1. Create a Character!");
            System.out.println("2. View your Party!");
            System.out.println("3. Random Subclass!");
            System.out.println("4. Animate Objects!");
            System.out.println("5. Roll my Stats!");
            System.out.println("6. Roll Dice!");
            System.out.println("7. Read your Spell Book!");
            System.out.println("8. Test!");
            System.out.println("9. Exit");
            // Collect the user input and use a switch to identify what we should do
            option = scan.nextInt();
            scan.nextLine(); // This brings us to the next Line in which the User can/will enter input.

            switch(option) {
                // Character Creation
                case 1:
                    do {
                        // Take the name and use it much like a spell to create a new Party Member object
                        System.out.println("\nWhat is your Character's Name?");
                        String charName = scan.nextLine();
                        if (!InputChecker.fullRandom(charName)) {
                            if (InputChecker.random(charName)) {
                                charName = RandCharacterGenerator.randName();
                            }
                            // This makes the object with a named attribute of the character's name
                            charName = ClassValidator.capitalizeFirst(charName);
                            Party.addCharacter(charName);
                            System.out.println("Let's create " + charName + "!\n");
                            // This is like the configureSpell method, it lets us set all the internal attributes of the party member
                            // Soon, we will also need a method to print the literal character sheet of the party member,
                            // For right now, the printing of the character sheet is within the createCharacter method
                            // It is on the to do list though
                            System.out.println("At any point, type \"options\" to see examples of valid entries.");
                            Party.createCharacter(Party.returnCharacter(charName));
                        } else {
                            Party.fullRandom();
                        }

                        System.out.println("\n\nCurrent party members: " + Party.printParty());

                        System.out.println("\n\nEnter Y to make another Character, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                // View Party Members
                case 2:
                    boolean viewAnother;
                    boolean repeatMenu;
                    do {
                        System.out.println("\nCurrent party members: " + Party.printParty());
                        System.out.println("\nWhich character would you like to view?");
                        String curCharacter = scan.nextLine();
                        do {
                        repeatMenu = false;
                        viewAnother = false;
                        Party.returnCharacter(curCharacter).printAll();
                        System.out.println("What would you like to do?");
                        System.out.println("1. Level Up");
                        System.out.println("2. Change Name");
                        System.out.println("3. View Another Character");
                        System.out.println("4. Exit to Main Menu");
                        subOption = scan.nextInt();
                        scan.nextLine();

                            switch (subOption) {
                                case 1 -> {
                                    Party.returnCharacter(curCharacter).nextLevel();
                                    repeatMenu = true;
                                }
                                case 2 -> {
                                    System.out.println("What is " + Party.returnCharacter(curCharacter).getCharName() + "'s new name?");
                                    String newName= scan.nextLine();
                                    Party.returnCharacter(curCharacter).changeCharName(newName);
                                    curCharacter = newName;
                                    repeatMenu = true;
                                }
                                case 3 -> viewAnother = true;
                                default -> {}
                            }
                        } while (repeatMenu);

                    } while (viewAnother);
                    break;

                // Random Subclass and/or class
                case 3:
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
                case 4:
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
                case 5:
                    do {
                        System.out.println("\nEnter the minimum threshold you want when rolling stats.");
                        System.out.println("Valid threshold options are integers 70-100 inclusive, and any other number for no threshold.");
                        int threshold = scan.nextInt();
                        scan.nextLine();
                        if (threshold >= 60 && threshold <= 105) {
                            System.out.println(DiceRoller.rollGoodStats(threshold));
                        }
                        System.out.println(DiceRoller.rollAllStats(true));

                        System.out.println("\nEnter Y to roll more Stats, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));

                    break;
                // Dice Rolling
                case 6:
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
                case 7:
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
                                System.out.println("\n\n");
                                Grimoire.returnSpell(spellName).printSpellCard();
                                // Using the returnSpell method is key, because user input is either a String or an integer,
                                // and we can't input a String as a Spell object to a method that wants a spell object input
                                // maybe it's worth putting a String -> Spell function within these methods, but right now that is
                                // not a priority
                                configureMore = true;
                                // Since they configured a spell, let's reprint the book and ask if they want to make more changes.
                            }
                        } while (configureMore);
                        do {
                            System.out.println("\nWould you like to cast a spell? Alternatively, enter the name of a spell to view its spell card.");
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
                            } else if (Grimoire.returnSpell(doMore) != null){
                                System.out.println("\n");
                                Grimoire.returnSpell(doMore).printSpellCard();
                                System.out.println("Would you like to go back to spell casting?");
                                doMore = scan.nextLine();
                            }
                        } while (InputChecker.yes(doMore));

                        System.out.println("\nEnter Y to read your book some more, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                // Testing
                case 8:
                    do {

                        System.out.println("\nEnter Y to try again, or N to go back.");
                        doMore = scan.nextLine();

                    } while (InputChecker.yes(doMore));
                    break;
                case 9:
                    break;
                default:
                    // I don't remember why I include this scan.nextLine() piece, but it probably is good
                    // to wipe to the next line in case someone entered a number <0 or greater than the switch index
                    scan.nextLine();
                    break;
            } //switch ends
        }while (option != 9);
        System.out.println("Sorry to see you go, hope you're back soon.");
        scan.close();
    }
}
