class Solution {

    public long repairCars(int[] ranks, int cars) {

        int minRank = Integer.MAX_VALUE;

        for (int rank : ranks)
            minRank = Math.min(minRank, rank);

        long low = 1;
        long high = (long) minRank * cars * cars;

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (canRepair(ranks, cars, mid))
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private boolean canRepair(int[] ranks, int cars, long time) {

        long repaired = 0;

        for (int rank : ranks) {

            repaired += (long) Math.sqrt((double) time / rank);

            if (repaired >= cars)
                return true;
        }

        return false;
    }
}