import java.util.ArrayList;

public class Weapon {
    protected int damageDieSides;
    protected int numDamageDice;
    protected int magicalModifier;
    protected String weaponName;
    protected ArrayList<String> weaponProperties;
    protected boolean finesse;
    // TODO: add a damage type
    public Weapon(int damageDieSides, String weaponName, boolean finesse) {
        this.damageDieSides = damageDieSides;
        if (weaponName.equalsIgnoreCase("greatsword")) {
            numDamageDice = 2;
        } else {
            numDamageDice = 1;
        }
        magicalModifier = 0;
        this.weaponName = weaponName;
        this.finesse = finesse;
    }

    public Weapon(int damageDieSides, String weaponName, int magicalModifier, boolean finesse) {
        this.damageDieSides = damageDieSides;
        if (weaponName.equalsIgnoreCase("greatsword")) {
            numDamageDice = 2;
        } else {
            numDamageDice = 1;
        }
        this.weaponName = weaponName;
        this.magicalModifier = magicalModifier;
        this.finesse = finesse;
    }
    @Override
    public String toString() {
        return damageDieSides + ", " +
                weaponName + ", " +
                magicalModifier + ", " +
                finesse;
    }

    public String formattedString() {
        return ClassValidator.capitalizeFirst(weaponName) + " (" + numDamageDice + "d" + damageDieSides + ")";
    }

    public int getDamageDieSides() {
        return damageDieSides;
    }

    public void setDamageDieSides(int damageDieSides) {
        this.damageDieSides = damageDieSides;
    }

    public int getMagicalModifier() {
        return magicalModifier;
    }

    public void setMagicalModifier(int magicalModifier) {
        this.magicalModifier = magicalModifier;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        weaponName = ClassValidator.capitalizeFirst(weaponName);
        this.weaponName = weaponName;
    }

    public void addProperty(String weaponProperty) {
        weaponProperties.add(weaponProperty);
    }

    public boolean isFinesse() {
        return finesse;
    }

    public void setFinesse(boolean finesse) {
        this.finesse = finesse;
    }

    public int getNumDamageDice() {
        return numDamageDice;
    }

    public void setNumDamageDice(int numDamageDice) {
        this.numDamageDice = numDamageDice;
    }
}
