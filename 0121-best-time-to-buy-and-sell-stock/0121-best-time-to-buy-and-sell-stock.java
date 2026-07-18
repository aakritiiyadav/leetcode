class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit=0;
        int mini=prices[0];
        for(int i =1;i<prices.length;i++){
            int profit= prices[i]-mini;
            maxProfit= Math.max(maxProfit, profit);
            mini= Math.min(mini, prices[i]);
        }
        return maxProfit;
    }
}