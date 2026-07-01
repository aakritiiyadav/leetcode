class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];
        Queue<int[]> q = new LinkedList<>();

        // initialize distances
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // add all thieves to queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        // Multi-source BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int[] d : dir) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n
                        && dist[nr][nc] == Integer.MAX_VALUE) {
                    dist[nr][nc] = dist[cur[0]][cur[1]] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        // Max Heap for Dijkstra
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> b[2] - a[2]
        );

        boolean[][] visited = new boolean[n][n];
        pq.offer(new int[]{0, 0, dist[0][0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int r = cur[0];
            int c = cur[1];
            int safe = cur[2];

            if (r == n - 1 && c == n - 1)
                return safe;

            if (visited[r][c]) continue;
            visited[r][c] = true;

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
                    int newSafe = Math.min(safe, dist[nr][nc]);
                    pq.offer(new int[]{nr, nc, newSafe});
                }
            }
        }

        return 0;
    }
}