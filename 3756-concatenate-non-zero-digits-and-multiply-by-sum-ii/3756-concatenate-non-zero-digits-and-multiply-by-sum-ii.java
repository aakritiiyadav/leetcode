class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Prefix count of non-zero digits
        int[] cnt = new int[n + 1];

        // Prefix sum of non-zero digits
        int[] sum = new int[n + 1];

        // Extract non-zero digits
        ArrayList<Integer> digits = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            cnt[i + 1] = cnt[i];
            sum[i + 1] = sum[i];

            if (ch != '0') {
                cnt[i + 1]++;
                sum[i + 1] += ch - '0';
                digits.add(ch - '0');
            }
        }

        int m = digits.size();

        // powers of 10
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Rolling hash of concatenated digits
        long[] hash = new long[m + 1];
        for (int i = 0; i < m; i++) {
            hash[i + 1] = (hash[i] * 10 + digits.get(i)) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = cnt[l];
            int right = cnt[r + 1];

            int len = right - left;

            if (len == 0) {
                ans[i] = 0;
                continue;
            }

            long number = (hash[right] - hash[left] * pow10[len]) % MOD;
            if (number < 0) number += MOD;

            long digitSum = sum[r + 1] - sum[l];

            ans[i] = (int) ((number * digitSum) % MOD);
        }

        return ans;
    }
}