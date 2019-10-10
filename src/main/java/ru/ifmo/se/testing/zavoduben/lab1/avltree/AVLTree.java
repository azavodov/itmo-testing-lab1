package ru.ifmo.se.testing.zavoduben.lab1.avltree;

import ru.ifmo.se.testing.zavoduben.lab1.logging.LogPublisher;
import ru.ifmo.se.testing.zavoduben.lab1.logging.LogSubscriber;

import java.util.ArrayList;
import java.util.List;


public class AVLTree {

    private Node root;

    private LogPublisher log = new LogPublisher();

    public AVLTree() {
        this.root = null;
    }

    public AVLTree(Node rootNode) {
        this.root = rootNode;
    }

    public void addLogSubscriber(LogSubscriber collector) {
        log.addSubscriber(collector);
    }

    public int getHeight() {
        return height(this.root);
    }

    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    public void insert(int value) {
        this.root = insertInto(root, value);
    }

    private Node insertInto(Node node, int value) {

        /* 1.  Perform the normal BST rotation */
        if (node == null) {
            log.add("Creating node with value {0}", value);
            return new Node(value);
        }

        if (value < node.value) {
            log.add("Inserting {0} to left subtree of node {1}", value, node);
            node.left = insertInto(node.left, value);
        } else {
            log.add("Inserting {0} to right subtree of node {1}", value, node);
            node.right = insertInto(node.right, value);
        }

        /* 2. Update height of this ancestor node */
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        /* 3. Get the balance factor of this ancestor node to check whether
           this node became unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        Node result;

        if (balance > 1 && value < node.left.value) {
            log.add("Left-Left imbalance in node {0}", node);
            result = rightRotate(node);

        } else if (balance < -1 && value > node.right.value) {
            log.add("Right-Right imbalance in node {0}", node);
            result = leftRotate(node);

        } else if (balance > 1 && value > node.left.value) {
            log.add("Left-Right imbalance in node {0}", node);
            node.left = leftRotate(node.left);
            result = rightRotate(node);

        } else if (balance < -1 && value < node.right.value) {
            log.add("Right-Left imbalance in node {0}", node);
            node.right = rightRotate(node.right);
            result = leftRotate(node);

        } else {
            log.add("Node {0} balanced", node);
            /* return the (unchanged) node pointer */
            return node;
        }

        log.add("Now the node is {0}", result);
        return result;
    }

    private Node rightRotate(Node baseNode) {
        log.add("Right-rotating node {0}", baseNode);
        Node newRoot = baseNode.left;
        Node centerNode = baseNode.left.right;

        // Perform rotation
        newRoot.right = baseNode;
        baseNode.left = centerNode;

        // Update heights
        baseNode.height = Math.max(height(baseNode.left), height(baseNode.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        log.add("Rebalanced node: {0}", newRoot);
        return newRoot;
    }

    private Node leftRotate(Node baseNode) {
        log.add("Left-rotating node {0}", baseNode);
        Node newRoot = baseNode.right;
        Node centerNode = baseNode.right.left;

        // Perform rotation
        newRoot.left = baseNode;
        baseNode.right = centerNode;

        //  Update heights
        baseNode.height = Math.max(height(baseNode.left), height(baseNode.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        log.add("Rebalanced node: {0}", newRoot);
        return newRoot;
    }

    // Get Balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    public void preOrder(Node root) {
        if (root != null) {
            preOrder(root.left);
            System.out.printf("%d ", root.value);
            preOrder(root.right);
        }
    }

    private Node minValueNode(Node node) {
        Node current = node;
        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;
        return current;
    }

    private Node deleteNode(Node root, int value) {
        log.add("[deleteNode]");
        // STEP 1: PERFORM STANDARD BST DELETE

        if (root == null)
            return root;

        // If the value to be deleted is smaller than the root's value,
        // then it lies in left subtree
        if (value < root.value) {
            log.add("[deleteNode] Left subtree");
            root.left = deleteNode(root.left, value);
        }
        // If the value to be deleted is greater than the root's value,
        // then it lies in right subtree
        else if (value > root.value) {
            log.add("[deleteNode] Right subtree");
            root.right = deleteNode(root.right, value);

            // if value is same as root's value, then This is the node
            // to be deleted
        } else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                log.add("[deleteNode] Node with only one child or no child");
                Node temp;
                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                // No child case
                if (temp == null) {
                    temp = root;
                    root = null;
                } else // One child case
                    root = temp; // Copy the contents of the non-empty child

                temp = null;
            } else {
                // node with two children: Get the inorder successor (smallest
                // in the right subtree)
                log.add("[deleteNode] Node with two children");
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.value = temp.value;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.value);
            }
        }

        // If the tree had only one node then return
        if (root == null) {
            log.add("[deleteNode] Tree had only one node");
            return root;
        }

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        //  this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) {
            log.add("[deleteNode] Left Left Case");
            return rightRotate(root);
        }

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            log.add("[deleteNode] Left Right Case");
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) {
            log.add("[deleteNode] Right Right Case");
            return leftRotate(root);
        }

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            log.add("[deleteNode] Right Left Case");
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void print(Node root) {

        if (root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height,
                width = (int) Math.pow(2, height - 1);

        // Preparing variables for loop.
        List<Node> current = new ArrayList<Node>(1),
                next = new ArrayList<Node>(2);
        current.add(root);

        final int maxHalfLength = 4;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength * width);
        for (int i = 0; i < maxHalfLength * width; i++)
            sb.append(' ');
        String textBuffer;

        // Iterating through height levels.
        for (int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int) Math.pow(2, height - 1 - i) - 1));

            // Creating spacer space indicator.
            textBuffer = sb.toString();

            // Print tree node elements
            for (Node n : current) {

                System.out.print(textBuffer);

                if (n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf("(%6d)", n.value);
                    next.add(n.left);
                    next.add(n.right);

                }

                System.out.print(textBuffer);

            }

            System.out.println();
            // Print tree node extensions for next level.
            if (i < height - 1) {

                for (Node n : current) {

                    System.out.print(textBuffer);

                    if (n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renewing indicators for next run.
            elements *= 2;
            current = next;
            next = new ArrayList<Node>(elements);

        }

    }

}
