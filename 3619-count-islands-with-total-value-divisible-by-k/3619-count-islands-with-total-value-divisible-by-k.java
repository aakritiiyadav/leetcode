class Solution {

    int m, n;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    long dfs(int[][] grid, int i, int j) {

        // Store the current cell's value
        long sum = grid[i][j];

        // Mark this cell as visited
        grid[i][j] = 0;

        // Explore all four directions
        for (int d = 0; d < 4; d++) {

            int nr = i + dr[d];
            int nc = j + dc[d];

            // Check boundaries and whether the cell is land
            if (nr >= 0 && nr < m &&
                nc >= 0 && nc < n &&
                grid[nr][nc] > 0) {

                sum += dfs(grid, nr, nc);
            }
        }

        return sum;
    }

    public int countIslands(int[][] grid, int k) {

        m = grid.length;
        n = grid[0].length;

        int count = 0;

        // Visit every cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Start DFS for each unvisited island
                if (grid[i][j] > 0) {

                    long islandSum = dfs(grid, i, j);

                    // Check whether the island sum is divisible by k
                    if (islandSum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}