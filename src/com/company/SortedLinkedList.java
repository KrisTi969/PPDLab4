package com.company;

import com.company.Node;

import java.util.concurrent.locks.Lock;

/**
 * Created by crys_ on 03.11.2017.
 */
public class SortedLinkedList {
    private Node first;
    private double size;

    public synchronized Node getFirst() {
        return first;
    }

    public synchronized void setFirst(Node first) {
        this.first = first;
    }

    public synchronized void setSize(double size) {
        this.size = size;
    }

    public synchronized double getSize() {
        return size;
    }


    public synchronized void insert(int x) {
        if (getFirst() == null) {
            setFirst(new Node(x));
            setSize(getSize() + 1);
        } else {
            Node node = getFirst();
            Node newNode = new Node(x);
            while (node.getNext() != null && x > node.getData()) {
                node = node.getNext();
            }

            if (node.getNext() == null && x > node.getData()) {

                node.setNext(newNode);
                newNode.setPrev(node);

            } else {
                node.getPrev().setNext(newNode);
                newNode.setNext(node);
                newNode.setPrev(node.getPrev());
                node.setPrev(newNode);
            }
            setSize(getSize() + 1);
        }
    }

    public synchronized void delete(double x) {
        Node node = getFirst();
        while (node != null) {
            if (node.getData() == x) {
                if (node == getFirst()) {
                    setFirst(getFirst().getNext());
                    getFirst().setPrev(null);
                    setSize(getSize() - 1);
                    break;
                } else {
                    if (node.getPrev() != null) {
                        node.getPrev().setNext(node.getNext());
                    }
                    if (node.getNext() != null)
                        node.getNext().setPrev(node.getPrev());
                    setSize(getSize() - 1);
                    break;
                }

            } else {
                node = node.getNext();
            }
        }
    }
}
