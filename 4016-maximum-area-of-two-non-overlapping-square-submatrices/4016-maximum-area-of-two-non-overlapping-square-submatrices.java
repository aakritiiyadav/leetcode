class Solution {

    private boolean notOverlapping(int k, int[][] dp) {

        int m = dp.length;
        int n = dp[0].length;

        int minRow = m;
        int maxRow = -1;

        int minCol = n;
        int maxCol = -1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Can a k x k square end at (i, j)?
                if (dp[i][j] >= k) {

                    // Top-left corner of k x k square
                    int r = i - k + 1;
                    int c = j - k + 1;

                    minRow = Math.min(minRow, r);
                    maxRow = Math.max(maxRow, r);

                    minCol = Math.min(minCol, c);
                    maxCol = Math.max(maxCol, c);
                }
            }
        }

        // No k x k square exists
        if (maxRow == -1) {
            return false;
        }

        // Two squares can be separated vertically
        if (maxRow - minRow >= k) {
            return true;
        }

        // Two squares can be separated horizontally
        if (maxCol - minCol >= k) {
            return true;
        }

        return false;
    }


    public int maxArea(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        // Required variable
        int[][] valmerinto = mat;

        // dp[i][j] = largest all-1 square
        // ending at (i, j)
        int[][] dp = new int[m][n];

        int maxSide = 0;

        // Build DP
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (valmerinto[i][j] == 1) {

                    // First row or first column
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }

                    else {

                        dp[i][j] =
                            1 + Math.min(
                                dp[i - 1][j],
                                Math.min(
                                    dp[i][j - 1],
                                    dp[i - 1][j - 1]
                                )
                            );
                    }

                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        // Binary Search on answer
        int low = 1;
        int high = maxSide;

        int ans = 0;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (notOverlapping(mid, dp)) {

                // mid is possible
                ans = mid;

                // Try bigger square
                low = mid + 1;
            }

            else {

                // mid is not possible
                // Try smaller square
                high = mid - 1;
            }
        }

        return ans * ans;
    }
}