class Solution {

    class Node {
        int len;
        int leftMax;
        int rightMax;
        int max;

        char leftChar;
        char rightChar;

        Node() {}

        Node(char c) {
            len = 1;
            leftMax = 1;
            rightMax = 1;
            max = 1;
            leftChar = c;
            rightChar = c;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        this.s = s.toCharArray();

        int n = s.length();

        tree = new Node[4 * n];

        for (int i = 0; i < 4 * n; i++) {
            tree[i] = new Node();
        }

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    // Build segment tree
    void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node(s[l]);
            return;
        }

        int mid = l + (r - l) / 2;

        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);

        merge(node, 2 * node, 2 * node + 1);
    }

    // Update one index
    void update(
        int node,
        int l,
        int r,
        int index,
        char ch
    ) {

        if (l == r) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(2 * node, l, mid, index, ch);
        } else {
            update(2 * node + 1, mid + 1, r, index, ch);
        }

        merge(node, 2 * node, 2 * node + 1);
    }

    // Merge left child + right child
    void merge(int node, int left, int right) {

        Node L = tree[left];
        Node R = tree[right];
        Node curr = tree[node];

        curr.len = L.len + R.len;

        curr.leftChar = L.leftChar;
        curr.rightChar = R.rightChar;

        // Prefix
        curr.leftMax = L.leftMax;

        if (L.leftMax == L.len &&
            L.rightChar == R.leftChar) {

            curr.leftMax = L.len + R.leftMax;
        }

        // Suffix
        curr.rightMax = R.rightMax;

        if (R.rightMax == R.len &&
            L.rightChar == R.leftChar) {

            curr.rightMax = R.len + L.rightMax;
        }

        // Best answer inside this segment
        curr.max = Math.max(L.max, R.max);

        // Possible substring crossing boundary
        if (L.rightChar == R.leftChar) {

            curr.max = Math.max(
                curr.max,
                L.rightMax + R.leftMax
            );
        }
    }
}