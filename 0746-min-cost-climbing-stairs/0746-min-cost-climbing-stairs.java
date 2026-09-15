class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        Arrays.fill(dp,-1);
        return Math.min(solve(0,cost,dp),solve(1,cost,dp));

    }
    public int solve(int i, int[]cost,int[]dp){
        if(i>=cost.length)return 0;
        if(dp[i]!=-1)return dp[i];
        dp[i] = cost[i] + Math.min(
            solve(i + 1, cost, dp),
            solve(i + 2, cost, dp)
        );
        return dp[i];
    }
}