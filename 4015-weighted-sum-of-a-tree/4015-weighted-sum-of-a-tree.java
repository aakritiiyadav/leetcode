import java.util.*;

class Solution {

    public long weightedSum(int[] parent, int[] nums) {

        int n = parent.length;

        // Build tree
        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        // depth[i] = depth of node i
        int[] depth = new int[n];

        depth[0] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        int height = 1;

        // BFS
        while (!q.isEmpty()) {

            int node = q.poll();

            for (int child : children[node]) {

                depth[child] = depth[node] + 1;

                height = Math.max(height, depth[child]);

                q.offer(child);
            }
        }

        // Calculate answer
        long ans = 0;

        for (int i = 0; i < n; i++) {

            long weight =
                (long) nums[i] * (height - depth[i] + 1);

            ans += weight;
        }

        return ans;
    }
}