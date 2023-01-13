public class Spell {
    private final String spellName;
    private int spellLevel;
    private int higherLevelDamageDice;
    private int higherLevelAttacksIncrease;
    private int diceSideNumber;
    private int numDice;
    private int numTargets;
    private boolean dealsDamage;
    private String damageType;
    private boolean isSavingThrow;
    private String saveType;
    private boolean isAttackRoll;
    private int numAttacks;
    private int attackModifier;
    private int damageModifier;
    private boolean isAutomatic;
    private String spellEffects;
    private String spellSchool;


    public Spell(String spellName) {
        this.spellName = spellName;
        spellLevel = -1;
        higherLevelDamageDice = -1;
        higherLevelAttacksIncrease = 0;
        spellEffects = null;
        numTargets = -5;
        diceSideNumber = -1;
        numDice = -1;
        dealsDamage = false;
        damageType = null;
        isSavingThrow = false;
        saveType = null;
        isAttackRoll = false;
        numAttacks = 0;
        attackModifier = -1;
        damageModifier = -1;
        isAutomatic = false;
    }

    public static Spell spellBuilder(String spellName) {return new Spell(spellName);}

    public Spell(String spellName, int spellLevel, int higherLevelDamageDice, int higherLevelAttacksIncrease, int diceSideNumber, int numDice, int numTargets,
                 boolean dealsDamage, String damageType, boolean isSavingThrow, String saveType, boolean isAttackRoll, int numAttacks, int attackModifier,
                 int damageModifier, boolean isAutomatic, String spellEffects, String spellSchool) {
        this.spellName = spellName;
        this.spellLevel = spellLevel;
        this.higherLevelDamageDice = higherLevelDamageDice;
        this.higherLevelAttacksIncrease = higherLevelAttacksIncrease;
        this.diceSideNumber = diceSideNumber;
        this.numDice = numDice;
        this.numTargets = numTargets;
        this.dealsDamage = dealsDamage;
        this.damageType = damageType;
        this.isSavingThrow = isSavingThrow;
        this.saveType = saveType;
        this.isAttackRoll = isAttackRoll;
        this.numAttacks = numAttacks;
        this.attackModifier = attackModifier;
        this.damageModifier = damageModifier;
        this.isAutomatic = isAutomatic;
        this.spellEffects = spellEffects;
        this.spellSchool = spellSchool;
    }

    public String castSpell(int spellLevel) {
        // TODO: total damage does not match damage dealt
        // This method is used to cast a given spell, normally, it will be used by calling the returnSpell method with an input from the user as to the spell's name,
        // and then that spell object will determine the "effects" that get run
        int difference = spellLevel - this.spellLevel;
        // Make sure the spell was actually cast at a valid level, or higher
        if (difference >= 0) {
            String effects = "";
            higherLevelAttacksIncrease *= difference;
            higherLevelDamageDice *= difference;
            if (!dealsDamage) {
                effects = spellEffects;
                //+ effects.concat(" With " + numTargets + " targets.");
                // If the spell requires an attack roll, or more than one attack roll.
            } else if (isAttackRoll) {
                int damageTotal = 0;
                int perAttackDamage;
                for (int i = 0; i < (numAttacks + higherLevelAttacksIncrease); ++i) {
                    effects = effects.concat((DiceRoller.rollDice(20,1) + attackModifier) + " to hit, damage: ");
                    perAttackDamage = DiceRoller.rollDice(diceSideNumber,(numDice + higherLevelDamageDice)) + damageModifier;
                    damageTotal += perAttackDamage;
                    effects = effects.concat(String.valueOf(perAttackDamage));
                    effects = effects.concat(" " + damageType + " damage.\n");
                }
                effects = "Total damage: " + damageTotal + "\n" + effects;
                // if the Spell is a saving throw, we'll output half damage for a successful save, in case that applies.
                // Some spells deal no damage on a successful save, we don't really account for that, just hope that the user knows the difference.
            } else if (isSavingThrow) {
                effects = effects.concat("Saving throw type: " + saveType + "\n");
                int damage = DiceRoller.rollDice(diceSideNumber,(numDice + higherLevelDamageDice)) + damageModifier;
                effects = effects.concat(damage + " " + damageType + " damage on a failed save.\n");
                effects = effects.concat((damage / 2) + " " + damageType + " damage on a successful save.\n");
            } else if (isAutomatic) {
                // For spells that are automatic and deal damage, AKA Magic missile, this formula ought to work, but I can only think of Magic Missile and Cloud of Daggers, so it might not work for everything.
                effects = effects.concat(String.valueOf(DiceRoller.rollDice(diceSideNumber, numDice) + damageModifier));
                effects = effects.concat(" " + damageType + " damage.\n");
            }
            return effects;
        }
        return "Unknown Effects, or you cast the spell at too low of a level";
    }

    public void printSpellCard() {
        System.out.println(spellName);
        System.out.printf("Level %-3s %s\n",spellLevel,ClassValidator.capitalizeFirst(spellSchool));
        if (isSavingThrow) {
            System.out.printf("This spell can be resisted with a %s saving throw.\n",saveType);
            if (dealsDamage) {
                System.out.println("This usually means that the spell deals half damage to creatures that succeed the save, and full damage to those that fail.");
            } else {
                System.out.println("This usually means that the spell does not take effect on your target(s).");
            }
        }
        if (dealsDamage) {
            System.out.print("This spell deals " + numDice + "d" + diceSideNumber + " " + damageType + " damage");
            if (numTargets != -1) {
                System.out.println(" to " + numTargets + " target(s).");
            } else {
                System.out.println(" in an area.");
            }
        } else {
            System.out.println(spellEffects);
            System.out.print("You may target ");
            if (numTargets != -1) {
                System.out.println(numTargets + " target(s) with this spell.");
            } else {
                System.out.println("a group of creatures with this spell.");
            }
        }
        System.out.print("This spell ");
        if (higherLevelDamageDice != -1) {
            System.out.print("rolls an extra " + higherLevelDamageDice);
            if (higherLevelDamageDice == 1) {
                System.out.print(" die");
            } else {
                System.out.print(" dice");
            }
            System.out.println(" for each spell slot level beyond " + spellLevel + ".");
        } else if (higherLevelAttacksIncrease != 0) {
            System.out.print("makes " + higherLevelAttacksIncrease + " extra ");
            if (higherLevelAttacksIncrease == 1) {
                System.out.print("attack");
            } else {
                System.out.print("attacks");
            }
            System.out.println(" for each spell slot level beyond " + spellLevel + ".");
        } else {
            System.out.println("doesn't seem to get stronger at higher levels, or it has non-damaging effects.");
        }
    }

    @Override
    public String toString() {
        return spellName + ", " +
        spellLevel + ", " +
        higherLevelDamageDice + ", " +
        higherLevelAttacksIncrease + ", " +
        diceSideNumber + ", " +
        numDice + ", " +
        numTargets + ", " +
        dealsDamage + ", " +
        damageType + ", " +
        isSavingThrow + ", " +
        saveType + ", " +
        isAttackRoll + ", " +
        numAttacks + ", " +
        attackModifier + ", " +
        damageModifier + ", " +
        isAutomatic + ", " +
        spellEffects + ", " +
        spellSchool;
    }

    public String formattedString() {
        return spellName;
    }

    public String getSpellName() {
        return spellName;
    }

    public int getSpellLevel() {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel) {
        this.spellLevel = spellLevel;
    }

    public int getDiceSideNumber() {
        return diceSideNumber;
    }

    public void setDiceSideNumber(int diceSideNumber) {
        this.diceSideNumber = diceSideNumber;
    }

    public int getNumDice() {
        return numDice;
    }

    public void setNumDice(int numDice) {
        this.numDice = numDice;
    }

    public int getNumTargets() {
        return numTargets;
    }

    public void setNumTargets(int numTargets) {
        this.numTargets = numTargets;
    }

    public boolean isSavingThrow() {
        return isSavingThrow;
    }

    public void setSavingThrow(boolean savingThrow) {
        isSavingThrow = savingThrow;
    }

    public boolean isNotAttackRoll() {
        return !isAttackRoll;
    }

    public void setAttackRoll(boolean attackRoll) {
        isAttackRoll = attackRoll;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public void setSaveType(String saveType) {
        this.saveType = saveType;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getSpellEffects() {
        return spellEffects;
    }

    public void setSpellEffects(String spellEffects) {
        this.spellEffects = spellEffects;
    }

    public void setDealsDamage(boolean dealsDamage) {
        this.dealsDamage = dealsDamage;
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getHigherLevelDamageDice() {
        return higherLevelDamageDice;
    }

    public void setHigherLevelDamageDice(int higherLevelDamageDice) {
        this.higherLevelDamageDice = higherLevelDamageDice;
    }

    public int getHigherLevelAttacksIncrease() {
        return higherLevelAttacksIncrease;
    }

    public void setHigherLevelAttacksIncrease(int higherLevelAttacksIncrease) {
        this.higherLevelAttacksIncrease = higherLevelAttacksIncrease;
    }

    public String getSpellSchool() {
        return spellSchool;
    }

    public void setSpellSchool(String spellSchool) {
        this.spellSchool = spellSchool;
    }
}
