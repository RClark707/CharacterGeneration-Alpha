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
        String effects = "Unknown Effects";
        for (Spell spell : spellBook) {
            if (spellName.equals(spell)) {
                while (spell.getSpellLevel() == -1) {
                    System.out.println("What level is this spell?");
                    spell.setSpellLevel(scan.nextInt());
                    scan.nextLine();
                }
                while (spell.getDiceSideNumber() == -1) {
                    System.out.println("How many sides do the dice rolled for this spell have?");
                    spell.setDiceSideNumber(scan.nextInt());
                }
                while (spell.getNumDice() == -1) {
                    System.out.println("How many of those dice will you roll?");
                    spell.setNumDice(scan.nextInt());
                    scan.nextLine();
                }
                // Should I add a while/if statement here too?
                System.out.println("Does this spell deal damage?");
                String input = scan.nextLine();
                if (InputChecker.yes(input)) {
                    spell.setDealsDamage(true);
                    while (spell.getDamageType() == null) {
                        System.out.println("What is the damage type of this spell?");
                        spell.setDamageType(scan.nextLine());
                    }
                    while (spell.getHigherLevelDamageDice() == -1 && spell.getHigherLevelAttacksIncrease() == 0) {
                        System.out.println("Does your spell make additional attacks or roll more damage dice at higher levels? (Enter \"damage\" or \"attacks\")");
                        String hasHigherLevelEffects = scan.nextLine();
                        if (!InputChecker.no(hasHigherLevelEffects)) {
                            if (hasHigherLevelEffects.equals("attacks") || hasHigherLevelEffects.equals("Attacks")) {
                                spell.setHigherLevelAttacksIncrease(scan.nextInt());
                                scan.nextLine();
                            } else {
                                System.out.println("How many additional dice does this spell roll for each spell slot level beyond the spell's level?");
                                spell.setHigherLevelDamageDice(scan.nextInt());
                                scan.nextLine();
                            }
                        }
                    }
                } else {
                    while (effects.equals(spell.getSpellEffects())) {
                        System.out.println("What are the effects of this spell?");
                        spell.setSpellEffects(scan.nextLine());
                    }
                }
                while (spell.getNumTargets() == -5) {
                    System.out.println("How many targets does this spell have? (Enter -1 for Area of Effect)");
                    spell.setNumTargets(scan.nextInt());
                    scan.nextLine();
                }
                if (!spell.isSavingThrow()) {
                    System.out.println("Does this spell require a saving throw?");
                    spell.setSavingThrow(InputChecker.yes(scan.nextLine()));
                    if (spell.isSavingThrow()) {
                        System.out.println("What type of saving throw does it require?");
                        String saveType = scan.nextLine();
                        if (ClassValidator.isValidStat(saveType)) {
                            saveType = ClassValidator.capitalizeFirst(saveType);
                            spell.setSaveType(saveType);
                        }
                    }
                }
                if (spell.isNotAttackRoll() && !spell.isSavingThrow()) {
                    System.out.println("Does this spell require an attack roll?");
                    spell.setAttackRoll(InputChecker.yes(scan.nextLine()));
                    while (spell.getNumAttacks() == 0) {
                        System.out.println("How many attacks does this spell make at once?");
                        spell.setNumAttacks(scan.nextInt());
                        scan.nextLine();
                    }
                    while (spell.getAttackModifier() == -1) {
                        System.out.println("What is the attack modifier of your spell, if applicable?");
                        spell.setAttackModifier(scan.nextInt());
                        scan.nextLine();
                    }
                    while (spell.getDamageModifier() == -1) {
                        System.out.println("What is the damage modifier of your spell, if applicable? (This is used per instance of damage)");
                        spell.setDamageModifier(scan.nextInt());
                        scan.nextLine();
                    }
                }
                if (spell.isNotAttackRoll() && !spell.isSavingThrow()) {
                    spell.setAutomatic(true);
                }
            }
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
