package dev.scorpio;
class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

public class BinaryTreeHeight {

    Node root;

    int height(Node node) {
        // TODO: implement logic to calculate height
        if(node == null)
            return 0;
        return 1+Math.max(height(node.left),height(node.right));
    }

    public static void main(String[] args) {
     /*   BinaryTreeHeight tree = new BinaryTreeHeight();

        // Creating example tree
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Height of the tree is: " + tree.height(tree.root));*/

       /* BinaryTreeHeight tree = new BinaryTreeHeight();
        tree.root = new Node(10);
        System.out.println("Height: " + tree.height(tree.root));  // Expected: 1*/

       /* BinaryTreeHeight tree = new BinaryTreeHeight();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.left.left = new Node(3);
        tree.root.left.left.left = new Node(4);
        System.out.println("Height: " + tree.height(tree.root));  // Expected: 4*/

        /*BinaryTreeHeight tree = new BinaryTreeHeight();
        tree.root = new Node(1);
        tree.root.right = new Node(2);
        tree.root.right.right = new Node(3);
        tree.root.right.right.right = new Node(4);
        System.out.println("Height: " + tree.height(tree.root));  // Expected: 4*/

       /* BinaryTreeHeight tree = new BinaryTreeHeight();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.right = new Node(6);
        System.out.println("Height: " + tree.height(tree.root));  // Expected: 3*/

        BinaryTreeHeight tree = new BinaryTreeHeight();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        System.out.println("Height: " + tree.height(tree.root));  // Expected: 3




    }
}

