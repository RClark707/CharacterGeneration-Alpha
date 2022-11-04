public class Spell {
    private final String spellName;
    private int spellLevel;
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


    public Spell() {
        spellName = "Unnamed Spell";
        spellLevel = -1;
        spellEffects = "Unknown Effects";
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

    public Spell(String spellName) {
        this.spellName = spellName;
        spellLevel = -1;
        spellEffects = "Unknown Effects";
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


    public String spellBeingCast(Spell spellName) {
        return spellName.castSpell();
    }
    public String castSpell() {
        String effects = "";

        if (!dealsDamage) {
            effects = spellEffects;
            //+ effects.concat(" With " + numTargets + " targets.");
        // If the spell requires an attack roll, or more than one attack roll.
        } else if (isAttackRoll) {
            for (int i = 0; i < numAttacks; ++i) {
                effects = effects.concat((DiceRoller.rollDice(20,1) + attackModifier) + " to hit, damage: ");
                effects = effects.concat(String.valueOf(DiceRoller.rollDice(diceSideNumber,numDice) + damageModifier));
                effects = effects.concat(" " + damageType + " damage.\n");
            }
        // if the Spell is a saving throw, we'll output half damage for a successful save, in case that applies.
        // Some spells deal no damage on a successful save, we don't really account for that, just hope that the user knows the difference.
        } else if (isSavingThrow) {
            effects = effects.concat("Saving throw type: " + saveType + "\n");
            int damage = DiceRoller.rollDice(diceSideNumber,numDice) + damageModifier;
            effects = effects.concat(damage + damageType + " damage on a failed save.\n");
            effects = effects.concat((damage / 2) + damageType + " damage on a successful save.\n");
        } else if (isAutomatic) {
            // For spells that are automatic and deal damage, AKA Magic missile, this formula ought to work, but I can only think of Magic Missile and Cloud of Daggers, so it might not work for everything.
            effects = effects.concat(String.valueOf(DiceRoller.rollDice(diceSideNumber, numDice) + damageModifier));
            effects = effects.concat(" " + damageType + " damage.\n");
        }
        return effects;
    }

    // Method for printing all the attributes of a certain spell. still working on it.
    public String print() {
        System.out.println();
        return "";
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

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public String getSaveType() {
        return saveType;
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

    public boolean isDealsDamage() {
        return dealsDamage;
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
}
