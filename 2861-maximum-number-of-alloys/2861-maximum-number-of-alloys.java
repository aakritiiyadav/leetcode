class Solution {

    public int maxNumberOfAlloys(int n, int k, int budget,
                                 List<List<Integer>> composition,
                                 List<Integer> stock,
                                 List<Integer> cost) {

        long low = 0;
        long high = 2_000_000_000L;

        while (low < high) {

            long mid = low + (high - low + 1) / 2;

            if (canMake(mid, budget, composition, stock, cost))
                low = mid;
            else
                high = mid - 1;
        }

        return (int) low;
    }

    private boolean canMake(long alloys,
                            int budget,
                            List<List<Integer>> composition,
                            List<Integer> stock,
                            List<Integer> cost) {

        for (List<Integer> machine : composition) {

            long money = 0;

            for (int i = 0; i < stock.size(); i++) {

                long required = (long) machine.get(i) * alloys;

                long buy = Math.max(0, required - stock.get(i));

                money += buy * cost.get(i);

                if (money > budget)
                    break;
            }

            if (money <= budget)
                return true;
        }

        return false;
    }
}