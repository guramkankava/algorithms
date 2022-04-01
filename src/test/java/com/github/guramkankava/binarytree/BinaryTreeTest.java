package com.github.guramkankava.binarytree;

import com.github.guramkankava.binarytree.BinaryTree;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    @Test
    public void testAdd () {
        BinaryTree bt = new BinaryTree();
        bt.add(5);
        bt.add(4);
        bt.add(6);
        bt.add(7);
        bt.add(1);
        bt.add(2);
        System.out.println(bt.root.getValue());
    }
}