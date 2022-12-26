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

}
