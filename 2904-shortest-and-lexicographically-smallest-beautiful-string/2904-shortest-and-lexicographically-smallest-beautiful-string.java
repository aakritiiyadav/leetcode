class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Too many ones
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Remove unnecessary leading zeros
            while (ones == k && s.charAt(left) == '0') {
                left++;
            }

            // We have a beautiful substring
            if (ones == k) {

                String candidate = s.substring(left, right + 1);

                if (ans.equals("")
                        || candidate.length() < ans.length()
                        || (candidate.length() == ans.length()
                            && candidate.compareTo(ans) < 0)) {

                    ans = candidate;
                }
            }
        }

        return ans;
    }
}