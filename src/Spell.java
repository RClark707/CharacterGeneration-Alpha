public class Spell {
    private int spellLevel;
    private int diceSideNumber;
    private int numDice;
    private int numTargets;
    private String damageType;
    private boolean isSavingThrow;
    private String saveType;
    private boolean didSave;
    // Might be useless
    private boolean isAttackRoll;
    private boolean isAutomatic;
    private String spellName;
    private String spellEffects;
    private boolean dealsDamage;

    //private final Spell Fireball = new Spell(3, 6, 8, 0, false, true, false);
    //private final Spell AnimateObjects = new Spell(5,4,10,1,false,false,true);
    //private final Spell InflictWounds = new Spell(1,10,3,1,false,false,true);
    //private final Spell Invisibility = new Spell(2,0,0,0,true,false,false);

   /* public final Spell[] spellList = {
            AnimateObjects,
            Fireball,
            InflictWounds,
            Invisibility,
    };

    */

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
        isAutomatic = false;
    }

    public Spell(int spellLevel, int damageDice, int numDice, int numTargets, boolean isAutomatic, boolean isSavingThrow, boolean isAttackRoll) {
        this.spellLevel = spellLevel;
        this.diceSideNumber = damageDice;
        this.numDice = numDice;
        this.numTargets = numTargets;
        this.isAutomatic = isAutomatic;
        this.isSavingThrow = isSavingThrow;
        this.isAttackRoll = isAttackRoll;
    }

    public static Spell spellBuilder(String spellName) {return new Spell(spellName);}

    public String castSpell(Spell spellName) {
        String effects = "";
        String spellType;
        if (spellName.isAttackRoll) {
            spellType = "Attack";
        } else if (spellName.isSavingThrow) {
            spellType = "Save";
        } else {
            spellType = "Automatic";
        }
        switch (spellType) {
            case "Attack" -> {
                isAttackRoll = true;
                int attackRoll = DiceRoller.rollDice(20,1);
                int damage = DiceRoller.rollDice(diceSideNumber,numDice);
                effects = attackRoll + " to hit, " + damage + " " + damageType +  " damage dealt.";
            }
            case "Save" -> isSavingThrow = true;
            default -> isAutomatic = true;
        }
        return spellEffects + "\n" + effects;
    }

    public int castSaveSpell(boolean didSave) {
        int damage = DiceRoller.rollDice(diceSideNumber, numDice);
        if (isSavingThrow) {
            if (didSave) {
                damage /= 2;
            }
        }
        return damage;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
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

    public boolean isAttackRoll() {
        return isAttackRoll;
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

    public boolean isDidSave() {
        return didSave;
    }

    public void setDidSave(boolean didSave) {
        this.didSave = didSave;
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
}
