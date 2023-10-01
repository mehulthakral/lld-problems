import java.util.concurrent.ThreadLocalRandom;

public class Board {

    Cell[][] cells;
    Integer boardLength;

    public Integer getBoardLength() {
        return boardLength;
    }

    public Board(Integer boardLength, Integer numSnakes, Integer numLadders) {
        initialiseBoard(boardLength);
        addSnakesAndLadders(numSnakes, numLadders);
    }

    private void initialiseBoard(Integer boardLength) {
        this.boardLength = boardLength;
        cells = new Cell[boardLength][boardLength];
        for (int i=0; i<boardLength; i++) {
            for (int j=0; j<boardLength; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private void addSnakesAndLadders(Integer numSnakes, Integer numLadders) {
        while (numSnakes > 0) {
            int head = ThreadLocalRandom.current().nextInt(0, boardLength * boardLength);
            int tail = ThreadLocalRandom.current().nextInt(0, boardLength * boardLength);
            if (head <= tail) {
                continue;
            }
            Cell cell = getCell(head);
            cell.setJumpElement(new JumpElement(head, tail));
            System.out.println("Placed Snake at position " + head);
            numSnakes--;
        }

        while (numLadders > 0) {
            int head = ThreadLocalRandom.current().nextInt(0, boardLength * boardLength);
            int tail = ThreadLocalRandom.current().nextInt(0, boardLength * boardLength);
            if (head >= tail) {
                continue;
            }
            Cell cell = getCell(head);
            cell.setJumpElement(new JumpElement(head, tail));
            System.out.println("Placed Ladder at position " + head);
            numLadders--;
        }
    }

    public Cell getCell(int position) {
        if (position < 0 || position >= boardLength*boardLength) {
            return null;
        }
        int x = position % boardLength;
        int y = position / boardLength;
        return cells[x][y];
    }
}
