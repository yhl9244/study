package algorithm.queue;

/**
 * Created by suneee on 2018/10/30.
 * 基于数组实现的队列
 */
public class ArrayQueue {

    // 存放数据的数组
    private String[] items;
    // 数组大小
    private int n;
    // 队头下标
    private int head;
    // 队尾下标
    private int tail;

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // 如果tail == n 表示队列已经满了
        if(tail == n) {
            return false;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if(head == tail) {
            return null;
        }
        return items[head++];
    }

    public void printall() {
        for (int i = head; i < tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        System.out.println(queue.enqueue("a"));
        System.out.println(queue.enqueue("b"));
        System.out.println(queue.enqueue("c"));
        System.out.println(queue.enqueue("d"));
        System.out.println(queue.enqueue("e"));
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
