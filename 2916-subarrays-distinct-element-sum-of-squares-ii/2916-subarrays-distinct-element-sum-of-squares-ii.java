class Solution {
static final int MOD = 1_000_000_007;


class SegmentTree {
    long[] sum, sumSq, lazy;

    SegmentTree(int n) {
        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];
    }

    void apply(int node, int l, int r, long val) {
        long len = r - l + 1L;
        long oldSum = sum[node];

        sumSq[node] = (sumSq[node] + 2L * val % MOD * oldSum % MOD + val * val % MOD * len % MOD) % MOD;
        sum[node] = (oldSum + val * len) % MOD;
        lazy[node] += val;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;

        apply(node * 2, l, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, r, lazy[node]);

        lazy[node] = 0;
    }

    void update(int node, int l, int r, int ql, int qr) {
        if (l > qr || r < ql) return;

        if (ql <= l && r <= qr) {
            apply(node, l, r, 1);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    long query(int node, int l, int r, int ql, int qr) {
        if (l > qr || r < ql) return 0;

        if (ql <= l && r <= qr) return sumSq[node];

        push(node, l, r);

        int mid = (l + r) / 2;

        return (query(node * 2, l, mid, ql, qr) + query(node * 2 + 1, mid + 1, r, ql, qr)) % MOD;
    }
}

public int sumCounts(int[] nums) {
    int n = nums.length;
    int[] last = new int[100001];
    java.util.Arrays.fill(last, -1);

    SegmentTree tree = new SegmentTree(n);
    long ans = 0;

    for (int i = 0; i < n; i++) {
        int prev = last[nums[i]];
        tree.update(1, 0, n - 1, prev + 1, i);
        ans = (ans + tree.query(1, 0, n - 1, 0, i)) % MOD;
        last[nums[i]] = i;
    }

    return (int) ans;
}

}
