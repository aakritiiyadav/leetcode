class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans= new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0,new ArrayList<>(), nums,ans);
        return ans;

    }
    private void backtrack(int start,List<Integer> current, int[]nums,List<List<Integer>> ans){
  ans.add(new ArrayList<>(current));
  for(int i =start;i<nums.length;i++){
    if(i>start&& nums[i]==nums[i-1]){
        continue;
    }
    current.add(nums[i]);
    backtrack(i+1,current,nums,ans);
    current.remove(current.size()-1);
  }
    }
}