class Solution {
    public int minSwaps(String s) {

        int unmatched = 0;

        for (char c : s.toCharArray()) {

            if (c == '[') {
                unmatched++;
            }
            else if (unmatched > 0) {
                unmatched--;
            }
        }

        return (unmatched + 1) / 2;
    }
}