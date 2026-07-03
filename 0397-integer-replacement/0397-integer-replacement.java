class Solution {
    Map<Long, Integer> memo = new HashMap<>();

    public int integerReplacement(int n) {
        return solve((long)n);
    }

    private int solve(long n) {
        if (n == 1) return 0;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int ans;

        if (n % 2 == 0) {
            ans = 1 + solve(n / 2);
        } else {
            ans = 1 + Math.min(
                solve(n - 1),
                solve(n + 1)
            );
        }

        memo.put(n, ans);
        return ans;
    }
}