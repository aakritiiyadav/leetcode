class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // A palindrome can have at most one odd frequency
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Characters available for the left half
        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        int halfLen = n / 2;

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            boolean found = false;

            // Try smallest possible character
            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                // Choose c
                half[c]--;
                left.append((char) ('a' + c));

                // Can we complete it?
                if (possible(left, half, middle, target)) {
                    found = true;
                    break;
                }

                // Undo
                left.deleteCharAt(left.length() - 1);
                half[c]++;
            }

            if (!found) {
                return "";
            }
        }

        return buildPalindrome(left, middle, target);
    }

    private boolean possible(
            StringBuilder left,
            int[] half,
            char middle,
            String target) {

        StringBuilder remaining = new StringBuilder();

        // Largest possible remaining left half
        for (int c = 25; c >= 0; c--) {
            for (int k = 0; k < half[c]; k++) {
                remaining.append((char) ('a' + c));
            }
        }

        StringBuilder fullLeft = new StringBuilder(left);
        fullLeft.append(remaining);

        String palindrome = buildPalindromeString(fullLeft, middle);

        return palindrome.compareTo(target) > 0;
    }

    private String buildPalindrome(
            StringBuilder left,
            char middle,
            String target) {

        String ans = buildPalindromeString(left, middle);

        if (ans.compareTo(target) > 0) {
            return ans;
        }

        return "";
    }

    private String buildPalindromeString(
            StringBuilder left,
            char middle) {

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (middle != 0) {
            ans.append(middle);
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString();
    }
}