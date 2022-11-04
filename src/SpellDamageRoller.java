public class SpellDamageRoller {

    // Conveniently, with some more testing, this method should be redundant. the castSpell method theoretically can handle it
    public static int animateObjects(int level, int armorClass) {
        int damage = 0;
        int numHits = 0;
        int diceRoll;
        int numCrits = 0;

        // make sure we have a valid spell level
        if (level >= 5) {
            for (int i = 0; i < (10 + ((level - 5) * 2)); ++i) {
                diceRoll = DiceRoller.rollDice(20, 1) + 8;

                if (diceRoll >= armorClass) {
                    numHits++;
                    if (diceRoll == 28) {
                        numCrits++;
                    }
                }
            } // end of loop

            damage = DiceRoller.rollDice(4, numHits) + (4 * numHits) + DiceRoller.rollDice(4, numCrits);
        } // end of if statement
        return damage;
    } // end of method
}
