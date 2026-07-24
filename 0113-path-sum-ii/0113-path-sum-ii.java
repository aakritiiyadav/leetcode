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

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        dfs(root, targetSum, 0, new ArrayList<>());

        return ans;
    }

    private void dfs(TreeNode node, int target, int sum, List<Integer> path) {

        if (node == null)
            return;

        path.add(node.val);

        sum += node.val;

        if (node.left == null && node.right == null) {

            if (sum == target)
                ans.add(new ArrayList<>(path));
        }

        dfs(node.left, target, sum, path);

        dfs(node.right, target, sum, path);

        path.remove(path.size() - 1);
    }
}