package algorithm.stack;

import java.util.Iterator;

/**
 * Created by suneee on 2018/10/30.
 * 基于链表实现的栈
 */
public class StackBasedLinkedList<Item> implements Iterable<Item> {

    private Node first;
    private Integer n = 0;

    private class Node {
        Item item;
        Node next;
    }

    /**
     * 添加数据
     * @param item
     */
    public void push(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        if(first == null) {
            first = newNode;
        } else {
            newNode.next = first;
            first = newNode;
        }
        n++;
    }

    /**
     * 删除数据
     * @return
     */
    public Item pop() {
        if(first == null) return null;
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    /**
     * 获取刚添加数据不删除
     * @return
     */
    public Item peek() {
        return first.item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        Node node = first;
        int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            Item item = node.item;
            node = node.next;
            i--;
            return item;
        }
    }

    public static void main(String[] args) {
        StackBasedLinkedList<Integer> stack = new StackBasedLinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        iterator = stack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
