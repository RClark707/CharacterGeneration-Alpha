import java.util.Random;

public class DiceRoller {

    private static final Random rand = new Random();
    //adding final was a suggested edit. Not important

    // This will roll any number of any sided dice, even d3's!
    public static int rollDice(int sideNumber, int numTimes) {
        int rollValue = 0;

        for (int i = 0; i < numTimes; ++i) {
            rollValue += (rand.nextInt(sideNumber) + 1);
        }

        return rollValue;
    }

    // This rolls 1 single stat using the 4d6 method
    public static int rollStat() {
        int statScore;

        int a = DiceRoller.rollDice(6,1);
        int b = DiceRoller.rollDice(6,1);
        int c = DiceRoller.rollDice(6,1);
        int d = DiceRoller.rollDice(6,1);
        int statSum = a + b + c + d;
        statScore = statSum - Math.min(Math.min(a,b),Math.min(c,d));

        return statScore;
    }

    // This uses the above method to roll 6 stats and concatenate them into a string with a Total
    public static String rollAllStats(boolean includeTotal) {
        String statString = "";
        int totalScore = 0;
        int currentStatRoll;

        for (int i = 1; i <= 6; ++i) {
            currentStatRoll = DiceRoller.rollStat();
            totalScore += currentStatRoll;
            // using += is valid and works, but seems to be bad practice or weird.
            statString = statString.concat(String.valueOf(currentStatRoll));
            statString = statString.concat(" ");
        }

        if (includeTotal) {
            statString += "  Total: " + totalScore;
        }

        return statString;
    }

    // This is the same as the above, but it sets a minimum total threshold and shows the Total
    // change to array?
    public static String rollGoodStats(int threshold) {
        String statString;
        int totalScore;
        int currentStatRoll;

        do {
            statString = "";
            totalScore = 0;
            for (int i = 1; i <= 6; ++i) {
                currentStatRoll = DiceRoller.rollStat();
                totalScore += currentStatRoll;
                statString = statString.concat(String.valueOf(currentStatRoll));
                statString = statString.concat(" ");
            }
        } while (totalScore < threshold);
        statString += "  Total: " + totalScore;
        return statString;
    }

    public static int computeAbilityModifier(int abilityScore) {
        return Math.floorDiv(abilityScore - 10,2);
    }

    public static void rollWeaponAttack(PartyMember partyMember, Weapon weapon, boolean advantage, boolean disadvantage, int numAttacks) {
        // weapon attack roll
        for (int i = 0; i < numAttacks; ++i) {
            int attackRoll = rollDice(20, 1);
            if (advantage) {
                int secondAttackRoll = rollDice(20, 1);
                attackRoll = Math.max(attackRoll, secondAttackRoll);
            } else if (disadvantage) {
                int secondAttackRoll = rollDice(20, 1);
                attackRoll = Math.min(attackRoll, secondAttackRoll);
            }
            boolean criticalMiss = attackRoll == 1;
            boolean criticalHit = attackRoll == 20;
            // check that boolean initializer out, wow.

            int statIndex = 0;
            if (weapon.isFinesse()) {
                statIndex = 1;
            }
            int attackingAbilityModifier = computeAbilityModifier(partyMember.retrieveStat(statIndex));
            int proficiencyBonus = Math.floorDiv(2 + (partyMember.getCharLevel() - 1), 4);
            // The formula for the Proficiency Bonus is {2 + (Total Level-1)/4}Rounded Down or 1 + (total level/4)Rounded up, both formulas will give the same results.
            int attackModifier = attackingAbilityModifier + proficiencyBonus;
            // the first part gets you the ability score modifier, not yet incorporating finesse
            // the second part gets you the proficiency bonus using a predetermined formula
            attackRoll += attackModifier;

            int damage = rollDice(weapon.getDamageDieSides(), weapon.getNumDamageDice());
            if (criticalHit) {
                // roll it again for a critical hit
                damage += rollDice(weapon.getDamageDieSides(), weapon.getNumDamageDice());
            }
            damage += attackingAbilityModifier;

            if (!criticalMiss) {
                if (criticalHit) {
                    System.out.print("\nCRITICAL HIT! " + attackRoll + " to hit. Damage: " + damage + " damage-type damage!");
                }
                System.out.println("\n" + attackRoll + " to hit. Damage: " + damage + " damage-type damage!");
            } else {
                System.out.println("\nCritical Miss... :(");
            }
        }
    }

    protected static int rollSkillCheck(String skillName,PartyMember partyMember) {
        int diceRoll = DiceRoller.rollDice(20,1);
        if (RandCharacterGenerator.proficiencyList.isEmpty()) {
            RandCharacterGenerator.establishProficiencyList();
        }
        if (RandCharacterGenerator.proficiencyList.containsKey(skillName)) {
            diceRoll += DiceRoller.computeAbilityModifier(partyMember.retrieveStat(RandCharacterGenerator.proficiencyList.get(skillName)));
        } else {
            diceRoll += DiceRoller.computeAbilityModifier(partyMember.retrieveStat(partyMember.retrieveStatIndex(InputChecker.shortToLong(skillName))));
        }

        if (partyMember.proficiencies.contains(skillName)) {
            diceRoll += DiceRoller.computeProficiencyBonus(partyMember.getCharLevel());
        }
        return diceRoll;
    }

    protected static int rollSavingThrow(String statName,PartyMember partyMember) {
        int diceRoll = DiceRoller.rollDice(20,1);
        diceRoll += DiceRoller.computeAbilityModifier(partyMember.retrieveStat(partyMember.retrieveStatIndex(statName)));
        if (RandCharacterGenerator.returnSavingThrowProficienciesByClass(partyMember.getCharClass()).contains(statName)) {
            diceRoll += DiceRoller.computeProficiencyBonus(partyMember.getCharLevel());
        }
        return diceRoll;
    }

    public static int computeProficiencyBonus(int charLevel) {
        return Math.floorDiv(2 + (charLevel - 1), 4);
    }
}
