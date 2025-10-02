package dev.scorpio;

import java.util.*;

public class LevelOrderTraversal {

    //    List<List<Integer>> result = new ArrayList<>();
    static Queue<Integer> queue = new LinkedList<Integer>();

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.val = value;
            left = null;
            right = null;
        }
    }

    public void levelOrder(TreeNode root) {
        if (root == null)
            return;
        while (!queue.isEmpty())
            System.out.println(queue.poll());
        System.out.println();

        if (root.left != null) {
            queue.add(root.left.val);
        }
        if (root.right != null) {
            queue.add(root.right.val);
        }
        levelOrder(root.left);
        levelOrder(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // build tree

        LevelOrderTraversal traversal = new LevelOrderTraversal();
        queue.add(root.val);
        traversal.levelOrder(root);

        // print levels or process them
    }
}
