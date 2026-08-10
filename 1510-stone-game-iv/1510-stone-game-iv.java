
class Solution {
    public boolean winnerSquareGame(int n) {

        // dp[i] = true if current player can win
        // when there are i stones
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false
        // No stones -> no move -> lose

        for (int i = 1; i <= n; i++) {

            // Try removing every possible perfect square
            for (int j = 1; j * j <= i; j++) {

                int remaining = i - j * j;

                // If opponent reaches a losing state,
                // current player wins
                if (!dp[remaining]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}

