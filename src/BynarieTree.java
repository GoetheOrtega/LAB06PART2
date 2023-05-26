import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class BynarieTree {
    private Node root;

    private static class Node {
        String data;
        Node left;
        Node right;

        Node(String data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // Constructor
    public void BinaryTree() {
        root = null;
    }

    // Insertar un nuevo nodo en el árbol
    public void insert(String data) {
        root = insert(root, data);
    }

    private Node insert(Node node, String data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (Math.random() < 0.5) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }
        return node;
    }

    // Método para imprimir el árbol en orden
    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    // Método para imprimir el árbol por niveles
    public void levelOrder() {
        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                System.out.print(current.data + " ");
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            System.out.println();
        }
    }

    // Método para determinar si hay dos elementos idénticos en el árbol binario
    public boolean hasDuplicates() {
        Set<String> set = new HashSet<>();
        return hasDuplicates(root, set);
    }

    private boolean hasDuplicates(Node node, Set<String> set) {
        if (node == null)
            return false;

        if (set.contains(node.data))
            return true;

        set.add(node.data);

        return hasDuplicates(node.left, set) || hasDuplicates(node.right, set);
    }

    // Método para imprimir el número de niveles del árbol binario que tienen hojas
    public int countLevelsWithLeaves() {
        return countLevelsWithLeaves(root);
    }

    private int countLevelsWithLeaves(Node node) {
        if (node == null)
            return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean hasLeaves = false;
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.left == null && current.right == null) {
                    hasLeaves = true;
                }
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            if (hasLeaves) {
                count++;
            }
        }

        return count;
    }

// Método para imprimir el número de nodos que tienen más descendientes en el subárbol izquierdo
public int countNodesWithMoreLeftDescendants() {
    return countNodesWithMoreLeftDescendants(root);
}
    private int countNodesWithMoreLeftDescendants(Node node) {
        if (node == null)
            return 0;

        int count = 0;

        if (countDescendants(node.left) > countDescendants(node.right))
            count++;

        count += countNodesWithMoreLeftDescendants(node.left);
        count += countNodesWithMoreLeftDescendants(node.right);

        return count;
    }

    private int countDescendants(Node node) {
        if (node == null)
            return 0;

        int count = 0;
        if (node.left != null)
            count++;
        if (node.right != null)
            count++;

        count += countDescendants(node.left);
        count += countDescendants(node.right);

        return count;
    }

    // Método para imprimir los números de nodos que tienen el número de descendientes en el subárbol izquierdo
// distinto del número de descendientes en el subárbol derecho en 1
    public void printNodesWithDifferentDescendantsCount() {
        printNodesWithDifferentDescendantsCount(root);
    }

    private void printNodesWithDifferentDescendantsCount(Node node) {
        if (node == null)
            return;

        int leftDescendants = countDescendants(node.left);
        int rightDescendants = countDescendants(node.right);

        if (Math.abs(leftDescendants - rightDescendants) == 1) {
            System.out.print(node.data + " ");
        }

        printNodesWithDifferentDescendantsCount(node.left);
        printNodesWithDifferentDescendantsCount(node.right);
    }

    // Método para hallar el nodo medio del árbol basado en el valor más cercano a la media aritmética de los números
    public String findNodeClosestToAverage() {
        if (root == null)
            return null;

        int[] sumCount = findSumCount(root);
        int sum = sumCount[0];
        int count = sumCount[1];
        int average = sum / count;

        Node closestNode = findNodeClosestToAverage(root, average);
        return closestNode.data;
    }

    private Node findNodeClosestToAverage(Node node, int average) {
        if (node == null)
            return null;

        Node closestNode = null;
        int closestDiff = Integer.MAX_VALUE;

        int nodeValue = Integer.parseInt(node.data);
        int diff = Math.abs(nodeValue - average);

        if (diff < closestDiff) {
            closestNode = node;
            closestDiff = diff;
        }

        Node leftClosestNode = findNodeClosestToAverage(node.left, average);
        Node rightClosestNode = findNodeClosestToAverage(node.right, average);

        if (leftClosestNode != null) {
            int leftValue = Integer.parseInt(leftClosestNode.data);
            int leftDiff = Math.abs(leftValue - average);
            if (leftDiff < closestDiff) {
                closestNode = leftClosestNode;
                closestDiff = leftDiff;
            }
        }

        if (rightClosestNode != null) {
            int rightValue = Integer.parseInt(rightClosestNode.data);
            int rightDiff = Math.abs(rightValue - average);
            if (rightDiff < closestDiff) {
                closestNode = rightClosestNode;
                closestDiff = rightDiff;
            }
        }

        return closestNode;
    }

    private int[] findSumCount(Node node) {
        int[] sumCount = new int[2];

        if (node == null)
            return sumCount;

        int value = Integer.parseInt(node.data);
        int leftSum = 0;
        int leftCount = 0;
        int rightSum = 0;
        int rightCount = 0;
        if (node.left != null) {
            int[] leftSumCount = findSumCount(node.left);
            leftSum = leftSumCount[0];
            leftCount = leftSumCount[1];
        }

        if (node.right != null) {
            int[] rightSumCount = findSumCount(node.right);
            rightSum = rightSumCount[0];
            rightCount = rightSumCount[1];
        }

        int sum = value + leftSum + rightSum;
        int count = 1 + leftCount + rightCount;

        sumCount[0] = sum;
        sumCount[1] = count;

        return sumCount;
    }
}

// Clase principal que representa un nodo de un árbol binario
class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}