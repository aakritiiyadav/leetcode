class Solution {

    int[][] dp;
    int[] suffix;
    int n;

    public int stoneGameII(int[] piles) {

        n = piles.length;

        // Suffix sum
        suffix = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        // dp[i][M]
        dp = new int[n][n + 1];

        // -1 means not calculated
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], -1);
        }

        return solve(0, 1);
    }

    private int solve(int i, int M) {

        // No piles left
        if (i >= n) {
            return 0;
        }

        // Already calculated
        if (dp[i][M] != -1) {
            return dp[i][M];
        }

        int best = 0;

        // Take X piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int newM = Math.max(M, X);

            int opponent = solve(i + X, newM);

            int currentPlayer = suffix[i] - opponent;

            best = Math.max(best, currentPlayer);
        }

        return dp[i][M] = best;
    }
}