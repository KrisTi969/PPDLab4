package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by crys_ on 04.11.2017.
 */
public class SortedLinkedListV2_Runnable_Course_Grain extends Thread{
   public SortedLinkedListV2 listV2;
    public double item;
    public double jstart;
    public String operatie;
    public Lock lock = new ReentrantLock();


    public SortedLinkedListV2_Runnable_Course_Grain(SortedLinkedListV2 listV2 , double item, double jstart, String operatie) {
        this.listV2 = listV2;
        this.item = item;
        this.jstart = jstart;
        this.operatie = operatie;
      //  System.out.println("Thread curent:" + this.getName());
    }
    public SortedLinkedListV2_Runnable_Course_Grain(SortedLinkedListV2 listV2 , String operatie) {
        this.listV2 = listV2;
        this.operatie = operatie;

        //  System.out.println("Thread curent:" + this.getName());
    }

    @Override
    public void run() {
        if (operatie == "Iteratie") {
            Iterator iterator = new Iterator(listV2);
            iterator.print();
            long stop = System.nanoTime();
            System.out.print( "Operatia a inceput la: " + stop + "si se afiseza valoarea: " + item + " Threadul care se ocupa: " + getName());
        }
        if (operatie == "Adaugare") {
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
                    }
                    NodeV2 nodeV2 = new NodeV2(item);
                    nodeV2.next = curr;
                    pred.next = nodeV2;
                } finally {
                    lock.unlock();
                    long stop = System.nanoTime();
                    System.out.println("Este operatie de ADUNARE " + "Operatia a inceput la: " + stop + "si se adauga valoarea: " + item + " Threadul care se ocupa: " + getName());
                    item++;
                }
            }
        }
        if(operatie == "Stergere") {
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
                    }
                } finally {
                    lock.unlock();
                    long stop = System.nanoTime();
                    System.out.println("Este operatie de STERGERE  " + "Operatia a inceput la: " + stop + "si se sterge valoarea: " + item + " Threadul care se ocupa: " + getName());
                    item++;
                }
            }
        }
    }
}
