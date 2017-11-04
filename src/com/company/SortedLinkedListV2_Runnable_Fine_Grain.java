package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
/**
 * Created by crys_ on 04.11.2017.
 */
public class SortedLinkedListV2_Runnable_Fine_Grain extends Thread {
    public int start = 0;
    public SortedLinkedListV2 listV2;
    public double item;
    public double jstart;
    public String operatie;
    public int operation_number;
    private Lock lock = (Lock) listV2.head;
    private Lock lock2 ;
    private Lock lock3 = new ReentrantLock();


    public SortedLinkedListV2_Runnable_Fine_Grain(SortedLinkedListV2 listV2, double item, double jstart, String operatie) {
        this.listV2 = listV2;
        this.item = item;
        this.jstart = jstart;
        this.operatie = operatie;
        //  System.out.println("Thread curent:" + this.getName());
    }

    public SortedLinkedListV2_Runnable_Fine_Grain(SortedLinkedListV2 listV2, String operatie) {
        this.listV2 = listV2;
        this.operatie = operatie;

        //  System.out.println("Thread curent:" + this.getName());
    }

    public SortedLinkedListV2_Runnable_Fine_Grain(SortedLinkedListV2 listV2, int operation_number, String operatie) {
        this.listV2 = listV2;
        this.operatie = operatie;
        this.operation_number = operation_number;

        //  System.out.println("Thread curent:" + this.getName());
    }

    @Override
    public void run() {
        int aux = 1;
        if (operatie == "Adaugare") {
            while (item != jstart) {
               lock.lock();
                NodeV2 pred = listV2.head;
                lock3 = (Lock) pred;
                try {
                    NodeV2 curr = listV2.head.next;
                    lock2 = (Lock) listV2.head.next;
                    lock2.lock();
                    try {
                        while (curr.key < item) {
                            lock3.unlock();
                            pred = curr;
                            curr = pred.next;
                            lock2.unlock();
                        }
                        if (curr.key == item) {
                        }
                        NodeV2 nodeV2 = new NodeV2(item);
                        nodeV2.next = curr;
                        pred.next = nodeV2;
                    } finally {
                        lock2.unlock();
                    }
                } finally {
                    lock3.unlock();
                    long stop = System.nanoTime();
                    System.out.println("Este operatie de ADUNARE " + "Operatia a inceput la: " + stop + "si se adauga valoarea: " + item + " Threadul care se ocupa: " + getName());
                    item++;
                }
            }
            if (operatie == "Stergere") {
                while (start != operation_number) {
                    lock.lock();
                    try {
                        Iterator iterator = new Iterator(listV2);
                        item = iterator.next().getKey();
                        NodeV2 pred = listV2.head;
                        NodeV2 curr = listV2.head.next;
                        lock2.lock();
                        try {
                            while (curr.key < item) {
                                lock3.unlock();
                                pred = curr;
                                curr = pred.next;
                                lock2.lock();
                            }
                            if (curr.key == item) {
                                pred.next = curr.next;
                            } else {
                            }
                        } finally {
                            lock2.unlock();

                        }
                    } finally {
                        lock3.lock();
                        long stop = System.nanoTime();
                        System.out.println("Este operatie de STERGERE  " + "Operatia a inceput la: " + stop + "si se sterge valoarea: " + item + " Threadul care se ocupa: " + getName());
                        start++;
                    }
                }
            }
            while (aux != 4) {
                if (operatie == "Iteratie") {
                    Iterator iterator = new Iterator(listV2, aux);
                    long stop = System.nanoTime();
                    System.out.print("Operatia a inceput la: " + stop + "si se afiseza valoarea: " + item + " Threadul care se ocupa: " + getName());
                    try {
                        iterator.print();
                        aux++;
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
