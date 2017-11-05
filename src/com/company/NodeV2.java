package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by crys_ on 03.11.2017.
 */
public class NodeV2 {
    public double key;
    public NodeV2 next;
    public Lock lock = new ReentrantLock();

    NodeV2(double item){
        key = item;
        next = null;
    }

    public double getKey() {
        return key;
    }

    public void setKey(double key) {
        this.key = key;
    }

    public NodeV2 getNext() {
        return next;
    }

    public void setNext(NodeV2 next) {
        this.next = next;
    }
}

