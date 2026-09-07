class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        long[] dp = new long[s.length() + 1];
        long[] last = new long[26];

        // Empty subsequence
        dp[0] = 1;

        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';

            // Every old subsequence:
            // 1. doesn't take current character
            // 2. takes current character
            dp[i + 1] = (2 * dp[i]) % MOD;

            // Remove duplicates created by previous occurrence
            dp[i + 1] = (dp[i + 1] - last[c] + MOD) % MOD;

            // Store current contribution for this character
            last[c] = dp[i];
        }

        // Remove empty subsequence
        return (int) ((dp[s.length()] - 1 + MOD) % MOD);
    }
}