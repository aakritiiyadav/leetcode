class Solution {

    public int findNthDigit(int n) {

        long N = n;

        long digits = 1;
        long count = 9;
        long start = 1;

        // Find the digit-length block
        while (N > digits * count) {

            N -= digits * count;

            digits++;
            count *= 10;
            start *= 10;
        }

        // Find the actual number
        long num = start + (N - 1) / digits;

        // Find which digit inside that number
        int index = (int)((N - 1) % digits);

        String s = String.valueOf(num);

        return s.charAt(index) - '0';
    }
}