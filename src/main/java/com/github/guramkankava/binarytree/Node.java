package com.github.guramkankava.binarytree;

public class Node {

    private int value;
    private Node left, right;

    public Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(int value) {
        this.left = new Node(value);
    }

    public Node getRight() {
        return right;
    }

    public void setRight(int value) {
        this.right = new Node(value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
