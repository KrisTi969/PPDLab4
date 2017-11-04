package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by crys_ on 04.11.2017.
 */
public class SortedLinkedListV2_Runnable_Delete_Course_Grain extends Thread {
    public SortedLinkedListV2 listV2;
    public double item;
    public double jstart;
    public Lock lock = new ReentrantLock();

    public SortedLinkedListV2_Runnable_Delete_Course_Grain(SortedLinkedListV2 listV2 , double item, double jstart) {
        this.listV2 = listV2;
        this.item = item;
        this.jstart = jstart;
    }

    @Override
    public void run() {
        while (item != jstart) {
            lock.lock();
            NodeV2 pred = listV2.head;
            try {
                NodeV2 curr = listV2.head.next;
                while (curr.key < item) {
                    pred = curr;
                    curr = pred.next;
                }
                if (curr.key == item) {
                    pred.next = curr.next;
                } else {
                    // nu facem nimic
                }
            } finally {
                lock.unlock();
                item++;
                long stop = System.nanoTime();
                System.out.println("Este operatie de stergere " + "Operatia a inceput la: " + stop + "si se adauga valoarea: " + item + " Threadul care se ocupa: " +getName());
            }
        }
    }
}

