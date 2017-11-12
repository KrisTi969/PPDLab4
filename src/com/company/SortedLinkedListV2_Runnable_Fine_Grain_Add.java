package com.company;

/**
 * Created by crys_ on 04.11.2017.
 */
public class SortedLinkedListV2_Runnable_Fine_Grain_Add extends NotifyingThread  {

    public int start = 0;
    public SortedLinkedListV2 listV2;
    public double item;
    public double jstart;
    public String operatie;
    public int operation_number;


    public SortedLinkedListV2_Runnable_Fine_Grain_Add(SortedLinkedListV2 listV2, double item, double jstart, String operatie) {
        this.listV2 = listV2;
        this.item = item;
        this.jstart = jstart;
        this.operatie = operatie;
        //  System.out.println("Thread curent:" + this.getName());
    }

    public SortedLinkedListV2_Runnable_Fine_Grain_Add(SortedLinkedListV2 listV2, String operatie) {
        this.listV2 = listV2;
        this.operatie = operatie;

        //  System.out.println("Thread curent:" + this.getName());
    }

    public SortedLinkedListV2_Runnable_Fine_Grain_Add(SortedLinkedListV2 listV2, int operation_number, String operatie) {
        this.listV2 = listV2;
        this.operatie = operatie;
        this.operation_number = operation_number;

        //  System.out.println("Thread curent:" + this.getName());
    }


    @Override
    public synchronized void doRun() {
        int aux = 0;
        if (operatie.equals("Adaugare")) {
            while (item != jstart) {
                listV2.head.lock.lock();
                NodeV2 pred = listV2.head;
                try {
                    NodeV2 curr = listV2.head.next;
                    curr.lock.lock();
                    try {
                        while (curr.key < item) {
                            pred.lock.unlock();
                            pred = curr;
                            curr = pred.next;
                            curr.lock.lock();
                        }
                        if (curr.key == item) {
                        }
                        NodeV2 nodeV2 = new NodeV2(item);
                        nodeV2.next = curr;
                        pred.next = nodeV2;

                    } finally {
                        curr.lock.unlock();
                    }
                } finally {
                    pred.lock.unlock();
                    long stop = System.nanoTime();
                    System.out.println("Este operatie de ADUNARE " + "Operatia a inceput la: " + stop + "si se adauga valoarea: " + item + " Threadul care se ocupa: " + getName());
                    item++;
                }
            }
        }
        if (operatie.equals("Stergere")) {
            while (start != operation_number) {
                listV2.head.lock.lock();
                Iterator iterator = new Iterator(listV2);
                item = iterator.next().getKey();
                try {
                    item = iterator.next().getKey();
                } catch (NullPointerException e) {
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                NodeV2 pred = listV2.head;
                try {
                    NodeV2 curr = pred.next;
                    curr.lock.lock();
                    try {
                        while (curr.key < item) {
                            pred.lock.unlock();
                            pred = curr;
                            curr = pred.next;
                            curr.lock.lock();
                        }
                        if (curr.key == item) {
                            pred.next = curr.next;
                        } else {
                            // nu facem nimic
                        }
                    } finally {
                        curr.lock.unlock();
                    }
                } finally {
                    pred.lock.unlock();
                    item++;
                    long stop = System.nanoTime();
                    System.out.println("Este operatie de Stergere " + "Operatia a inceput la: " + stop + "si se sterge valoarea: " + item + " Threadul care se ocupa: " + getName());
                    start++;
                }
            }
        }
       // while (aux != 4) {
            if (operatie.equals("Iteratie")) {
                Iterator iterator = new Iterator(listV2, aux);
                long stop = System.nanoTime();
                System.out.print("Operatia a inceput la: " + stop + "si se afiseza valoarea: " + item + " Threadul care se ocupa: " + getName());
                iterator.print();
                try {
                    iterator.print();
                    aux++;
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
      //  }

    }
}
