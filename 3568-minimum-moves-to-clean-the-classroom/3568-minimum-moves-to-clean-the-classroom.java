import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;

        // Give every litter a bit number
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // All bits = 1 means all litter collected
        int allMask = (1 << litterCount) - 1;

        // State = [row, col, energy, mask]
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{startR, startC, energy, 0});

        // visited[row][col][energy][mask]
        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        visited[startR][startC][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            // Process one BFS level
            while (size-- > 0) {

                int[] curr = q.poll();

                int r = curr[0];
                int c = curr[1];
                int e = curr[2];
                int mask = curr[3];

                // All litter collected
                if (mask == allMask) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    // Need energy to make a move
                    if (e == 0)
                        continue;

                    int newEnergy = e - 1;
                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    // Reset energy
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    if (!visited[nr][nc][newEnergy][newMask]) {

                        visited[nr][nc][newEnergy][newMask] = true;

                        q.offer(new int[]{
                            nr, nc, newEnergy, newMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}