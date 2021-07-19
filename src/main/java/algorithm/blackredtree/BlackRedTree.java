package algorithm.blackredtree;

import java.util.LinkedList;
import java.util.Queue;

public class BlackRedTree<E extends Comparable<E>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public Node root;

    private int size;

    public int getSize() {
        return size;
    }

    private class Node {
        private E e;
        private Node left;
        private Node right;
        private boolean color;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.color = RED;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", color=" + color +
                    '}';
        }
    }

    public void addElement(E e) {
        root = addElement(root, e);
        root.color = BLACK;
    }

    /**
     * 将元素E添加到以node为根结点的树上
     * 返回新加入E节点的子树的根
     * @param node
     * @param e
     * @return
     */
    private Node  addElement(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        // 递归添加
        if (e.compareTo(node.e) < 0) {
            node.left = addElement(node.left, e);
        } else {
            node.right = addElement(node.right, e);
        }
        // 左旋
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }
        return node;
    }

    private Node leftRotate(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    private boolean isRed(Node node) {
        if (node == null) return BLACK;
        return node.color;
    }

    private void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.println(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


    public static void main(String[] args) {
        BlackRedTree blackRedTree = new BlackRedTree();
        blackRedTree.addElement(37);
        blackRedTree.addElement(42);
        blackRedTree.levelOrder();
    }


}
