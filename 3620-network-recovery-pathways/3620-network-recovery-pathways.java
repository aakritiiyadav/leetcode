class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int maxW = 0;

        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int[] indegree = new int[n];

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxW = Math.max(maxW, e[2]);
        }

        // topological sort
        List<Integer> topo = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);

            for (int[] edge : graph[u]) {
                int v = edge[0];
                indegree[v]--;
                if (indegree[v] == 0) q.offer(v);
            }
        }

        int low = 0, high = maxW;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (can(mid, graph, topo, online, k, n)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int x, List<int[]>[] graph, List<Integer> topo,
                        boolean[] online, long k, int n) {
        long INF = Long.MAX_VALUE / 2;
        long[] dp = new long[n];
        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int u : topo) {
            if (dp[u] == INF) continue;

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int w = edge[1];

                if (w < x) continue;

                if (v != n - 1 && !online[v]) continue;

                dp[v] = Math.min(dp[v], dp[u] + w);
            }
        }

        return dp[n - 1] <= k;
    }
}