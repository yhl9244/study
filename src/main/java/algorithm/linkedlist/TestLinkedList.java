package algorithm.linkedlist;

public class TestLinkedList {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        LinkedList linkedList = new LinkedList();
        for (int i : arr) {
            linkedList.addNode2Head(i);
        }
        linkedList.printList();
        System.out.println();
        LinkedList linkedList1 = new LinkedList();
        for (int i : arr) {
            linkedList1.addNode2Tail(i);
        }
        linkedList1.printList();
    }
}
