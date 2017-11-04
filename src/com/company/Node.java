package com.company;

/**
 * Created by crys_ on 03.11.2017.
 */
public class Node {

    private double data;
    private Node prev;
    private Node next;

    public Node(int x) {
        this.data=x;
    }

    public double getData() {
        return data;
    }
    public void setData(double data) {

        this.data = data;
    }
    public Node getPrev() {
        return prev;
    }
    public void setPrev(Node prev) {
        this.prev = prev;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }

}
