class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at
        // previous index whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {

            long[] next = new long[k];

            int mod = num % k;

            // Start a new subarray with only num
            next[mod]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                int newRemainder = (int) ((long) r * mod % k);

                next[newRemainder] += dp[r];
            }

            // Add all subarrays ending here
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans;
    }
}