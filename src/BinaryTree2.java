import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinaryTree2 {
    private Node root;

    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private Node insertNode(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertNode(root.left, data);
        } else if (data > root.data) {
            root.right = insertNode(root.right, data);
        }

        return root;
    }

    public void inorderTraversal(Consumer<Node> action) {
        System.out.println("Inorder Traversal:");
        inorder(root, action);
        System.out.println();
    }

    private void inorder(Node root, Consumer<Node> action) {
        if (root != null) {
            inorder(root.left, action);
            action.accept(root);
            inorder(root.right, action);
        }
    }

    public void preorderTraversal(Consumer<Node> action) {
        System.out.println("Preorder Traversal:");
        preorder(root, action);
        System.out.println();
    }

    private void preorder(Node root, Consumer<Node> action) {
        if (root != null) {
            action.accept(root);
            preorder(root.left, action);
            preorder(root.right, action);
        }
    }

    public void postorderTraversal(Consumer<Node> action) {
        System.out.println("Postorder Traversal:");
        postorder(root, action);
        System.out.println();
    }

    private void postorder(Node root, Consumer<Node> action) {
        if (root != null) {
            postorder(root.left, action);
            postorder(root.right, action);
            action.accept(root);
        }
    }

    public void levelOrderTraversal(Consumer<Node> action) {
        System.out.println("Level Order Traversal:");
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            action.accept(current);

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {
        BinaryTree2 tree = new BinaryTree2();
        tree.insert(4);
        tree.insert(2);
        tree.insert(6);
        tree.insert(1);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);

        Consumer<Node> squareAction = node -> System.out.print(node.data * node.data + " ");

        tree.inorderTraversal(squareAction);
        tree.preorderTraversal(squareAction);
        tree.postorderTraversal(squareAction);
        tree.levelOrderTraversal(squareAction);
    }
}
