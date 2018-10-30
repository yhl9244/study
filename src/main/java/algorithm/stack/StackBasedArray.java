package algorithm.stack;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 基于数组实现的栈
 * @param <Item>
 */
public class StackBasedArray<Item> implements Iterable<Item> {

    // 存储数据的数组
    private Item[] array = (Item[]) new Object[1];
    // 记录元素个数
    private Integer n = 0;

    public StackBasedArray() {
    }

    /**
     * 添加元素
     * @param item
     */
    public void push(Item item) {
        if(n == array.length) {
            //自动扩容
            resize(2*array.length);
        }
        array[n++] = item;
    }

    /**
     * 删除元素
     * @return
     */
    public Item pop() {
        Item item = array[--n];
        array[n] = null;
        if(n > 0 && n == array.length/4){
            resize(array.length/2);
        }
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int len) {
        Item[] temp = (Item[]) new Object[len];
        for (Integer i = 0; i < n; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    //返回栈中最近添加的元素而不删除它
    public Item peek() {
        return array[n-1];
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    class ArrayIterator implements Iterator{

        int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Object next() {
            return array[--i];
        }
    }

    public static void main(String[] args) {
        StackBasedArray<Integer> stackBasedArray = new StackBasedArray<>();
        stackBasedArray.push(1);
        stackBasedArray.push(2);
        stackBasedArray.push(3);
        stackBasedArray.push(4);
        System.out.println(stackBasedArray.peek());
        Iterator<Integer> iterator = stackBasedArray.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        stackBasedArray.pop();
        stackBasedArray.pop();
        iterator = stackBasedArray.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
