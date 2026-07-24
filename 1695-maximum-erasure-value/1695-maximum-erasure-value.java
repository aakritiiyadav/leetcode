class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        HashSet<Integer> map= new HashSet<>();
         int left = 0;
        int sum = 0;
        int ans = 0;
        for(int right=0;right<n;right++){
            while(map.contains(nums[right])){
                map.remove(nums[left]);
            sum-=nums[left];
            left++;
            

        }
        map.add(nums[right]);
        sum+=nums[right];
        ans= Math.max(sum,ans);


        }
        return ans;
        
    }
}