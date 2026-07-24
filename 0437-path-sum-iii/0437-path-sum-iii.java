/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    int ans = 0;
    HashMap<Long, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int targetSum) {

        map.put(0L, 1);

        dfs(root, 0, targetSum);

        return ans;
    }

    private void dfs(TreeNode node, long currSum, int target) {

        if (node == null)
            return;

        currSum += node.val;

        ans += map.getOrDefault(currSum - target, 0);

        map.put(currSum, map.getOrDefault(currSum, 0) + 1);

        dfs(node.left, currSum, target);
        dfs(node.right, currSum, target);

        map.put(currSum, map.get(currSum) - 1);

        if (map.get(currSum) == 0)
            map.remove(currSum);
    }
}