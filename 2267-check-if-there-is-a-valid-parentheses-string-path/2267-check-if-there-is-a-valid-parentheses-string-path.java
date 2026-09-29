class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // Starting point must be '('
        if (grid[0][0] != '(') {
            return false;
        }

        // dp[r][c][balance] = can we reach (r,c)
        // with this balance?
        boolean[][][] dp = new boolean[m][n][m + n];

        dp[0][0][1] = true;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                for (int balance = 0; balance < m + n; balance++) {

                    if (!dp[r][c][balance]) {
                        continue;
                    }

                    // Move DOWN
                    if (r + 1 < m) {
                        int newBalance = balance;

                        if (grid[r + 1][c] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[r + 1][c][newBalance] = true;
                        }
                    }

                    // Move RIGHT
                    if (c + 1 < n) {
                        int newBalance = balance;

                        if (grid[r][c + 1] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[r][c + 1][newBalance] = true;
                        }
                    }
                }
            }
        }

        // Valid parentheses string must end with balance 0
        return dp[m - 1][n - 1][0];
    }
}