class Solution {
    private int[][] dp;
    private int[] prefix;
    private int[] nums;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        nums = stoneValue;
        prefix = new int[n + 1];
        dp = new int[n][n];

        // Prefix sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(0, n - 1);
    }

    private int solve(int i, int j) {
        // Only one stone
        if (i >= j) {
            return 0;
        }

        // Already calculated
        if (dp[i][j] != 0) {
            return dp[i][j];
        }

        int ans = 0;

        int left = 0;
        int right = prefix[j + 1] - prefix[i];

        // Try every split
        for (int k = i; k < j; k++) {

            left += nums[k];
            right -= nums[k];

            if (left < right) {

                // This split cannot improve answer
                if (ans >= 2 * left) {
                    continue;
                }

                ans = Math.max(
                    ans,
                    left + solve(i, k)
                );

            } else if (left > right) {

                // Further right sums will only decrease
                if (ans >= 2 * right) {
                    break;
                }

                ans = Math.max(
                    ans,
                    right + solve(k + 1, j)
                );

            } else {

                // Equal sums: Alice can choose either side
                ans = Math.max(
                    ans,
                    Math.max(
                        left + solve(i, k),
                        right + solve(k + 1, j)
                    )
                );
            }
        }

        return dp[i][j] = ans;
    }
}