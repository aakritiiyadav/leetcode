class Solution {

    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);

        dp[0] = 1;

        return solve(nums, target, dp);
    }

    public int solve(int[] nums, int target, int[] dp) {

        if (target < 0) {
            return 0;
        }

        if (dp[target] != -1) {
            return dp[target];
        }

        int ans = 0;

        for (int num : nums) {
            ans += solve(nums, target - num, dp);
        }

        dp[target] = ans;

        return ans;
    }
}