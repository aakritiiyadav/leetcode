class Solution {
public int minGroups(int[][] intervals) {


    // Step 1: Sort by starting point
    Arrays.sort(intervals, (a, b) ->
        Integer.compare(a[0], b[0])
    );

    // Step 2: Min Heap to store ending points
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    // Step 3: Process intervals
    for (int[] interval : intervals) {

        int start = interval[0];
        int end = interval[1];

        // Step 4: Reuse a group if available
        if (!pq.isEmpty() && start > pq.peek()) {
            pq.poll();
        }

        // Step 5: Add current ending point
        pq.offer(end);
    }

    // Step 6: Minimum number of groups
    return pq.size();
}


}
