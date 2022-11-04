import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SpellBook {
    ArrayList<Spell> spellBook = new ArrayList<>();
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    public void addSpell(String spellName) {
        spellBook.add(Spell.spellBuilder(spellName));
    }

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

    public Spell randSpellName() {
        return spellBook.get(rand.nextInt(spellBook.size()));
    }

    public String printSpellBook() {
        String spells = "";
        for (Spell spell : spellBook) {
            spells = spells.concat(spell.getSpellName() + ", ");
        }
        return spells;
    }



}
