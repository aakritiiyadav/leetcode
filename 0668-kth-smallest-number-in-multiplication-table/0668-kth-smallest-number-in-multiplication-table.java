class Solution {

    public int findKthNumber(int m, int n, int k) {

        long low = 1;
        long high = (long) m * n;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            long count = 0;

            // Count numbers <= mid
            for (int i = 1; i <= m; i++) {

                count += Math.min(
                    (long) n,
                    mid / i
                );
            }

            if (count >= k) {

                // mid can be the answer
                high = mid - 1;

            } else {

                // Need a bigger number
                low = mid + 1;
            }
        }

        return (int) low;
    }
}