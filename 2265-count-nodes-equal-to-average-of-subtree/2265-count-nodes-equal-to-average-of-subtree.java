class Solution {

    int ans = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return ans;
    }

    // returns [sum, count]
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Left subtree
        int[] left = dfs(node.left);

        // Right subtree
        int[] right = dfs(node.right);

        // Current subtree
        int sum = left[0] + right[0] + node.val;
        int count = left[1] + right[1] + 1;

        // Check average
        if (node.val == sum / count) {
            ans++;
        }

        return new int[]{sum, count};
    }
}