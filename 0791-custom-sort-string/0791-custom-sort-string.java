
class Solution {
    public String customSortString(String order, String s) {

        int[] freq = new int[26];

        // Count frequency of characters in s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        // Add characters according to order
        for (char c : order.toCharArray()) {

            while (freq[c - 'a'] > 0) {
                ans.append(c);
                freq[c - 'a']--;
            }
        }

        // Add remaining characters
        for (int i = 0; i < 26; i++) {

            while (freq[i] > 0) {
                ans.append((char) ('a' + i));
                freq[i]--;
            }
        }

        return ans.toString();
    }
}

