class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        // Calculate total sum first
        int sum = 0;
        for (int x : stones) {
            sum += x;
        }

        int best = sum;

        // Work backwards
        for (int i = n - 2; i >= 1; i--) {
            sum -= stones[i + 1];

            best = Math.max(best, sum - best);
        }

        return best;
    }
}