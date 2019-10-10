package ru.ifmo.se.testing.zavoduben.lab1.avltree;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.ifmo.se.testing.zavoduben.lab1.avltree.AVLTreeAssert.assertThat;

class AVLTreeTest {

    private AVLTree testSubject;
    private LogCollector collector;

    @BeforeEach
    void setUp() {
        this.collector = new LogCollector();
        this.testSubject = new AVLTree();
        testSubject.addLogSubscriber(collector);
    }

    @Test
    void getHeight_emptyTree_isZero() {
        assertThat(testSubject).hasHeight(0);
    }

    @Test
    void getHeight_singleNode_isOne() {
        testSubject.insert(1);
        assertThat(testSubject).hasHeight(1);
    }

    @Test
    void insert_firstNode() {
        collector.enable();
        testSubject.insert(1);
        assertThat(collector.getMessages()).containsExactly(
                "Creating node with value 1"
        );
    }

    @Test
    void insert_sameSingleValue_toRightSubtree() {
        testSubject.insert(10);

        collector.enable();
        testSubject.insert(10);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 10 to right subtree of node 10",
                "Creating node with value 10",
                "Node (- 10 10) balanced"
        );
    }

    @Test
    void insert_valueFromLeftSubtreeAgain_goesToRightSubtreeWithoutRebalancing() {
        testSubject.insert(10);
        testSubject.insert(9);

        collector.enable();
        testSubject.insert(9);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 9 to left subtree of node (9 10 -)",
                "Inserting 9 to right subtree of node 9",
                "Creating node with value 9",
                "Node (- 9 9) balanced",
                "Node ((- 9 9) 10 -) balanced"
        );
    }

    @Test
    void insert_multipleSame_allGoToRightSubtree_withoutBalancing() {
        testSubject.insert(10);

        collector.enable();
        for (int i = 0; i < 5; i++) {
            testSubject.insert(10);
        }

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 10 to right subtree of node 10",
                "Creating node with value 10",
                "Node (- 10 10) balanced",

                "Inserting 10 to right subtree of node (- 10 10)",
                "Inserting 10 to right subtree of node 10",
                "Creating node with value 10",
                "Node (- 10 10) balanced",
                "Node (- 10 (- 10 10)) balanced",

                "Inserting 10 to right subtree of node (- 10 (- 10 10))",
                "Inserting 10 to right subtree of node (- 10 10)",
                "Inserting 10 to right subtree of node 10",
                "Creating node with value 10",
                "Node (- 10 10) balanced",
                "Node (- 10 (- 10 10)) balanced",
                "Node (- 10 (- 10 (- 10 10))) balanced",

                "Inserting 10 to right subtree of node (- 10 (- 10 (- 10 10)))",
                "Inserting 10 to right subtree of node (- 10 (- 10 10))",
                "Inserting 10 to right subtree of node (- 10 10)",
                "Inserting 10 to right subtree of node 10",
                "Creating node with value 10",
                "Node (- 10 10) balanced",
                "Node (- 10 (- 10 10)) balanced",
                "Node (- 10 (- 10 (- 10 10))) balanced",
                "Node (- 10 (- 10 (- 10 (- 10 10)))) balanced",

                "Inserting 10 to right subtree of node (- 10 (- 10 (- 10 (- 10 10))))",
                "Inserting 10 to right subtree of node (- 10 (- 10 (- 10 10)))",
                "Inserting 10 to right subtree of node (- 10 (- 10 10))",
                "Inserting 10 to right subtree of node (- 10 10)",
                "Inserting 10 to right subtree of node 10",
                "Creating node with value 10",
                "Node (- 10 10) balanced",
                "Node (- 10 (- 10 10)) balanced",
                "Node (- 10 (- 10 (- 10 10))) balanced",
                "Node (- 10 (- 10 (- 10 (- 10 10)))) balanced",
                "Node (- 10 (- 10 (- 10 (- 10 (- 10 10))))) balanced"
        );
    }

    @Test
    void insert_lesserValueToSingleNode_goesToLeftSubtree() {
        testSubject.insert(10);

        collector.enable();
        testSubject.insert(9);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 9 to left subtree of node 10",
                "Creating node with value 9",
                "Node (9 10 -) balanced"
        );
    }

    @Test
    void insert_greaterValueToSingleNode_goesToRightSubtree() {
        testSubject.insert(20);

        collector.enable();
        testSubject.insert(21);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 21 to right subtree of node 20",
                "Creating node with value 21",
                "Node (- 20 21) balanced"
        );
    }

    @Test
    void insert_toRightSubtreeWithFreeChild_doesNotRebalance() {
        testSubject.insert(14);
        testSubject.insert(16);
        testSubject.insert(12);

        collector.enable();
        testSubject.insert(17);
        testSubject.insert(15);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 17 to right subtree of node (12 14 16)",
                "Inserting 17 to right subtree of node 16",
                "Creating node with value 17",
                "Node (- 16 17) balanced",
                "Node (12 14 (- 16 17)) balanced",
                "Inserting 15 to right subtree of node (12 14 (- 16 17))",
                "Inserting 15 to left subtree of node (- 16 17)",
                "Creating node with value 15",
                "Node (15 16 17) balanced",
                "Node (12 14 (15 16 17)) balanced"
        );
    }

    @Test
    void insert_toLeftSubtreeWithFreeChild_doesNotRebalance() {
        testSubject.insert(14);
        testSubject.insert(16);
        testSubject.insert(12);

        collector.enable();
        testSubject.insert(11);
        testSubject.insert(13);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 11 to left subtree of node (12 14 16)",
                "Inserting 11 to left subtree of node 12",
                "Creating node with value 11",
                "Node (11 12 -) balanced",
                "Node ((11 12 -) 14 16) balanced",
                "Inserting 13 to left subtree of node ((11 12 -) 14 16)",
                "Inserting 13 to right subtree of node (11 12 -)",
                "Creating node with value 13",
                "Node (11 12 13) balanced",
                "Node ((11 12 13) 14 16) balanced"
        );
    }

    @Test
    void insert_toRightSubtreeOfRightBranch_rebalances() {
        testSubject.insert(10);
        testSubject.insert(15);

        collector.enable();
        testSubject.insert(20);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 20 to right subtree of node (- 10 15)",
                "Inserting 20 to right subtree of node 15",
                "Creating node with value 20",
                "Node (- 15 20) balanced",
                "Right-Right imbalance in node (- 10 (- 15 20))",
                "Left-rotating node (- 10 (- 15 20))",
                "Rebalanced node: (10 15 20)",
                "Now the node is (10 15 20)"
        );
    }

    @Test
    void insert_toLeftSubtreeOfLeftBranch_rebalances() {
        testSubject.insert(15);
        testSubject.insert(10);

        collector.enable();
        testSubject.insert(5);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 5 to left subtree of node (10 15 -)",
                "Inserting 5 to left subtree of node 10",
                "Creating node with value 5",
                "Node (5 10 -) balanced",
                "Left-Left imbalance in node ((5 10 -) 15 -)",
                "Right-rotating node ((5 10 -) 15 -)",
                "Rebalanced node: (5 10 15)",
                "Now the node is (5 10 15)"
        );
    }

    @Test
    void insert_toRightSubtreeOfLeftBranch_rebalances() {
        testSubject.insert(20);
        testSubject.insert(10);

        collector.enable();
        testSubject.insert(15);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 15 to left subtree of node (10 20 -)",
                "Inserting 15 to right subtree of node 10",
                "Creating node with value 15",
                "Node (- 10 15) balanced",
                "Left-Right imbalance in node ((- 10 15) 20 -)",
                "Left-rotating node (- 10 15)",
                "Rebalanced node: (10 15 -)",
                "Right-rotating node ((10 15 -) 20 -)",
                "Rebalanced node: (10 15 20)",
                "Now the node is (10 15 20)"
        );
    }

    @Test
    void insert_toLeftSubtreeOfRightBranch_rebalances() {
        testSubject.insert(9);
        testSubject.insert(12);

        collector.enable();
        testSubject.insert(11);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 11 to right subtree of node (- 9 12)",
                "Inserting 11 to left subtree of node 12",
                "Creating node with value 11",
                "Node (11 12 -) balanced",
                "Right-Left imbalance in node (- 9 (11 12 -))",
                "Right-rotating node (11 12 -)",
                "Rebalanced node: (- 11 12)",
                "Left-rotating node (- 9 (- 11 12))",
                "Rebalanced node: (9 11 12)",
                "Now the node is (9 11 12)"
        );
    }

    @Test
    void insert_intoUnbalancedList_rebalances() {
        Node node9 = Node.withValue(9);
        Node node8 = Node.builder().withValue(8).withRightChild(node9).build();
        Node node7 = Node.builder().withValue(7).withRightChild(node8).build();
        Node node6 = Node.builder().withValue(6).withRightChild(node7).build();

        AVLTree tree = new AVLTree(node6);
        tree.addLogSubscriber(collector);

        collector.enable();
        tree.insert(10);

        assertThat(collector.getMessages()).containsExactly(
                "Inserting 10 to right subtree of node (- 6 (- 7 (- 8 9)))",
                "Inserting 10 to right subtree of node (- 7 (- 8 9))",
                "Inserting 10 to right subtree of node (- 8 9)",
                "Inserting 10 to right subtree of node 9",
                "Creating node with value 10",
                "Node (- 9 10) balanced",
                "Right-Right imbalance in node (- 8 (- 9 10))",
                "Left-rotating node (- 8 (- 9 10))",
                "Rebalanced node: (8 9 10)",
                "Now the node is (8 9 10)",
                "Right-Right imbalance in node (- 7 (8 9 10))",
                "Left-rotating node (- 7 (8 9 10))",
                "Rebalanced node: ((- 7 8) 9 10)",
                "Now the node is ((- 7 8) 9 10)",
                "Right-Right imbalance in node (- 6 ((- 7 8) 9 10))",
                "Left-rotating node (- 6 ((- 7 8) 9 10))",
                "Rebalanced node: ((- 6 (- 7 8)) 9 10)",
                "Now the node is ((- 6 (- 7 8)) 9 10)"
        );
    }

    @Test
    void insert_complex() {
        collector.enable();
        List<Integer> insertedValues = Arrays.asList(91, 54, 29, 69, 93, 16, 81, 24, 50, 98);
        for (Integer integer : insertedValues) {
            testSubject.insert(integer);
        }

        assertThat(collector.getMessages()).containsExactly(
                "Creating node with value 91",

                "Inserting 54 to left subtree of node 91",
                "Creating node with value 54",
                "Node (54 91 -) balanced",

                "Inserting 29 to left subtree of node (54 91 -)",
                "Inserting 29 to left subtree of node 54",
                "Creating node with value 29",
                "Node (29 54 -) balanced",
                "Left-Left imbalance in node ((29 54 -) 91 -)",
                "Right-rotating node ((29 54 -) 91 -)",
                "Rebalanced node: (29 54 91)",
                "Now the node is (29 54 91)",

                "Inserting 69 to right subtree of node (29 54 91)",
                "Inserting 69 to left subtree of node 91",
                "Creating node with value 69",
                "Node (69 91 -) balanced",
                "Node (29 54 (69 91 -)) balanced",

                "Inserting 93 to right subtree of node (29 54 (69 91 -))",
                "Inserting 93 to right subtree of node (69 91 -)",
                "Creating node with value 93",
                "Node (69 91 93) balanced",
                "Node (29 54 (69 91 93)) balanced",

                "Inserting 16 to left subtree of node (29 54 (69 91 93))",
                "Inserting 16 to left subtree of node 29",
                "Creating node with value 16",
                "Node (16 29 -) balanced",
                "Node ((16 29 -) 54 (69 91 93)) balanced",

                "Inserting 81 to right subtree of node ((16 29 -) 54 (69 91 93))",
                "Inserting 81 to left subtree of node (69 91 93)",
                "Inserting 81 to right subtree of node 69",
                "Creating node with value 81",
                "Node (- 69 81) balanced",
                "Node ((- 69 81) 91 93) balanced",
                "Node ((16 29 -) 54 ((- 69 81) 91 93)) balanced",

                "Inserting 24 to left subtree of node ((16 29 -) 54 ((- 69 81) 91 93))",
                "Inserting 24 to left subtree of node (16 29 -)",
                "Inserting 24 to right subtree of node 16",
                "Creating node with value 24",
                "Node (- 16 24) balanced",
                "Left-Right imbalance in node ((- 16 24) 29 -)",
                "Left-rotating node (- 16 24)",
                "Rebalanced node: (16 24 -)",
                "Right-rotating node ((16 24 -) 29 -)",
                "Rebalanced node: (16 24 29)",
                "Now the node is (16 24 29)",
                "Node ((16 24 29) 54 ((- 69 81) 91 93)) balanced",

                "Inserting 50 to left subtree of node ((16 24 29) 54 ((- 69 81) 91 93))",
                "Inserting 50 to right subtree of node (16 24 29)",
                "Inserting 50 to right subtree of node 29",
                "Creating node with value 50",
                "Node (- 29 50) balanced",
                "Node (16 24 (- 29 50)) balanced",
                "Node ((16 24 (- 29 50)) 54 ((- 69 81) 91 93)) balanced",

                "Inserting 98 to right subtree of node ((16 24 (- 29 50)) 54 ((- 69 81) 91 93))",
                "Inserting 98 to right subtree of node ((- 69 81) 91 93)",
                "Inserting 98 to right subtree of node 93",
                "Creating node with value 98",
                "Node (- 93 98) balanced",
                "Node ((- 69 81) 91 (- 93 98)) balanced",
                "Node ((16 24 (- 29 50)) 54 ((- 69 81) 91 (- 93 98))) balanced"
        );
    }
}
