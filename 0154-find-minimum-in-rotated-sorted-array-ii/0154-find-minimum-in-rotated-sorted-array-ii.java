class Solution {
    public int findMin(int[] nums) {
        int m=nums[0];
        for(int num:nums){
            m=Math.min(m,num);
        }
        return m;
    }
}