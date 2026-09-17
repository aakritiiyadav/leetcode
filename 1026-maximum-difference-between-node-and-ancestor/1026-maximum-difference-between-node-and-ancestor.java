class Solution {

    public int maxAncestorDiff(TreeNode root) {
        return dfs(root, root.val, root.val);
    }

    private int dfs(TreeNode node, int minSoFar, int maxSoFar) {

        if (node == null) {
            return 0;
        }

        // Difference with the extreme ancestor values
        int diff = Math.max(
            Math.abs(node.val - minSoFar),
            Math.abs(node.val - maxSoFar)
        );

        // Update information for children
        minSoFar = Math.min(minSoFar, node.val);
        maxSoFar = Math.max(maxSoFar, node.val);

        int left = dfs(node.left, minSoFar, maxSoFar);
        int right = dfs(node.right, minSoFar, maxSoFar);

        return Math.max(diff, Math.max(left, right));
    }
}