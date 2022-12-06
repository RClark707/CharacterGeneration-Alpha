import java.util.ArrayList;
import java.util.Scanner;

public class Weapon {
    int damageDieSides;
    int numDamageDice;
    int magicalModifier;
    String weaponName;
    //String[] weaponProperties;


    public Weapon() {}

    public Weapon(int damageDieSides, String weaponName) {
        this.damageDieSides = damageDieSides;
        numDamageDice = 1;
        magicalModifier = 0;
        this.weaponName = weaponName;
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
    /*
    public String[] getWeaponProperties() {
        return weaponProperties;
    }



    public void setWeaponProperties(ArrayList<String> weaponProperties) {
        this.weaponProperties = weaponProperties;
    }
    */

    public int getNumDamageDice() {
        return numDamageDice;
    }

    public void setNumDamageDice(int numDamageDice) {
        this.numDamageDice = numDamageDice;
    }
}
