package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by crys_ on 03.11.2017.
 */
public class SortedLinkedListV2 extends Thread{
    public NodeV2 head;
    public Lock lock = new ReentrantLock();


    public SortedLinkedListV2() {
        head = new NodeV2(Integer.MIN_VALUE);
        head.next = new NodeV2(Integer.MAX_VALUE);
    }


    public synchronized boolean add(double item) {
        lock.lock();
        NodeV2 pred = head;
        try {
            NodeV2 curr = head.next;
            while (curr.key < item) {
                pred = curr;
                curr = pred.next;
            }
            if (curr.key == item) {
                return false;
            }
            NodeV2 nodeV2 = new NodeV2(item);
            nodeV2.next = curr;
            pred.next = nodeV2;
            return true;
        } finally {
            lock.unlock();
            long stop = System.nanoTime();
            System.out.println("Operatia a inceput la:" + stop + "si se adauga valoarea: " + item + getName());
        }
    }

  /*  public synchronized boolean delete(double item) {
        lock.lock();
        NodeV2 pred = head;
        try {
            NodeV2 curr = head.next;
            while (curr.key<item) {
                pred = curr;
                curr = pred.next;
            }
            if (curr.key== item) {
                pred.next = curr.next;
                return true;
            }
            else {
                return  false;
            }
        } finally {
            lock.unlock();
        }
    }*/

    public synchronized NodeV2 getFirst() {
        return head;
    }
}
