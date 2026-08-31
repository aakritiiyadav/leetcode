class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int first = -1;
        int last = -1;

        int minDist = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;

        int pos = 1;

        while (curr.next != null) {

            int nextVal = curr.next.val;

            // Check if curr is a critical point
            if ((curr.val > prev.val && curr.val > nextVal) ||
                (curr.val < prev.val && curr.val < nextVal)) {

                // First critical point
                if (first == -1) {
                    first = pos;
                }

                // Calculate distance from previous critical point
                if (last != -1) {
                    minDist = Math.min(minDist, pos - last);
                }

                last = pos;
            }

            prev = curr;
            curr = curr.next;
            pos++;
        }

        // Less than 2 critical points
        if (first == last) {
            return new int[]{-1, -1};
        }

        int maxDist = last - first;

        return new int[]{minDist, maxDist};
    }
}