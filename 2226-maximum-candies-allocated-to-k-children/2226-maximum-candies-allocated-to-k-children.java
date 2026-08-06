class Solution {

    public int maximumCandies(int[] candies, long k) {

        int low = 0;
        int high = 0;

        for (int pile : candies)
            high = Math.max(high, pile);

        while (low < high) {

            int mid = low + (high - low + 1) / 2;

            if (canDistribute(candies, k, mid))
                low = mid;
            else
                high = mid - 1;
        }

        return low;
    }

    private boolean canDistribute(int[] candies, long k, int each) {

        long children = 0;

        for (int pile : candies) {

            children += pile / each;

            if (children >= k)
                return true;
        }

        return false;
    }
}