class Solution {

    Integer[][] dp;

    public int countVowelStrings(int n) {
        dp = new Integer[n + 1][5];

        int ans = 0;

        for (int i = 0; i < 5; i++) {
            ans += solve(n, i);
        }

        return ans;
    }

    private int solve(int len, int vowel) {

        if (len == 1)
            return 1;

        if (dp[len][vowel] != null)
            return dp[len][vowel];

        int ans = 0;

        for (int next = vowel; next < 5; next++) {
            ans += solve(len - 1, next);
        }

        return dp[len][vowel] = ans;
    }
}