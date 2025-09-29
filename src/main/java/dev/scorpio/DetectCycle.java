package dev.scorpio;


public class DetectCycle {

    public static boolean hasCycle(ListNode head) {
        // Your code goes here
        ListNode slow = head;
        ListNode fast = head;

        while(slow!=null && fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }
        return false; // change this based on your implementation
    }

    public static void main(String[] args) {
        // Example setup (optional)
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);

        // Creating a cycle for testing: -4 -> 2
        head.next.next.next.next = head.next;

        boolean result = hasCycle(head);
        System.out.println("Cycle detected: " + result);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}

