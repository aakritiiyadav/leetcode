import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        long[] diff = new long[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs((long) nums1[i] - nums2[i]);
            sum += diff[i];
        }

        if (sum <= k) return 0;

        Arrays.sort(diff);

        int max = (int) diff[n - 1];

        int[] freq = new int[max + 1];

        for (long d : diff) {
            freq[(int) d]++;
        }

        for (int d = max; d > 0 && k > 0; d--) {
            if (freq[d] == 0) continue;

            long count = freq[d];
            long operations = Math.min(k, count);

            freq[d] -= (int) operations;
            freq[d - 1] += (int) operations;
            k -= operations;
        }

        long ans = 0;

        for (int d = 0; d <= max; d++) {
            ans += (long) d * d * freq[d];
        }

        return ans;
    }
}