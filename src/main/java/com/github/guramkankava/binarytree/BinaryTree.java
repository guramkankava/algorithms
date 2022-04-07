package com.github.guramkankava.binarytree;

import static java.util.Objects.isNull;

public class BinaryTree {

    private Node root;

    public BinaryTree() {
        this.root = new Node(0);
    }

    public Node getRoot () {
        return root;
    }

    public void add(int value) {
        if (value > root.getValue()) {
            checkNext(value, root, root.getRight());
        } else if (value < root.getValue() ) {
            checkNext(value, root, root.getLeft());
        }
    }

    private void checkNext(int value, Node previous, Node next) {
        if(isNull(next)) {
            if(value > previous.getValue()) {
                previous.setRight(value);
            } else if (value < previous.getValue()) {
                previous.setLeft(value);
            }
        } else {
            if(value > next.getValue()) {
                checkNext(value, next, next.getRight());
            } else if (value < next.getValue()) {
                checkNext(value, next, next.getLeft());
            }
        }
    }

    public Node getByValue(int value) {
        if (value == root.getValue()) {
            return root;
        } else if (value > root.getValue()) {
            return get(value, root.getRight());
        } else {
            return get(value, root.getLeft());
        }
    }

    private Node get(int value, Node node) {
        if (isNull(node) || node.getValue() == value) {
            return node;
        } else if (value > node.getRight().getValue()) {
            return get(value, node.getRight());
        } else {
            return get(value, node.getLeft());
        }
    }

    public void insert(Node node, int value) {
        if (value < node.getValue()) {
            if (node.getLeft() != null) {
                insert(node.getLeft(), value); }
            else {
                System.out.println(" Inserted " + value + " to left of " + node.getValue());
                node.setLeft(value);
            }
        } else if (value > node.getValue()) {
            if (node.getRight() != null) {
                insert(node.getRight(), value);
            } else {
                System.out.println("  Inserted " + value + " to right of "
                        + node.getValue());
                node.setRight(value);
            }
        }
    }

    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.print(" " + node.getValue());
            traverseInOrder(node.getRight());
        }
    }

}
