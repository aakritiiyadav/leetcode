import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        Deque<int[]> dq = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            int num = nums[i];
            int count = 1;

            // Merge smaller segments
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] p = dq.pollLast();

                count += p[1];

                cost += (long) (num - p[0]) * p[1];
            }

            dq.offerLast(new int[]{num, count});

            // Maintain cost <= k
            while (cost > k) {

                int[] p = dq.pollFirst();

                int val = p[0];
                int cnt = p[1];

                cost -= (long) (val - nums[j]);

                j--;

                if (cnt > 1) {
                    dq.offerFirst(new int[]{val, cnt - 1});
                }
            }

            // Count valid subarrays
            ans += j - i + 1;
        }

        return ans;
    }
}