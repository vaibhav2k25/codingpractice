package dev.scorpio;

// Definition for a binary tree node.
public class SearchInBST {

    public TreeNode searchBST(TreeNode root, int val) {
        // TODO: Add logic to search for val in BST
        if (root == null)
            return null;
        if (root.val == val)
            return root;
        else if (val < root.val)
            return searchBST(root.left, val);
        return searchBST(root.right, val);
    }

    // Helper method to build a simple BST for testing
    public static TreeNode buildSampleBST() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        return root;
    }

    // Main method for input/output
    public static void main(String[] args) {
        SearchInBST searcher = new SearchInBST();
        TreeNode root = buildSampleBST();

        int valToSearch = 1;
        TreeNode result = searcher.searchBST(root, valToSearch);

        if (result != null) {
            System.out.println("Found node with value: " + result.val);
        } else {
            System.out.println("Value not found in BST.");
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
