import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    Integer count;
    int min = 1;
    int max = 6;

    public Dice(Integer count) {
        this.count = count;
    }

    public Integer rollDice() {
        int rollCount = count;
        Integer diceRollSum = 0;
        while(rollCount > 0) {
            diceRollSum += ThreadLocalRandom.current().nextInt(min,max+1);
            rollCount--;
        }
        return diceRollSum;
    }
}
