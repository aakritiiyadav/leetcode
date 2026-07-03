class Solution {
    public int minCut(String s) {
        int n = s.length();

        // palindrome table
        boolean[][] pal = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) &&
                   (j - i <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;

            for (int j = i; j < n; j++) {
                if (pal[i][j]) {
                    min = Math.min(min, 1 + dp[j + 1]);
                }
            }

            dp[i] = min;
        }

        return dp[0] - 1; 
    }
}