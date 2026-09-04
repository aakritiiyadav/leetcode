class Solution {
    public int firstStableIndex(int[] nums, int k) {

        int n = nums.length;

        // Suffix minimum
        int[] right = new int[n];

        right[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }

        // Prefix maximum
        int leftMax = 0;

        for (int i = 0; i < n; i++) {

            leftMax = Math.max(leftMax, nums[i]);

            int instability = leftMax - right[i];

            if (instability <= k) {
                return i;
            }
        }

        return -1;
    }
}