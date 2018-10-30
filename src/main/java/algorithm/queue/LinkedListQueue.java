package algorithm.queue;

/**
 * Created by suneee on 2018/10/30.
 * 基于数组实现的队列
 */
public class LinkedListQueue {

    // 队首
    private Node head = null;
    // 队尾
    private Node tail = null;

    //入队
    public void enqueue(String item) {
        if(tail == null) {
            Node newNode = new Node(item, null);
            head = newNode;
            tail = newNode;
        }else{
            tail.next = new Node(item, null);
            tail = tail.next;
        }
    }

    // 出队
    public String dequeue() {
        if(head == null) {
            return null;
        }
        String item = head.data;
        head = head.next;
        if(head == null) {
            tail = null;
        }
        return item;
    }

    public void printall() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p .next;
        }
        System.out.println();
    }

    private class Node {
        String data;
        Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.printall();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printall();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printall();

    }
}
