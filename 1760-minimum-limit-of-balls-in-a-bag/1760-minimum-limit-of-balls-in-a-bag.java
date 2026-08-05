class Solution {

    public int minimumSize(int[] nums, int maxOperations) {

        int low = 1;
        int high = 0;

        for (int num : nums)
            high = Math.max(high, num);

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (canSplit(nums, maxOperations, mid))
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private boolean canSplit(int[] nums, int maxOperations, int limit) {

        long operations = 0;

        for (int balls : nums) {
            operations += (balls - 1) / limit;
        }

        return operations <= maxOperations;
    }
}