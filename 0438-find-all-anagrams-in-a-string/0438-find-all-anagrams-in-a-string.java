
import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<>();

        if (p.length() > s.length()) {
            return ans;
        }

        int[] pFreq = new int[26];
        int[] windowFreq = new int[26];

        // Frequency of characters in p
        for (char c : p.toCharArray()) {
            pFreq[c - 'a']++;
        }

        int left = 0;

        for (int right = 0; right < s.length(); right++) {

            // Add current character to window
            windowFreq[s.charAt(right) - 'a']++;

            // Keep window size equal to p.length()
            if (right - left + 1 > p.length()) {
                windowFreq[s.charAt(left) - 'a']--;
                left++;
            }

            // Check if current window is an anagram
            if (Arrays.equals(pFreq, windowFreq)) {
                ans.add(left);
            }
        }

        return ans;
    }
}

