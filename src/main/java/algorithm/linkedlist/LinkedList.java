package algorithm.linkedlist;

public class LinkedList {

    public static class Node {
        int data;
        Node next = null;

        public Node (int data) {
           this.data = data;
        }
    }

    int length = 0; //链表长度
    Node head = new Node(0); // 头结点

    /**
     * 添加到尾部
     * @param data
     */
    public void addNode2Tail(int data) {
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node(data);
    }

    /**
     * 添加到头部
     * @param data
     */
    public void addNode2Head(int data) {
        Node newNode = new Node(data);
        newNode.next = head.next;
        head.next = newNode;
    }

    public void printList() {
        Node tmp = head.next;
        while (tmp != null) {
            System.out.print(tmp.data + " ");
            tmp = tmp.next;
        }
    }
}
