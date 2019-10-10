package ru.ifmo.se.testing.zavoduben.lab1.avltree;

public class NodeBuilder {
    private Node left, right;
    private Integer value;

    public NodeBuilder left(Node leftNode) {
        this.left = leftNode;
        return this;
    }

    public NodeBuilder withRightChild(Node rightNode) {
        this.right = rightNode;
        return this;
    }

    public NodeBuilder withValue(int value) {
        this.value = value;
        return this;
    }

    public Node build() {
        return new Node(value, left, right);
    }
}
