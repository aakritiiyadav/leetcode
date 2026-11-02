class Solution {
    public int minMoves2(int[] nums) {
        int ans=0;
        int n =nums.length;
        Arrays.sort(nums);
        int median =nums[n/2];
        for(int num:nums){
            ans+=Math.abs((long)num-median);
        }
        return ans;


    }
}