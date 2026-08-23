class Solution {
    public boolean sumGame(String num) {

        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQ = 0;
        int rightQ = 0;

        // Left half
        for (int i = 0; i < n / 2; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                leftQ++;
            } else {
                leftSum += ch - '0';
            }
        }

        // Right half
        for (int i = n / 2; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                rightQ++;
            } else {
                rightSum += ch - '0';
            }
        }

        int qDiff = leftQ - rightQ;
        int sumDiff = leftSum - rightSum;

        // Odd number of unmatched '?' -> Alice wins
        if (qDiff % 2 != 0) {
            return true;
        }

        // Bob wins only if the exact difference can be compensated
        return sumDiff != 9 * (rightQ - leftQ) / 2;
    }
}