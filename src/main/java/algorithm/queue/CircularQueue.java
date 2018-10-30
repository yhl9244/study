package algorithm.queue;

/**
 * Created by suneee on 2018/10/30.
 * 环形队列
 */
public class CircularQueue {

    // 保存数据的数组
    private String[] items;
    // 数组大小
    private int n;
    // 队头下标
    private int head;
    // 队尾下标
    private int tail;

    public CircularQueue(int capacity) {
        this.items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // 队列满了
        if ((tail + 1) % n == head) return false;
        items[tail] = item;
        tail = (tail + 1) % n;
        return true;
    }

    //出队
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if(head == tail){
            return null;
        }
        String item = items[head];
        head = (head + 1) % n;
        return item;
    }

    public void printall() {
        if(n==0) return;
        for (int i= head; i % n != tail; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(4);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");
        queue.enqueue("e");
        queue.printall();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printall();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printall();

    }
}
