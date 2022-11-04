public class Race {
    // TODO: Make this useful?????
    private static final int[] abilityScoreIncrease = new int[2];
    private String raceName;

    public Race(int large, int small, String raceName) {
        abilityScoreIncrease[0] = large;
        abilityScoreIncrease[1] = small;
        this.raceName = raceName;
    }

    public Race(int large, int small) {
        abilityScoreIncrease[0] = large;
        abilityScoreIncrease[1] = small;
    }

    public static void setAbilityScoreIncrease(int large, int small) {
        abilityScoreIncrease[0] = large;
        abilityScoreIncrease[1] = small;
    }

    public static int getAbilityScoreIncrease0() {
        return abilityScoreIncrease[0];
    }

    public static int getAbilityScoreIncrease1() {
        return abilityScoreIncrease[1];
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceName() {
        return raceName;
    }


}
