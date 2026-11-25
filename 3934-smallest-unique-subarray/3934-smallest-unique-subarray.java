import java.util.*;

class Solution {

    static final long MOD1 = 1000000007L;
    static final long MOD2 = 1000000009L;

    static final long BASE1 = 911382323L;
    static final long BASE2 = 972663749L;

    long[] h1, h2, p1, p2;

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        h1 = new long[n + 1];
        h2 = new long[n + 1];

        p1 = new long[n + 1];
        p2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;

        // Precompute prefix hashes
        for (int i = 0; i < n; i++) {

            h1[i + 1] = (h1[i] * BASE1 + nums[i]) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + nums[i]) % MOD2;

            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        // Binary Search
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(mid, n)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    boolean check(int len, int n) {

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {

            int j = i + len;

            long hash1 = (h1[j] - h1[i] * p1[len] % MOD1 + MOD1) % MOD1;

            long hash2 = (h2[j] - h2[i] * p2[len] % MOD2 + MOD2) % MOD2;

            String key = hash1 + "#" + hash2;

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        for (int count : map.values()) {

            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}