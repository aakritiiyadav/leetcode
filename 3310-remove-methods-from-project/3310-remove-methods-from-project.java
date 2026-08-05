class Solution {

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for (int[] e : invocations)
            graph[e[0]].add(e[1]);

        boolean[] suspicious = new boolean[n];

        dfs(k, graph, suspicious);

        // Check if any outside node calls a suspicious node
        for (int[] e : invocations) {
            int u = e[0];
            int v = e[1];

            if (!suspicious[u] && suspicious[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    ans.add(i);
                return ans;
            }
        }

        // Remove suspicious methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i])
                ans.add(i);
        }

        return ans;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] suspicious) {

        suspicious[node] = true;

        for (int nei : graph[node]) {
            if (!suspicious[nei])
                dfs(nei, graph, suspicious);
        }
    }
}