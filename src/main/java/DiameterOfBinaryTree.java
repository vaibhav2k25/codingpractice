import java.util.LinkedList;
import java.util.Queue;

public class DiameterOfBinaryTree {

    // Definition for a binary tree node
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Function to compute diameter of binary tree
    public int diameterOfBinaryTree(TreeNode root) {
        // TODO: Implement logic here
        if(root == null)
            return 0;
        return height(root.left)+height(root.right);
    }

    int height(TreeNode node) {
        // TODO: implement logic to calculate height
        if(node == null)
            return 0;
        return 1+Math.max(height(node.left),height(node.right));
    }

    // Helper to build tree from level-order input (null = no node)
    public static TreeNode buildTree(Integer[] values) {
        if (values.length == 0 || values[0] == null) return null;

        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();

            if (i < values.length && values[i] != null) {
                current.left = new TreeNode(values[i]);
                queue.add(current.left);
            }
            i++;

            if (i < values.length && values[i] != null) {
                current.right = new TreeNode(values[i]);
                queue.add(current.right);
            }
            i++;
        }

        return root;
    }

    // Main method to run and test
    public static void main(String[] args) {
        // Example input: [1, 2, 3, 4, 5]
        Integer[] input = {1, 2, 3, 4, 5, 6, 7, null, 8, null, null, 9, null, null, 10, 11};
        TreeNode root = buildTree(input);

        DiameterOfBinaryTree solution = new DiameterOfBinaryTree();
        int result = solution.diameterOfBinaryTree(root);
        System.out.println("Output: " + result);
    }
}
