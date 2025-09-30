package dev.scorpio;
import java.util.*;

public class BinaryTreeTraversals {

    // === Preorder ===

    public void preorderRecursive(TreeNode root) {
        // TODO: Implement recursive preorder traversal
        if(root==null)
            return;
        System.out.println(root.val);
        preorderRecursive(root.left);
        preorderRecursive(root.right);
    }

    public void preorderIterative(TreeNode root) {
        // TODO: Implement iterative preorder traversal
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

    }

    // === Inorder ===

    public void inorderRecursive(TreeNode root) {
        // TODO: Implement recursive inorder traversal

        if(root==null)
            return;
        inorderRecursive(root.left);
        System.out.println(root.val);
        inorderRecursive(root.right);
    }

    public void inorderIterative(TreeNode root) {
        // TODO: Implement iterative inorder traversal
    }

    // === Postorder ===

    public void postorderRecursive(TreeNode root) {
        // TODO: Implement recursive postorder traversal
        if(root==null)
            return;
        postorderRecursive(root.left);
        postorderRecursive(root.right);
        System.out.println(root.val);
    }

    public void postorderIterative(TreeNode root) {
        // TODO: Implement iterative postorder traversal
    }

    // === Optional: Main method for testing ===

    public static void main(String[] args) {
        // Sample tree:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BinaryTreeTraversals traversal = new BinaryTreeTraversals();

        System.out.println(("--------------PreOrderRecursive-------------------"));
        traversal.preorderRecursive(root);
//        traversal.preorderIterative(root);

        System.out.println(("--------------InOrderRecursive-------------------"));
        traversal.inorderRecursive(root);
//        traversal.inorderIterative(root);

        System.out.println(("--------------PostOrderRecursive-------------------"));
        traversal.postorderRecursive(root);
//        traversal.postorderIterative(root);
    }

}

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}


