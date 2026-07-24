class Solution {

    int count = 0;

    HashSet<Integer> cols = new HashSet<>();
    HashSet<Integer> diag1 = new HashSet<>();
    HashSet<Integer> diag2 = new HashSet<>();

    public int totalNQueens(int n) {

        char[][] board = new char[n][n];

        for (char[] row : board)
            Arrays.fill(row, '.');

        backtrack(0, board);

        return count;
    }

    private void backtrack(int row, char[][] board) {

        if (row == board.length) {
            count++;
            return;
        }

        for (int col = 0; col < board.length; col++) {

            if (cols.contains(col) ||
                diag1.contains(row - col) ||
                diag2.contains(row + col))
                continue;

            board[row][col] = 'Q';

            cols.add(col);
            diag1.add(row - col);
            diag2.add(row + col);

            backtrack(row + 1, board);

            board[row][col] = '.';

            cols.remove(col);
            diag1.remove(row - col);
            diag2.remove(row + col);
        }
    }
}