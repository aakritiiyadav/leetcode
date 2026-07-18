class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0];
        int max= nums[0];
        int n = nums.length;
        for(int i =0; i<n;i++){
            if(nums[i]>max){
                max=nums[i];
            }
            if(nums[i]<min){
                min=nums[i];
            }
        }
        while(min!=0){
            int temp= max;
            max=min;
            min=temp%min;
        }
        return max;
    }
}