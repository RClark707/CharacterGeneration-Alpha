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
                String saveType = scan.nextLine();
                if (ClassValidator.isValidStat(saveType)) {
                    saveType = ClassValidator.capitalizeFirst(saveType);
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
