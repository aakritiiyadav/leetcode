
import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        // Step 1: Build original tree
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        // Step 2: Find new parents
        int[] newParent = new int[n];
        int[] last = new int[26];
        int[] previous = new int[n];

        Arrays.fill(last, -1);

        int[] stack = new int[2 * n];
        int top = 0;

        stack[top++] = 0;

        while (top > 0) {

            int event = stack[--top];

            if (event >= 0) {

                int u = event;
                int c = s.charAt(u) - 'a';

                previous[u] = last[c];

                if (u == 0) {
                    newParent[u] = -1;
                } else {
                    newParent[u] = last[c] == -1
                            ? parent[u]
                            : last[c];
                }

                last[c] = u;

                stack[top++] = ~u;

                for (int v : tree[u]) {
                    stack[top++] = v;
                }

            } else {

                int u = ~event;
                int c = s.charAt(u) - 'a';

                last[c] = previous[u];
            }
        }

        // Step 3: Build modified tree
        List<Integer>[] modified = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            modified[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            modified[newParent[i]].add(i);
        }

        // Step 4: Calculate subtree sizes
        int[] order = new int[n];
        top = 0;

        stack[top++] = 0;

        int idx = 0;

        while (top > 0) {

            int u = stack[--top];

            order[idx++] = u;

            for (int v : modified[u]) {
                stack[top++] = v;
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        for (int i = n - 1; i >= 0; i--) {

            int u = order[i];

            if (newParent[u] != -1) {
                ans[newParent[u]] += ans[u];
            }
        }

        return ans;
    }
}
