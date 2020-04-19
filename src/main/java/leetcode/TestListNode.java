package leetcode;

public class TestListNode {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        int carry = 0; // 进位
        while (l1 != null || l2 != null || carry != 0) {
            ListNode node = new ListNode(0);
            int sum = ((l1 == null) ? 0 : l1.val) + ((l2 == null) ? 0 : l2.val) + carry;
            node.val = sum % 10;
            carry = sum / 10;
            head.next = node;
            head = node;

            l1 = (l1 == null) ? l1 : l1.next;
            l2 = (l2 == null) ? l2 : l2.next;
        }
        return prev.next;
    }


     class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }
}
