/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode reverseBetween(ListNode head, int left, int right) {

        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        // Move prev to node before left
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;
        ListNode tail = curr;

        ListNode prevNode = null;

        // Reverse right-left+1 nodes
        for (int i = 0; i <= right - left; i++) {

            ListNode next = curr.next;

            curr.next = prevNode;

            prevNode = curr;

            curr = next;
        }

        // Reconnect
        prev.next = prevNode;
        tail.next = curr;

        return dummy.next;
    }
}