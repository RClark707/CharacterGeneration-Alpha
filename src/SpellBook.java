import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SpellBook {
    ArrayList<Spell> spellBook = new ArrayList<>();
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    // The addSpell method takes in a String, because that is the easiest method of user input,
    // and then uses the String in conjunction with the spellBuilder method, located within the Spell class,
    // to construct a new spell with a name being the String input we originally had
    public void addSpell(String spellName) {
        spellBook.add(Spell.spellBuilder(spellName));
    }
    // Based on a String for a spell name, we can return the correct spell from the spellBook array, most often the Grimoire array.
    public Spell returnSpell(String spellName) {
        //spellName = spellName.replaceAll("\\s", "");
        for (Spell curSpell : spellBook) {
            if (spellName.equals(curSpell.getSpellName())) {
                return curSpell;
            }
        }
        return null;
    }

    public int getSpellBookSize() {
        return spellBook.size();
    }

    public Spell getSpell(int index) {
        return spellBook.get(index);
    }

    /*
    public int getSpellIndex(Spell s) {
        return spellBook.indexOf(s);
    }

     */

    public void saveSpells(String spellFile) throws FileNotFoundException {
        // alternatively can use the FileOutputStream in conjunction with the print writer
        try (PrintWriter printer = new PrintWriter(spellFile)) {
            for (Spell s : spellBook) {
                printer.print(s.toString() + "\n");
            }
        }
    }

    public int countLines(String spellFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(spellFile));
        int numLines = 0;
        while (scan.hasNextLine()) {
            scan.nextLine();
            numLines++;
        }
        return numLines;
    }

    public void initializeSpellBook(String spellFile) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(spellFile));
        do {
            if (scan.hasNextLine()) {
               String spellName = scan.nextLine();
               int spellLevel = Integer.parseInt(scan.nextLine());
               int higherLevelDamageDice = Integer.parseInt(scan.nextLine());
               int higherLevelAttacksIncrease = Integer.parseInt(scan.nextLine());
               int diceSideNumber = Integer.parseInt(scan.nextLine());
               int numDice = Integer.parseInt(scan.nextLine());
               int numTargets = Integer.parseInt(scan.nextLine());
               boolean dealsDamage = Boolean.parseBoolean(scan.nextLine());
               String damageType = scan.nextLine();
               boolean isSavingThrow = Boolean.parseBoolean(scan.nextLine());
               String saveType = scan.nextLine();
               boolean isAttackRoll = Boolean.parseBoolean(scan.nextLine());
               int numAttacks = Integer.parseInt(scan.nextLine());
               int attackModifier = Integer.parseInt(scan.nextLine());
               int damageModifier = Integer.parseInt(scan.nextLine());
               boolean isAutomatic = Boolean.parseBoolean(scan.nextLine());
               String spellEffects = scan.nextLine();
               String spellSchool = scan.nextLine();
               spellBook.add(new Spell(spellName, spellLevel, higherLevelDamageDice, higherLevelAttacksIncrease, diceSideNumber, numDice, numTargets,
                       dealsDamage, damageType, isSavingThrow, saveType, isAttackRoll, numAttacks,
                       attackModifier, damageModifier, isAutomatic, spellEffects, spellSchool));
            }
            scan.nextLine();
        } while (scan.hasNextLine());
        scan.close();
        Scanner defaultScanner = new Scanner(new FileInputStream("defaultSpells.txt"));
        do {
            if (defaultScanner.hasNextLine()) {
                String spellName = defaultScanner.nextLine();
                int spellLevel = Integer.parseInt(defaultScanner.nextLine());
                int higherLevelDamageDice = Integer.parseInt(defaultScanner.nextLine());
                int higherLevelAttacksIncrease = Integer.parseInt(defaultScanner.nextLine());
                int diceSideNumber = Integer.parseInt(defaultScanner.nextLine());
                int numDice = Integer.parseInt(defaultScanner.nextLine());
                int numTargets = Integer.parseInt(defaultScanner.nextLine());
                boolean dealsDamage = Boolean.parseBoolean(defaultScanner.nextLine());
                String damageType = defaultScanner.nextLine();
                boolean isSavingThrow = Boolean.parseBoolean(defaultScanner.nextLine());
                String saveType = defaultScanner.nextLine();
                boolean isAttackRoll = Boolean.parseBoolean(defaultScanner.nextLine());
                int numAttacks = Integer.parseInt(defaultScanner.nextLine());
                int attackModifier = Integer.parseInt(defaultScanner.nextLine());
                int damageModifier = Integer.parseInt(defaultScanner.nextLine());
                boolean isAutomatic = Boolean.parseBoolean(defaultScanner.nextLine());
                String spellEffects = defaultScanner.nextLine();
                String spellSchool = defaultScanner.nextLine();
                spellBook.add(new Spell(spellName, spellLevel, higherLevelDamageDice, higherLevelAttacksIncrease, diceSideNumber, numDice, numTargets,
                        dealsDamage, damageType, isSavingThrow, saveType, isAttackRoll, numAttacks,
                        attackModifier, damageModifier, isAutomatic, spellEffects, spellSchool));
            }
            defaultScanner.nextLine();
        } while (defaultScanner.hasNextLine());
        defaultScanner.close();
    }

    public void clear() {
        spellBook.clear();
    }

    // This method changes the inner bits and bobs of the spell you want to make. Often, we will use the returnSpell method as a parameter to actually input a Spell object as the parameter.
    public void configureSpellEffects(Spell spellName) {
        while (spellName.getSpellLevel() == -1) {
           System.out.println("What level is this spell?");
           spellName.setSpellLevel(scan.nextInt());
           scan.nextLine();
        }
        while (spellName.getSpellSchool() == null) {
            System.out.println("What school of magic is this spell from? (Enter \"Options\" to see some examples of schools of magic)");
            String spellSchool = scan.nextLine();
            if (InputChecker.options(spellSchool)) {
                RandCharacterGenerator.printOptions("Schools of Magic",null);
                System.out.println("What school of magic is this spell from?");
                spellSchool = scan.nextLine();
            }
            spellName.setSpellSchool(spellSchool);
        }
        while (spellName.getDiceSideNumber() == -1) {
            System.out.println("How many sides do the dice rolled for this spell have?");
            spellName.setDiceSideNumber(scan.nextInt());
        }
        while (spellName.getNumDice() == -1) {
            System.out.println("How many of those dice will you roll?");
            spellName.setNumDice(scan.nextInt());
            scan.nextLine();
        }
        // Should I add a while/if statement here too?
        System.out.println("Does this spell deal damage?");
        String input = scan.nextLine();
        if (InputChecker.yes(input)) {
            spellName.setDealsDamage(true);
            while (spellName.getDamageType() == null) {
                System.out.println("What is the damage type of this spell?");
                spellName.setDamageType(scan.nextLine());
            }
            while (spellName.getHigherLevelDamageDice() == -1 && spellName.getHigherLevelAttacksIncrease() == 0) {
                System.out.println("Does your spell make additional attacks or roll more damage dice at higher levels? (Enter \"damage\" or \"attacks\")");
                String hasHigherLevelEffects = scan.nextLine();
                if (!InputChecker.no(hasHigherLevelEffects)) {
                    if (hasHigherLevelEffects.equals("attacks") || hasHigherLevelEffects.equals("Attacks")) {
                        System.out.println("How many attacks does this spell make for each spell slot level beyond the spell's level?");
                        spellName.setHigherLevelAttacksIncrease(scan.nextInt());
                        scan.nextLine();
                    } else {
                        System.out.println("How many additional dice does this spell roll for each spell slot level beyond the spell's level?");
                        spellName.setHigherLevelDamageDice(scan.nextInt());
                        scan.nextLine();
                    }
                }
            }
        } else {
            while (spellName.getSpellEffects() == null) {
                System.out.println("What are the effects of this spell?");
                spellName.setSpellEffects(scan.nextLine());
            }
        }
        while (spellName.getNumTargets() == -5) {
            System.out.println("How many targets does this spell have? (Enter -1 for Area of Effect)");
            spellName.setNumTargets(scan.nextInt());
            scan.nextLine();
        }
        if (!spellName.isSavingThrow()) {
            System.out.println("Does this spell require a saving throw?");
            spellName.setSavingThrow(InputChecker.yes(scan.nextLine()));
            if (spellName.isSavingThrow()) {
                // Could add a while loop to make sure that the save type is not null, but that's whatever
                System.out.println("What type of saving throw does it require?");
                String saveType = InputChecker.shortToLong(scan.nextLine());
                if (ClassValidator.isValidStat(saveType)) {
                    spellName.setSaveType(saveType);
                }
            }
        }
        if (spellName.isNotAttackRoll() && !spellName.isSavingThrow()) {
            System.out.println("Does this spell require an attack roll?");
            spellName.setAttackRoll(InputChecker.yes(scan.nextLine()));
            while (spellName.getNumAttacks() == 0) {
                System.out.println("How many attacks does this spell make at once?");
                spellName.setNumAttacks(scan.nextInt());
                scan.nextLine();
            }
            while (spellName.getAttackModifier() == -1) {
                System.out.println("What is the attack modifier of your spell, if applicable?");
                spellName.setAttackModifier(scan.nextInt());
                scan.nextLine();
            }
            while (spellName.getDamageModifier() == -1) {
                System.out.println("What is the damage modifier of your spell, if applicable? (This is used per instance of damage)");
                spellName.setDamageModifier(scan.nextInt());
                scan.nextLine();
            }
        }
        if (spellName.isNotAttackRoll() && !spellName.isSavingThrow()) {
            spellName.setAutomatic(true);
        }
    }

    // This gives us a random Spell name from the selection in our spell book
    public String randSpellName() {
    // This method will grab a random spell from our spell book, and then return the name of that spell
        return spellBook.get(rand.nextInt(spellBook.size())).getSpellName();
    }

    // This prints out the contents of our spell book by spell name
    public String printSpellBook() {
        // prints the names of each spell in the book
        String spells = "";
        for (int i = 0; i < spellBook.size(); ++i) {
            Spell spell = spellBook.get(i);
            if (i != spellBook.size() - 1) {
                spells = spells.concat(spell.getSpellName() + ", ");
            } else {
                spells = spells.concat(spell.getSpellName());
            }
        }
        return spells;
    }



}
