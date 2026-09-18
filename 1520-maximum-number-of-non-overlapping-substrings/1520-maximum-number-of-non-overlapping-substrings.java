import java.util.*;

class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Step 1: Find first and last occurrence
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';

            if (first[ch] == -1) {
                first[ch] = i;
            }

            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Step 2: Create smallest valid interval for every character
        for (int ch = 0; ch < 26; ch++) {

            if (first[ch] == -1) {
                continue;
            }

            int start = first[ch];
            int end = last[ch];

            boolean valid = true;

            for (int i = start; i <= end; i++) {

                int curr = s.charAt(i) - 'a';

                // This character started before our interval
                if (first[curr] < start) {
                    valid = false;
                    break;
                }

                // We need to expand the interval
                end = Math.max(end, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Step 3: Sort by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> ans = new ArrayList<>();

        int prevEnd = -1;

        // Step 4: Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                ans.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return ans;
    }
}