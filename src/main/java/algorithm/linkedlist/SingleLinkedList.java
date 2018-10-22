package algorithm.linkedlist;

/**
 * Created by suneee on 2018/10/16.
 */

/**
 * 1. 单链表的插入,删除,查找
 * 2. 链表存储int类型
 */
public class SingleLinkedList {

    private Node head = null;

    // 通过value查找
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    // 通过index查找
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            pos++;
        }
        return p;
    }

    // 在头结点插入数据
    public void insertToHead(int value) {
        Node node = new Node(value, null);
        insertToHead(node);
    }

    private void insertToHead(Node newnode) {
        if (head == null) {
            head = newnode;
        } else {
            newnode.next = head;
            head = newnode;
        }
    }

    public void insertToAfter(Node p, int value) {
        Node node = new Node(value, null);
        insertToAfter(p, node);
    }

    private void insertToAfter(Node p, Node newnode) {
        if (p == null) return;
        newnode.next = p.next;
        p.next = newnode;
    }

    public void insertToBefore(Node p, int value) {
        Node node = new Node(value, null);
        insertToBefore(p, node);
    }

    private void insertToBefore(Node p, Node newnode) {
        if (p == null) return;
        if (head == p) {
            insertToHead(newnode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) {
            return;
        }
        newnode.next = p;
        q.next = newnode;
    }

    public void deleteByNode(Node node) {
        if (node == null || head == null) return;
        if (node == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != node) {
            q = q.next;
        }

        if (q == null) {
            return;
        }

        //q.next = q.next.next;
        q.next = node.next;

    }

    public void deleteByValue(int value) {
        if (head == null) return;

        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null) return;
        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
        /*if(p == head){
            head = head.next;
            return;
        }
        p.next = p.next.next;*/

        // 可重复删除指定value的代码
        /*if (head != null && head.data == value) {
            head = head.next;
        }
        Node pNode = head;
        while (pNode != null) {
            if (pNode.next.data == value) {
                pNode.next = pNode.next.next;
                continue;
            }
            pNode = pNode.next;
        }*/
    }

    public void printall(){
        Node node = head;
        while (node != null) {
            System.out.print(node.getData() + " ");
            node = node.next;
        }
        System.out.println();
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

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.insertToHead(0);
        linkedList.printall();
        linkedList.insertToHead(1);
        linkedList.printall();
        linkedList.insertToAfter(linkedList.findByValue(1), 2);
        linkedList.printall();
        linkedList.insertToBefore(linkedList.findByValue(2), 3);
        linkedList.printall();
        System.out.println(linkedList.findByIndex(2).data);
        linkedList.deleteByNode(linkedList.findByIndex(0));
        linkedList.printall();
        linkedList.deleteByValue(0);
        linkedList.printall();
    }
}
