class Solution {

    class Pair {
        int left;
        int right;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    int ans = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root);
        return ans;
    }

    private Pair dfs(TreeNode root) {

        if (root == null)
            return new Pair(-1, -1);

        Pair left = dfs(root.left);
        Pair right = dfs(root.right);

        int leftZigZag = left.right + 1;
        int rightZigZag = right.left + 1;

        ans = Math.max(ans, Math.max(leftZigZag, rightZigZag));

        return new Pair(leftZigZag, rightZigZag);
    }
}