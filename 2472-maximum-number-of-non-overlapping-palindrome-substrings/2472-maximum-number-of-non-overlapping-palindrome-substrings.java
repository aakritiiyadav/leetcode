class Solution {

    int n;
    int k;
    boolean[][] pal;
    int[] dp;

    public int maxPalindromes(String s, int k) {

        this.n = s.length();
        this.k = k;

        pal = new boolean[n][n];

        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {

                if (s.charAt(i) == s.charAt(j) &&
                    (j - i <= 2 || pal[i + 1][j - 1])) {

                    pal[i][j] = true;
                }
            }
        }

        dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return solve(0);
    }

    int solve(int i) {

        if (i >= n) {
            return 0;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        // Skip current character
        int ans = solve(i + 1);

        // Try taking palindrome
        for (int j = i; j < n; j++) {

            if (pal[i][j] && j - i + 1 >= k) {

                ans = Math.max(
                    ans,
                    1 + solve(j + 1)
                );
            }
        }

        return dp[i] = ans;
    }
}