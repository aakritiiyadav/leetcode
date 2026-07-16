class Solution {

    int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        m = heights.length;
        n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Pacific: Top row
        for (int j = 0; j < n; j++) {
            dfs(0, j, heights, pacific);
        }

        // Pacific: Left column
        for (int i = 0; i < m; i++) {
            dfs(i, 0, heights, pacific);
        }

        // Atlantic: Bottom row
        for (int j = 0; j < n; j++) {
            dfs(m - 1, j, heights, atlantic);
        }

        // Atlantic: Right column
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, heights, atlantic);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    private void dfs(int row, int col, int[][] heights, boolean[][] visited) {

        if (visited[row][col])
            return;

        visited[row][col] = true;

        for (int[] d : dir) {

            int newRow = row + d[0];
            int newCol = col + d[1];

            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n)
                continue;

            // Reverse flow: move only to equal or higher height
            if (heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, heights, visited);
            }
        }
    }
}