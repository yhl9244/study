package algorithm.linkedlist;

/**
 * Created by suneee on 2018/10/16.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 单链表反转
 * 2. 链表中环检测
 * 3. 两个有序链表合并
 * 4. 删除链表倒数第n个节点
 * 5. 求链表中间节点
 */
public class LinkedList{

    private static Node head;

    public static void insertToHead(Integer value) {
        Node node = new Node(value, null);
        insertNodeToHead(node);
    }

    private static void insertNodeToHead(Node newnode) {
        if (head == null) {
            head = newnode;
        } else {
            newnode.next = head;
            head = newnode;
        }
    }

    /**************************单链表反转 start ***************************/
    /**
     * 方法一
     * @param list
     * @return
     */
    public static Node reverse(Node list) {
        Node headNode = null;

        Node previousNode = null;
        Node currentNode = list;
        while (currentNode != null){
            Node nextNode = currentNode.next;
            if(nextNode == null){
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        return headNode;
    }

    /**
     *
     * @param node 当前节点
     * @param pre 前一个节点
     * @return
     */
    public static Node reverse(Node node, Node pre) {
        if(node == null){
            return node;
        }else{
            // 反转后的头结点
            Node headNode = null;
            // 如果node.next为null,尾节点
            if(node.next == null){
                // 修改next引用
                node.next = pre;
                // 指定反转后头结点
                headNode = node;
            }else{
                // 非尾节点,继续递归
                headNode = reverse(node.next,node);
                node.next = pre;
            }
            return headNode;
        }
    }

    /**
     * 方法三
     * @param node 当前node
     * @return
     */
    public static Node reverse1(Node node){
        if(node == null || node.next == null){
            return node;
        }else{
            Node headnode = reverse1(node.next);
            node.next.next = node;
            node.next = null;
            return headnode;
        }
    }
    /**************************单链表反转 end *****************************/

    /**************************检测环 start *****************************/
    /**
     * 快慢指针法
     * @param list
     * @return
     */
    public static boolean checkCircle(Node list) {
        if(list==null) return false;
        Node slow = list;
        Node fast = list.next;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            // 已到链表末尾
            if (fast == null) {
                return false;
            } else if (fast == slow) {
                // 快慢指针相遇，存在环
                return true;
            }
        }

        return false;
    }

    /**
     * 足迹法
     * @param list
     * @return
     */
    // 保存足迹信息
    private static Map<Node, Integer> nodeMap = new HashMap<Node, Integer>();

    public static boolean checkCircle(Node node, Integer index) {
        if(node == null || node.next == null) return false;
        /*while (node.next != null) {
            if(nodeMap.containsKey(node)){
                nodeMap.put(node, nodeMap.get(node) + 1);
            }else{
                nodeMap.put(node, 1);
            }
            node = node.next;
        }
        nodeMap.put(node, 1);
        for (Map.Entry<Node, Integer> entry : nodeMap.entrySet()){
            Integer value = entry.getValue();
            if(value > 1) {
                return true;
            }
        }
        return false;*/
        if(nodeMap.containsKey(node)){
            return true;
        }else{
            nodeMap.put(node,index);
            return checkCircle(node.next,++index);
        }
    }
    /**************************检测环 end *******************************/

    /**************************有序链表合并start*******************************/
    /**
     * 判断法
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static Node merge(Node nodeA, Node nodeB) {
        if(nodeA == null){
            return nodeB;
        }
        if(nodeB == null) {
            return nodeA;
        }

        Node head = null;
        // 确定头结点
        if(nodeA.data < nodeB.data){
            head = nodeA;
            nodeA = nodeA.next;
        }else{
            head = nodeB;
            nodeB = nodeB.next;
        }
        Node newNode = head;
        while (nodeA != null && nodeB != null){
            if(nodeA.data < nodeB.data){
                newNode.next = nodeA;
                nodeA = nodeA.next;
            }else{
                newNode.next = nodeB;
                nodeB = nodeB.next;
            }
            newNode = newNode.next;
        }

        if(nodeA != null) {
            newNode.next = nodeA;
        }else{
            newNode.next = nodeB;
        }
        return head;
    }
    /**
     * 非递归法
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static Node merge1(Node nodeA, Node nodeB) {
        if(nodeA == null){
            return nodeB;
        }
        if(nodeB == null) {
            return nodeA;
        }

        Node head = createNode(null);
        Node currentNode = head;
        while (nodeA != null && nodeB != null){
            Node newNode = createNode(null);
            // 找出较小的点
            if(compareNode(nodeA,nodeB) <= 0){
                newNode.data = nodeA.data;
                nodeA = nodeA.next;
            }else {
                newNode = nodeB;
                nodeB = nodeB.next;
            }
            // 添加较小的节点
            currentNode.next = newNode;
            currentNode = currentNode.next;
        }
        // 去掉没有用的头结点
        head = head.next;

        return head;
    }
    /**
     * 递归法
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static Node merge2(Node nodeA, Node nodeB) {
        if(nodeA == null){
            return nodeB;
        }
        if(nodeB == null) {
            return nodeA;
        }

        Node result = null;

        if(compareNode(nodeA, nodeB) <= 0){
            result = nodeA;
            nodeA = nodeA.next;
        }else{
            result = nodeB;
            nodeB = nodeB.next;
        }
        result.next = merge2(nodeA,nodeB);
        return result;
    }

    private static int compareNode(Node nodeA, Node nodeB) {
        if (nodeA == null) {
            return 1;
        } else if (nodeB == null) {
            return -1;
        }

        if (nodeA.data == null) {
            return -1;
        } else if (nodeB.data == null) {
            return 1;
        }
        return nodeA.data.compareTo(nodeB.data);
    }

    /**************************有序链表合并end*********************************/

    /**********************删除链表倒数第n个节点 start**************************/

    public static Node deleteNodeFromEnd(Node node, int n) {
        if(node == null || n <= 0) return null;
        Node p = node;
        Node q = node;
        while (n > 0){
            p = p.next;
            if(p == null){
                node = node.next;
                return node;
            }
            n--;
        }
        while (p.next != null){
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return node;
    }

    /**********************删除链表倒数第n个节点 end****************************/

    /**********************查找中间节点 start ****************************/

    public static Node middleNode(Node node) {
        if(node == null || node.next == null) return node;
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast == null ? slow : slow.next;
    }
    /**********************查找中间节点 end ******************************/

    public static Node createNode(Integer value) {
        return new Node(value, null);
    }

    public static class Node {
        private Integer data;
        private Node next;

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Integer getData() {
            return data;
        }
    }

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        insertToHead(9);
        insertToHead(7);
        insertToHead(5);
        insertToHead(3);
        insertToHead(1);
        printAll(head);

        Node node1 = new Node(2, null);
        Node node2 = new Node(4, null);
        Node node3 = new Node(6, null);
        Node node4 = new Node(8, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        //printAll(merge(head, node1));
        //printAll(merge2(head,node1));
        //printAll(deleteNodeFromEnd(head, 2));
        printAll(middleNode(head));
        //printAll(reverse(head));
        //printAll(reverse(head,null));
        //printAll(reverse1(head));
        //System.out.println(checkCircle(head));
    }
}
