import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

    Board board;
    Deque<Player> playersList;
    Dice dice;
    Player winner;

    public Game(Integer boardLength, Integer numPlayers, Integer numDice) {
        board = new Board(boardLength, 5, 4);
        playersList = new LinkedList<>();
        addPlayers(numPlayers);
        dice = new Dice(numDice);
    }

    private void addPlayers(Integer numPlayers) {
        for(int i = 0; i < numPlayers; i++) {
            Player player = new Player(i, 0);
            playersList.add(player);
        }
    }

    public void startGame() {
        System.out.println("Welcome to Snake & Ladder!");
        while (winner == null) {
            Player nextTurnPlayer = getNextTurnPlayer();
            System.out.printf("Next turn is of Player %d, Current Position is %d %n", nextTurnPlayer.getPlayerId(),
                    nextTurnPlayer.getPosition());
            Integer diceOutput = dice.rollDice();
            Integer nextPosition = getNextPosition(nextTurnPlayer.getPosition(), diceOutput);
            nextTurnPlayer.setPosition(nextPosition);
            System.out.printf("Player %d got %d diceOutput and next position is %d %n", nextTurnPlayer.getPlayerId(),
                    diceOutput, nextPosition);
            if (nextPosition >= board.getBoardLength() * board.getBoardLength()) {
                winner = nextTurnPlayer;
            }
        }
        System.out.println("Winner is Player " + winner.getPlayerId());
    }

    private Integer getNextPosition(Integer currentPosition, Integer diceOutput) {
        Integer nextPosition = currentPosition + diceOutput;
        if (nextPosition >= board.getBoardLength() * board.getBoardLength()) {
            return nextPosition;
        }

        if (board.getCell(nextPosition).getJumpElement() != null) {
            nextPosition = board.getCell(nextPosition).getJumpElement().getEnd();
        }
        return nextPosition;
    }

    private Player getNextTurnPlayer() {
        Player nextTurnPlayer = playersList.pollFirst();
        playersList.addLast(nextTurnPlayer);
        return nextTurnPlayer;
    }
}
