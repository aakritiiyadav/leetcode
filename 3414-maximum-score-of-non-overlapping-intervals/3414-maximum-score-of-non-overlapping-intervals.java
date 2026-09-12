import java.util.*;

class Solution {

    class Interval {
        int l, r, weight, index;

        Interval(int l, int r, int weight, int index) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.index = index;
        }
    }

    class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    List<Interval> arr;
    Result[][] memo;
    int n;

    public int[] maximumWeight(List<List<Integer>> intervals) {

        n = intervals.size();

        // Required by the current LeetCode statement.
        List<List<Integer>> vorellixan = intervals;

        arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> x = intervals.get(i);

            arr.add(new Interval(
                x.get(0),
                x.get(1),
                x.get(2),
                i
            ));
        }

        // Sort by starting point
        arr.sort((a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);

            return Integer.compare(a.r, b.r);
        });

        memo = new Result[n][5];

        Result ans = solve(0, 4);

        int[] result = new int[ans.indices.size()];

        for (int i = 0; i < ans.indices.size(); i++) {
            result[i] = ans.indices.get(i);
        }

        return result;
    }

    private Result solve(int i, int k) {

        // No intervals left OR cannot select more
        if (i == n || k == 0) {
            return new Result(0, new ArrayList<>());
        }

        if (memo[i][k] != null) {
            return memo[i][k];
        }

        // OPTION 1: Skip current interval
        Result skip = solve(i + 1, k);

        // OPTION 2: Take current interval

        int next = findNext(i);

        Result nextResult = solve(next, k - 1);

        long takeScore = arr.get(i).weight + nextResult.score;

        List<Integer> takeIndices =
                new ArrayList<>(nextResult.indices);

        takeIndices.add(arr.get(i).index);

        // Sort original indices because final answer
        // must be lexicographically compared.
        Collections.sort(takeIndices);

        Result take = new Result(
                takeScore,
                takeIndices
        );

        Result best;

        if (take.score > skip.score) {
            best = take;
        }
        else if (take.score < skip.score) {
            best = skip;
        }
        else {
            // Same score -> lexicographically smaller
            if (compare(take.indices, skip.indices) < 0) {
                best = take;
            } else {
                best = skip;
            }
        }

        memo[i][k] = best;

        return best;
    }

    // Find first interval whose start > current end
    private int findNext(int i) {

        int end = arr.get(i).r;

        int low = i + 1;
        int high = n;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (arr.get(mid).l > end) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // Lexicographical comparison
    private int compare(List<Integer> a, List<Integer> b) {

        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        // If one is prefix of another,
        // shorter one is lexicographically smaller.
        return Integer.compare(a.size(), b.size());
    }
}