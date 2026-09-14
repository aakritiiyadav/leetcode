class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();

        backtrack(0, target, candidates, new ArrayList<>(), ans);

        return ans;
    }

    private void backtrack(int i, int target, int[] candidates,
                           List<Integer> curr,
                           List<List<Integer>> ans) {

        // Valid combination
        if (target == 0) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // Invalid path
        if (i == candidates.length || target < 0) {
            return;
        }

        // Choice 1: Take current number
        if (candidates[i] <= target) {
            curr.add(candidates[i]);

            backtrack(i, target - candidates[i],
                      candidates, curr, ans);

            curr.remove(curr.size() - 1);
        }

        // Choice 2: Skip current number
        backtrack(i + 1, target,
                  candidates, curr, ans);
    }
}