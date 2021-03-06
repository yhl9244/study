package algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的遍历
 * @author gehj
 * @date 2019/8/1 14:33
 */
public class BinaryTree2 {
    public static void main(String[] args) {
        TreeNode head=new TreeNode(1);
        TreeNode second=new TreeNode(2);
        TreeNode three=new TreeNode(3);
        TreeNode four=new TreeNode(4);
        TreeNode five=new TreeNode(5);
        TreeNode six=new TreeNode(6);
        TreeNode seven=new TreeNode(7);
        head.right=three;
        head.left=second;
        second.right=five;
        second.left=four;
        three.right=seven;
        three.left=six;
        System.out.print("广度优先遍历结果：");
        new BinaryTree2().breadthFirstSearch(head);
        System.out.println();



        System.out.print("深度(前序)优先遍历(递归实现)结果：");
        new BinaryTree2().depthFirstSearchByRecursion(head);
        System.out.println();

        System.out.print("深度(前序)优先遍历结果：");
        new BinaryTree2().depthFirstSearch(head);
        System.out.println();

        System.out.print("深度(中序)优先遍历(递归实现)结果：");
        new BinaryTree2().midFirstSearchByRecursion(head);
        System.out.println();

        System.out.print("深度(中序)优先遍历结果：");
        new BinaryTree2().midFirstSearch(head);
        System.out.println();

        System.out.print("深度(后序)优先遍历(递归实现)结果：");
        new BinaryTree2().backFirstSearchByRecursion(head);
        System.out.println();

        System.out.print("深度(后序)优先遍历结果：");
        new BinaryTree2().backFirstSearch(head);
        System.out.println();

    }




    /**
     * 广度优先遍历--使用队列
     * @param root
     */
    public void breadthFirstSearch(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> myQueue = new LinkedList<>();
        myQueue.add(root);
        while (!myQueue.isEmpty()) {
            TreeNode node = myQueue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                myQueue.add(node.left);
            }
            if (node.right != null) {
                myQueue.add(node.right);
            }
        }

    }

    /**
     * 深度优先遍历--使用栈--前序优先遍历
     * @param root
     */
    public void depthFirstSearch(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode<Integer>> myStack = new Stack<>();
        myStack.push(root);
        while (!myStack.empty()) {
            TreeNode node = myStack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                myStack.push(node.right);
            }
            if (node.left != null) {
                myStack.push(node.left);
            }


        }
    }

    /**
     * 深度优先遍历--使用递归--前序优先遍历
     */
    public void depthFirstSearchByRecursion(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        depthFirstSearchByRecursion(root.left);
        depthFirstSearchByRecursion(root.right);

    }

    /**
     * 中序优先遍历
     * @param root
     */
    public void midFirstSearch(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode<Integer>> myStack = new Stack<>();
        while (!myStack.empty() || root != null) {
            if (root != null) {
                myStack.add(root);
                root = root.left;
            } else {
                root = myStack.pop();
                System.out.print(root.val + " ");
                root = root.right;
            }
        }

    }

    /**
     * 中序优先遍历--使用递归
     * @param root
     */
    public void midFirstSearchByRecursion(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        midFirstSearchByRecursion(root.left);
        System.out.print(root.val + " ");
        midFirstSearchByRecursion(root.right);
    }


    /**
     * 后序优先遍历
     * @param root
     */
    public void backFirstSearch(TreeNode<Integer> root) {

    }

    /**
     * 后序优先遍历 -- 使用递归
     */
    public void backFirstSearchByRecursion(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        backFirstSearchByRecursion(root.left);
        backFirstSearchByRecursion(root.right);
        System.out.print(root.val + " ");
    }

}
