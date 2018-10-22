package algorithm.linkedlist;

/**
 * Created by suneee on 2018/10/16.
 */

/**
 * 1. 单链表反转
 * 2. 链表中环检测
 * 3. 两个有序链表合并
 * 4. 删除链表倒数第n个节点
 * 5. 求链表中间节点
 */
public class LinkedList {

    private static Node head;

    public static void insertToHead(int value) {
        Node node = new Node(value, null);
        insertToHead(node);
    }

    private static void insertToHead(Node newnode) {
        if (head == null) {
            head = newnode;
        } else {
            newnode.next = head;
            head = newnode;
        }
    }

    // 单链表反转
    public static Node reverse(Node list) {
        Node headNode = null;

        Node previousNode = null;
        Node currentNode = list;
        while (currentNode != null){
            Node nextNode = currentNode.next;
            if(nextNode == null){
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return headNode;
    }

    // 检测环
    public static boolean checkCircle(Node list) {
        if(list==null) return false;
        Node fast = list.next;
        Node slow = list;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }

        return false;
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        insertToHead(0);
        insertToHead(1);
        insertToHead(0);
        insertToHead(1);
        insertToHead(0);
        //printAll(head);
        //printAll(reverse(head));
        System.out.println(checkCircle(head));
    }
}
