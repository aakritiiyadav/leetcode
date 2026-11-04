import java.util.*;

class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void addLeft(int val) {
            left.merge(val, 1, Integer::sum);
            leftSize++;
            leftSum += val;
        }

        void addRight(int val) {
            right.merge(val, 1, Integer::sum);
            rightSize++;
            rightSum += val;
        }

        void removeLeft(int val) {
            left.merge(val, -1, Integer::sum);
            if (left.get(val) == 0) {
                left.remove(val);
            }
            leftSize--;
            leftSum -= val;
        }

        void removeRight(int val) {
            right.merge(val, -1, Integer::sum);
            if (right.get(val) == 0) {
                right.remove(val);
            }
            rightSize--;
            rightSum -= val;
        }

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addLeft(val);
            } else {
                addRight(val);
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeLeft(val);
            } else {
                removeRight(val);
            }
            balance();
        }

        void balance() {
            int target = (leftSize + rightSize + 1) / 2;

            while (leftSize > target) {
                int val = left.lastKey();
                removeLeft(val);
                addRight(val);
            }

            while (leftSize < target) {
                int val = right.firstKey();
                removeRight(val);
                addLeft(val);
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * leftSize - leftSum
                 + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {

            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        // Step 2: DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        // Step 3: Choose k non-overlapping windows
        for (int t = 0; t <= k; t++) {

            for (int i = 0; i <= n; i++) {

                if (dp[t][i] == INF) {
                    continue;
                }

                // Option 1: Skip current index
                if (i < n) {
                    dp[t][i + 1] = Math.min(
                        dp[t][i + 1],
                        dp[t][i]
                    );
                }

                // Option 2: Select window of size x
                if (t < k && i + x <= n) {

                    dp[t + 1][i + x] = Math.min(
                        dp[t + 1][i + x],
                        dp[t][i] + cost[i]
                    );
                }
            }
        }

        return dp[k][n];
    }
}