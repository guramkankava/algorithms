package com.github.guramkankava.binarytree;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    @Test
    public void testAdd () {
        BinaryTree bt = new BinaryTree();
        assertEquals(0, bt.getRoot().getValue());
        bt.add(5);
        bt.add(1);
        assertNotNull(bt.getRoot());
        assertNotNull(bt.getRoot().getRight());
        assertEquals(5, bt.getRoot().getRight().getValue());
        assertEquals(1, bt.getRoot().getRight().getLeft().getValue());
        bt.add(-10);
        bt.add(-11);
        bt.add(-9);
        assertNotNull(bt.getRoot().getLeft());
        assertEquals(-10, bt.getRoot().getLeft().getValue());
        assertNotNull(bt.getRoot().getLeft().getLeft());
        assertEquals(-11, bt.getRoot().getLeft().getLeft().getValue());
        assertNotNull(bt.getRoot().getLeft().getRight());
        assertEquals(-9, bt.getRoot().getLeft().getRight().getValue());
    }

    @Test
    public void testGet () {
        BinaryTree bt = new BinaryTree();
        bt.add(12);
        bt.add(21);
        bt.add(11);
        bt.add(19);
        bt.add(10);
        Node node = bt.getByValue(12);
        System.out.println(node);
        assertNotNull(node);
        assertEquals(12, node.getValue());
        Node nextRightNode = node.getRight();
        System.out.println(nextRightNode);
        assertNotNull(nextRightNode);
        assertEquals(21, nextRightNode.getValue());
        Node nextLeftNode = node.getLeft();
        System.out.println(nextLeftNode);
        assertNotNull(nextLeftNode);
        assertEquals(11, nextLeftNode.getValue());
        Node nextRightNodeLeftNode = nextRightNode.getLeft();
        System.out.println(nextRightNodeLeftNode);
        assertNotNull(nextRightNodeLeftNode);
        assertEquals(19, nextRightNodeLeftNode.getValue());
        Node nextLeftNodeLeftNode = nextLeftNode.getLeft();
        System.out.println(nextLeftNodeLeftNode);
        assertNotNull(nextLeftNodeLeftNode);
        assertEquals(10, nextLeftNodeLeftNode.getValue());
    }
}