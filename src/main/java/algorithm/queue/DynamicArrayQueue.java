package algorithm.queue;

/**
 * 动态扩展队列
 */
public class DynamicArrayQueue {

    // 存储数据数组
    private String[] items;
    // 数组大小
    private int n = 0;
    // 队头
    private int head = 0;
    // 队尾
    private int tail = 0;

    public DynamicArrayQueue(int capacity) {
        this.items = new String[capacity];
        n = capacity;
    }

    // 入队
    public boolean enqueue(String item) {
        // tail == n 表示队列尾没有空间了
        if(tail == n) {
            // tail ==n && head==0，表示整个队列都占满了
            if(head == 0) return false;
            // 数据搬移
            for (int i = head; i < tail; i++ ){
                items[i-head] = items[i];
            }
            // 搬完之后更新head和tail
            tail -= head;
            head = 0;
        }
        items[tail] = item;
        tail++;
        return true;
    }

    // 出队
    public String dequeue() {
        // 如果head == tail 表示队列为空
        if (head == tail) return null;
        String ret = items[head++];
        return ret;
    }

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

}
