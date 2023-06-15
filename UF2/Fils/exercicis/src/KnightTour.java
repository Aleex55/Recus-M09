import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KnightTour {
    private static final int BOARD_SIZE = 8;
    private static final int[] ROW_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] COL_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        solveKnightsTour(board, 0, 0);
        printBoard(board);
    }

    private static void solveKnightsTour(int[][] board, int startRow, int startCol) {
        board[0][0] = 1;
        int moveCount = 1;
        
        int currentRow = startRow;
        int currentCol = startCol;

        while (moveCount < BOARD_SIZE * BOARD_SIZE) {
            ExecutorService executorService = Executors.newFixedThreadPool(8);
            
            int minMoves = Integer.MAX_VALUE;
            int minIndex = -1;

            int[] threadResults = new int[ROW_MOVES.length];

            for (int i = 0; i < ROW_MOVES.length; i++) {
                int finalI = i;
                int nextRow = currentRow + ROW_MOVES[finalI];
                int nextCol = currentCol + COL_MOVES[finalI];

                if (isMoveValid(board, nextRow, nextCol)) {
                    int finalNextRow = nextRow;
                    int finalNextCol = nextCol;
                    executorService.submit(() -> {
                        int numMoves = countAccessibleSquares(board, finalNextRow, finalNextCol);
                        threadResults[finalI] = numMoves;
                    });
                }
            }

            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
            }

            for (int i = 0; i < ROW_MOVES.length; i++) {
                if (threadResults[i] < minMoves && threadResults[i] > 0) {
                    minMoves = threadResults[i];
                    minIndex = i;
                }
            }

            if (minIndex == -1) {
                break;
            } else {
                currentRow += ROW_MOVES[minIndex];
                currentCol += COL_MOVES[minIndex];
                board[currentRow][currentCol] = ++moveCount;
            }
        }
    }

    private static boolean isMoveValid(int[][] board, int row, int col) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == 0);
    }

    private static int countAccessibleSquares(int[][] board, int row, int col) {
        int count = 0;
        for (int i = 0; i < ROW_MOVES.length; i++) {
            int nextRow = row + ROW_MOVES[i];
            int nextCol = col + COL_MOVES[i];
            if (isMoveValid(board, nextRow, nextCol)) {
                count++;
            }
        }
        return count;
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
}
