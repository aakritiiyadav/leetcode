class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        // Frequency of characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Stores how many characters are left
        // after matching target's prefix
        int[] used = freq.clone();

        // Try to match target from left to right
        for (int i = 0; i < n; i++) {

            int cur = target.charAt(i) - 'a';

            // Use target[i] if possible
            if (used[cur] > 0) {
                used[cur]--;
            } else {
                // Cannot continue matching target.
                // We will handle the answer using the
                // last possible larger position.
                break;
            }
        }

        // Now try every position from RIGHT to LEFT.
        // We want the rightmost position that can be increased.
        for (int i = n - 1; i >= 0; i--) {

            // Rebuild remaining frequency for prefix target[0...i-1]
            int[] remain = freq.clone();

            boolean possible = true;

            for (int j = 0; j < i; j++) {

                int c = target.charAt(j) - 'a';

                if (remain[c] == 0) {
                    possible = false;
                    break;
                }

                remain[c]--;
            }

            if (!possible) {
                continue;
            }

            int cur = target.charAt(i) - 'a';

            // Find the smallest character > target[i]
            for (int c = cur + 1; c < 26; c++) {

                if (remain[c] > 0) {

                    // Use this larger character
                    remain[c]--;

                    StringBuilder ans = new StringBuilder();

                    // Same prefix as target
                    ans.append(target, 0, i);

                    // Make this position slightly larger
                    ans.append((char) ('a' + c));

                    // Remaining characters in sorted order
                    for (int x = 0; x < 26; x++) {
                        while (remain[x] > 0) {
                            ans.append((char) ('a' + x));
                            remain[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}