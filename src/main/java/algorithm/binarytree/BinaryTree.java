package algorithm.binarytree;

/**
 * Created by suneee on 2019/1/23.
 */
public class BinaryTree {

    private static Node root; // 根节点

    /**
     * 模拟二叉树
     */
    private static class Node {
        private Node left; // 左节点
        private Node right; // 右节点
        private String data;
        private Integer num;

        public Node(String data) {
            this.data = data;
        }

        public Node(Integer num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "left=" + left +
                    ", right=" + right +
                    ", data='" + data + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

    private static void init() {
        root = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        root.left = nodeB;
        root.right = nodeC;
    }

    private static void initNum() {
        root = new Node(7);
        Node nodeB = new Node(4);
        Node nodeC = new Node(8);
        Node nodeD = new Node(3);
        Node nodeE = new Node(5);
        Node nodeF = new Node(6);
        Node nodeG = new Node(9);
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;
        root.left = nodeB;
        root.right = nodeC;
    }

    /**
     * 前序遍历
     */
    public static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.data + "->");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     */
    public static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.data + "->");
        inOrder(root.right);
    }

    /**
     * 后序遍历
     */
    public static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + "->");

    }

    /**
     * 按层遍历
     */
    public static void cengOrder(Node node) {
        if (node == null) return;
        if (node == root) {
            //System.out.print(node.data + "->");
            System.out.print(node.num + " ");
            System.out.println();
        }
        if (node.left != null) {
            //System.out.print(node.left.data + "->");
            System.out.print(node.left.num + " ");
        }
        if (node.right != null) {
            //System.out.print(node.right.data + "->");
            System.out.print(node.right.num + " ");
        }
        System.out.println();
        cengOrder(node.left);
        cengOrder(node.right);
    }

    /**
     * 二叉树查找
     */
    public static Node find(int data) {
        Node p = root;
        while (p != null) {
            if (data < p.num) {
                p = p.left;
            } else if (data > p.num) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public static void insert(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }

        Node p = root;
        while (p != null) {
            if (data < p.num) {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            } else if (data > p.num) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }
    }

    public static void delete(int data) {
        Node p = root; // 指向要删除节点,初始化为根节点
        Node pp = null; // p的父节点
        while (p != null && p.num != data) {
            pp = p;
            if (data < p.num) p = p.left;
            else p = p.right;
        }
        if (p == null) return; //没有找到要删除节点

        // 要删除节点有两个子节点
        if (p.left != null && p.right != null) {
            Node minp = p.right; // 查找右子树中最小节点
            Node minpp = p; // minp的父节点
            while (minp.left != null) {
                minpp = minp;
                minp = minp.left;
            }
            p.num = minp.num;// 将minp的数据替换到p中
            p = minp; //删除minp
            pp = minpp;
        }

        // 删除节点是叶子节点或者仅有一个节点
        Node child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if(pp == null) root = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    public static void main(String[] args) {
        /*init();
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
        cengOrder(root);*/
        //initNum();
        insert(33);
        insert(16);
        insert(50);
        insert(13);
        insert(18);
        insert(34);
        insert(58);
        insert(15);
        insert(17);
        insert(25);
        insert(51);
        insert(66);
        insert(19);
        insert(27);
        insert(55);
        //System.out.println(find(4));
        cengOrder(root);
        delete(18);
        System.out.println("**********************");
        cengOrder(root);
    }
}
