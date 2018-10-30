package algorithm.stack;

import org.hamcrest.internal.ArrayIterator;

import java.util.Iterator;

/**
 * Created by suneee on 2018/10/30.
 */
public class StackBasedArray<Item> implements Iterable<Item> {


    /**
     * 存储数据的数组
     */
    private Item[] array = (Item[]) new Object[1];
    /**
     * 数组大小
     */
    private Integer n = 0;

    /**
     * 保存数据
     */
    public void push(Item item) {
        if(n == array.length){
            resize(array.length * 2);
        }
        array[n++] = item;
    }

    /**
     * 删除数据
     * @return
     */
    public Item pop() {
        Item item = array[--n];
        array[n] = null;
        if(n > 0 && n == array.length/4) {
            resize(array.length/2);
        }
        return item;
    }

    /**
     * 获取上一次添加元素不删除
     * @return
     */
    public Item peek(){
        return array[n-1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private void resize(int len) {
        Item[] temp = (Item[]) new Object[len];
        for (int i= 0; i < n; i++){
            temp[i] = array[i];
        }
        array = temp;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    class ArrayIterator implements Iterator {

        int i = n;

        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Object next() {
            return array[--i];
        }
    }

    public static void main(String[] args) {
        StackBasedArray<Integer> array = new StackBasedArray<>();
        array.push(1);
        array.push(2);
        array.push(3);
        array.push(4);
        array.push(5);
        Iterator<Integer> iterator = array.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(array.peek());
        System.out.println(array.pop());
        System.out.println(array.pop());

        iterator = array.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
