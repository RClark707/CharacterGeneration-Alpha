import java.io.FileNotFoundException;
import java.util.Scanner;

public class CharacterGenMAIN {

    public static void main(String[] args) throws FileNotFoundException {
        int option; // This is what option the user selects in the main menu
        int subOption; // used in case 2 for a secondary menu option tracker
        boolean viewAnother; // used to determine when to view another character (perhaps spell in the future)
        boolean repeatMenu; // used to determine when to repeat a menu's contents (used in do-while loops)
        String doMore; // Used inside the switch for each option to determine if the user wants to iterate again
        Scanner scan = new Scanner(System.in); // Takes user input
        String charClass; // Temporary placeholder to pass into methods as a parameter
        SpellBook Grimoire = new SpellBook();
        Party Party = new Party();
        String fileToSaveTo = "PartySaveFiles";
        String spellFile = "SpellFile";
        if (Party.countLines(fileToSaveTo) > 0) {
            Party.initializeParty(fileToSaveTo);
        }
        if (Grimoire.countLines(spellFile) > 0) {
            Grimoire.initializeSpellBook(spellFile);
        }

        // Here is our main menu, we use a switch to decide what to do with the user choice
        do {
            System.out.println("\nWhat would you like to do? (Enter a number 1-10)");
            System.out.println("1. Create a Character!");
            System.out.println("2. View your Party!");
            System.out.println("3. Pick a Random Subclass!");
            System.out.println("4. Animate Some Objects!");
            System.out.println("5. Roll Stats!");
            System.out.println("6. Roll Dice!");
            System.out.println("7. Read your Spell Book!");
            System.out.println("8. Play a Character!");
            System.out.println("9. Save All!");
            System.out.println("10. Exit");
            // Collect the user input and use a switch to identify what we should do
            option = scan.nextInt();
            scan.nextLine(); // This brings us to the next Line in which the User can/will enter input.

            switch(option) {
                // Character Creation
                case 1:
                    do {
                        // Take the name and use it much like a spell to create a new Party Member object
                        System.out.println("\nUseful Tips: Enter \"full random\" as a name for a completely random character, or enter \"random\" into any of your character's elements to pick a random option.");
                        System.out.println("\nWhat is your Character's Name?");
                        String charName = scan.nextLine().toLowerCase();
                        if (!InputChecker.fullRandom(charName)) {
                            if (InputChecker.random(charName)) {
                                charName = RandCharacterGenerator.randName();
                            }
                            // This makes the object with a named attribute of the character's name
                            charName = ClassValidator.capitalizeFirst(charName);
                            Party.addCharacter(charName);
                            System.out.println("Let's create " + charName + "!");
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
                    do {
                        System.out.print("\nCurrent party members: ");
                        if (Party.getPartySize() == 0) {
                            System.out.println("None");
                        } else {
                            System.out.println(Party.printParty());
                        }
                            viewAnother = false;
                            System.out.println("Who would you like to view?\n");
                            Party.printPartyOptions();
                            System.out.println((Party.getPartySize() + 1) + ". Save and Exit to Main Menu");
                            System.out.println((Party.getPartySize() + 2) + ". Clear party");
                            if (Party.getPartySize() == 0) {
                                System.out.println((Party.getPartySize() + 3) + ". Create a Character");
                            }
                            option = scan.nextInt();
                            scan.nextLine();

                        if (option <= Party.getPartySize()) {
                            do {
                                repeatMenu = false;
                                PartyMember curCharacter = Party.getPartyMember(option-1);
                                curCharacter.printAll();
                                System.out.println("What would you like to do?");
                                System.out.println("1. Level Up");
                                System.out.println("2. Change Name");
                                System.out.println("3. View Another Character");
                                System.out.println("4. DELETE X_X");
                                System.out.println("5. Save and Exit to Main Menu");
                                subOption = scan.nextInt();
                                scan.nextLine();
                                switch (subOption) {
                                    case 1 -> {
                                        curCharacter.nextLevel();
                                        repeatMenu = true;
                                    }
                                    case 2 -> {
                                        System.out.println("What is " + curCharacter.getCharName() + "'s new name?");
                                        String newName = scan.nextLine();
                                        curCharacter.changeCharName(newName);
                                        repeatMenu = true;
                                    }
                                    case 3 -> viewAnother = true;
                                    case 4 -> {
                                        System.out.println("Are you sure?");
                                        if (InputChecker.yes(scan.nextLine())) {
                                            Party.removePartyMember(option-1);
                                            viewAnother = true;
                                        } else {
                                            repeatMenu = true;
                                        }
                                    }
                                    default -> Party.saveParty(fileToSaveTo);
                                }
                            } while (repeatMenu);
                        } else if (option == Party.getPartySize() + 1) {
                            Party.saveParty(fileToSaveTo);
                        } else if (option == Party.getPartySize() + 2) {
                            System.out.println("Are you sure?");
                            if (InputChecker.yes(scan.nextLine())) {
                                Party.clear();
                                Party.saveParty(fileToSaveTo);
                                //viewAnother = true;
                            } else {
                                viewAnother = true;
                            }
                        } else {
                            System.out.println("Looks like you have no characters loaded, lets make one!");
                            System.out.println("\nUseful Tips: Enter \"full random\" as a name for a completely random character, or enter \"random\" into any of your character's elements to pick a random option.");
                            System.out.println("\nWhat is your Character's Name?");
                            String charName = scan.nextLine().toLowerCase();
                            if (InputChecker.fullRandom(charName)) {
                                Party.fullRandom();
                            } else {
                                if (InputChecker.random(charName)) {
                                    charName = RandCharacterGenerator.randName();
                                }
                                // This makes the object with a named attribute of the character's name
                                charName = ClassValidator.capitalizeFirst(charName);
                                Party.addCharacter(charName);
                                System.out.println("Let's create " + charName + "!");
                                // This is like the configureSpell method, it lets us set all the internal attributes of the party member
                                // Soon, we will also need a method to print the literal character sheet of the party member,
                                // For right now, the printing of the character sheet is within the createCharacter method
                                // It is on the to do list though
                                System.out.println("At any point, type \"options\" to see examples of valid entries.");
                                Party.createCharacter(Party.returnCharacter(charName));
                            }
                            viewAnother = true;
                        }
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
                            // repeatMenu = false;
                            viewAnother = false;
                            System.out.println("\nYour Spell Book contains the following spells:");
                            System.out.println(Grimoire.printSpellBook());

                            // Configuring a spell means to modify the internal attributes such that you can
                            // actually cast the spell using the spell.castSpell method, called by the returnSpell.castSpell most often
                            System.out.println("\nWhich spell do you want to configure?");
                            // could write a method for this in the SpellBook class like I did with the Party class
                            for (int i = 0; i < Grimoire.getSpellBookSize(); ++i) {
                                System.out.println((i+1)+ ". " + Grimoire.getSpell(i).getSpellName());
                            }
                            System.out.println((Grimoire.getSpellBookSize()+1) + ". Save and move on to casting");
                            System.out.println((Grimoire.getSpellBookSize()+2) + ". Clear spell book");
                            // TODO: add a way to view then straight up exit
                            option = scan.nextInt();
                            scan.nextLine();

                            if (option <= Grimoire.getSpellBookSize()) {
                                Spell curSpell = Grimoire.getSpell(option-1);
                                Grimoire.configureSpellEffects(curSpell);
                                System.out.println("\n\n");
                                curSpell.printSpellCard();
                                viewAnother = true;
                            } else if (option == Grimoire.getSpellBookSize() + 1) {
                                Grimoire.saveSpells(spellFile);
                            } else {
                                Grimoire.clear();
                                Grimoire.saveSpells(spellFile);
                            }
                        } while (viewAnother);
                        do {
                            System.out.println("\nWould you like to cast a spell? Alternatively, you can enter the name of a spell to view its spell card.");
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
                // Character Gameplay
                case 8:
                    do {
                        viewAnother = false;
                        if (Party.getPartySize() != 0) {
                            if (Party.getPartySize() != 0) {
                                System.out.println("\nWhich character would you like to play as?");
                                Party.printPartyOptions();
                                System.out.println((Party.getPartySize() + 1) + ". Exit to Main Menu");
                                option = scan.nextInt();
                                scan.nextLine();
                                if (option <= Party.getPartySize()) {
                                    System.out.println("You are currently playing as: " + Party.getPartyMember(option - 1).getCharName() + "\n\n");
                                    Party.playCharacter(Party.getPartyMember(option - 1));
                                    System.out.println("Sorry, this feature is currently in development.");
                                    viewAnother = true;
                                }
                            }
                        } else {
                            // Should I add these lines to the create Character method
                            System.out.println("Looks like you have no characters loaded, lets make one!");
                            System.out.println("\nUseful Tips: Enter \"full random\" as a name for a completely random character, or enter \"random\" into any of your character's elements to pick a random option.");
                            System.out.println("\nWhat is your Character's Name?");
                            String charName = scan.nextLine().toLowerCase();
                            if (InputChecker.fullRandom(charName)) {
                                Party.fullRandom();
                            } else {
                                if (InputChecker.random(charName)) {
                                    charName = RandCharacterGenerator.randName();
                                }
                                // This makes the object with a named attribute of the character's name
                                charName = ClassValidator.capitalizeFirst(charName);
                                Party.addCharacter(charName);
                                System.out.println("Let's create " + charName + "!");
                                // This is like the configureSpell method, it lets us set all the internal attributes of the party member
                                // Soon, we will also need a method to print the literal character sheet of the party member,
                                // For right now, the printing of the character sheet is within the createCharacter method
                                // It is on the to do list though
                                System.out.println("At any point, type \"options\" to see examples of valid entries.");
                                Party.createCharacter(Party.returnCharacter(charName));
                            }
                            viewAnother = true;
                        }
                    } while (viewAnother);
                    break;
                // Save ALl
                case 9:
                    Grimoire.saveSpells(spellFile);
                    Party.saveParty(fileToSaveTo);
                    System.out.println("\nYour Party and Spellbook have both been saved!");
                    break;
                default:
                    break;
            } //switch ends
        } while (option < 10);
        System.out.println("\nSorry to see you go, hope you're back soon.");
        scan.close();
    }
}
