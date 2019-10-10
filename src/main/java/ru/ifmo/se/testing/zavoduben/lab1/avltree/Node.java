package ru.ifmo.se.testing.zavoduben.lab1.avltree;

import java.util.Optional;

public class Node {
    Node left;
    Node right;
    int height = 1;
    int value;

    Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    Node(int value) {
        this(value, null, null);
    }

    @Override
    public String toString() {
        if (left == null && right == null) {
            return String.valueOf(this.value);
        }
        return "(" +
               Optional.ofNullable(left).map(Node::toString).orElse("-") +
               " " +
               value +
               " " +
               Optional.ofNullable(right).map(Node::toString).orElse("-") +
               ")";
//            return MessageFormat.format("Node[ value={0} left={1} right={2} ]", value, left, right);
    }

    public static NodeBuilder builder() {
        return new NodeBuilder();
    }

    public static Node withValue(int value) {
        return builder().withValue(value).build();
    }
}
