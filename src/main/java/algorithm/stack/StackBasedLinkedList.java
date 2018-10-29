package algorithm.stack;

import java.util.Iterator;

/**
 * 基于链表的栈实现
 * @param <Item>
 */
public class StackBasedLinkedList<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first;
    private Integer n = 0;

    public StackBasedLinkedList() {
    }

    /**
     * 添加元素
     * @param item
     */
    public void push(Item item){
       /* Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;*/
       Node newNode = new Node();
       newNode.item = item;
       if(first == null){
           first = newNode;
       }else{
           newNode.next = first;
           first = newNode;
       }
    }

    public Item pop() {
        if (first == null) return null;
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    //是否为空
    public boolean isEmpty(){
        return n == 0;
    }
    //元素数量
    public int size(){
        return n;
    }
    //返回栈中最近添加的元素而不删除它
    public Item peek(){
        return first.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {

        int i = n;
        Node t = first;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            Item item = t.item;
            t =t.next;
            i--;
            return item;
        }
    }

    public static void main(String[] args) {
        StackBasedLinkedList<Integer> stackBasedLinkedList = new StackBasedLinkedList<>();
        stackBasedLinkedList.push(1);
        stackBasedLinkedList.push(2);
        stackBasedLinkedList.push(3);
        stackBasedLinkedList.push(4);
        System.out.println(stackBasedLinkedList.peek());
        Iterator<Integer> iterator = stackBasedLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(stackBasedLinkedList.pop());
        System.out.println(stackBasedLinkedList.pop());
        iterator = stackBasedLinkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
