public class Spell {
    private int spellLevel;
    private int damageDice;
    private int numDice;
    private int numTargets;
    private boolean isSavingThrow;
    private String saveType;
    private boolean didSave;
    private boolean isAttackRoll;
    private boolean isAutomatic;
    private String spellName;

    public Spell() {}

    public Spell(int spellLevel, int damageDice, int numDice, int numTargets, boolean isAutomatic, boolean isSavingThrow, boolean isAttackRoll) {
        this.spellLevel = spellLevel;
        this.damageDice = damageDice;
        this.numDice = numDice;
        this.numTargets = numTargets;
        this.isAutomatic = isAutomatic;
        this.isSavingThrow = isSavingThrow;
        this.isAttackRoll = isAttackRoll;
    }

    public String castSpell (String spellType) {
        String effects = "";
        switch (spellType) {
            case "Attack" -> {
                isAttackRoll = true;
                DiceRoller.rollDice(20,1);
            }
            case "Save" -> {isSavingThrow = true;}
            default -> {isAutomatic = true;}
        }
        return effects;
    }


    public int castSaveSpell(boolean didSave) {
        int damage = DiceRoller.rollDice(damageDice, numDice);
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

    public int getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(int damageDice) {
        this.damageDice = damageDice;
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
}
