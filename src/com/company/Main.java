package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main extends Thread {
    static Lock lock = new ReentrantLock();
    ///// FINE GRAIN
    public static void main2(String[] args) {
        SortedLinkedListV2 listV2 = new SortedLinkedListV2();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int item = 0;
                int jstart = 11;
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
                        if (item == jstart)
                        {
                            WatchThreads.T1 = true;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int item = 11;
                int jstart = 19;
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
                        if (item == jstart - 1) {
                            WatchThreads.T2 = true;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int start = 0;
                int operation_number = 7;
                while (start != operation_number) {
                    listV2.head.lock.lock();
                    Iterator iterator = new Iterator(listV2);
                    double item = iterator.next().getKey();
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
                    }finally {
                        pred.lock.unlock();
                        long stop = System.nanoTime();
                        System.out.println("Este operatie de Stergere " + "Operatia a inceput la: " + stop + "si se sterge valoarea: " + item + " Threadul care se ocupa: " +getName());
                        start++;
                        if (start == operation_number)
                        {
                            WatchThreads.T3 = true;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                int aux = 0;
                while (!WatchThreads.T1 && !WatchThreads.T2 && !WatchThreads.T3) {
                    Iterator iterator = new Iterator(listV2, aux);
                    long stop = System.nanoTime();
                    System.out.print("Operatia a inceput la: " + stop + " Threadul care se ocupa: " + getName());
                    iterator.print();
                    aux++;
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("-----------------------------");
        Iterator iterator = new Iterator(listV2);
        iterator.print();


    }
    ///// COURSE GRAIN
    public static void main(String[] args) {
        SortedLinkedListV2 listV2 = new SortedLinkedListV2();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                int item = 0;
                int jstart = 11;
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
                        if (item == jstart) {
                            WatchThreads.T1 = true;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                int item = 11;
                int jstart = 19;
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
                        if (item == jstart) {
                            WatchThreads.T2 = true;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t3 = new Thread() {
            @Override
            public void run() {
                int start = 0;
                int operation_number = 7;
                while (start != operation_number) {
                    lock.lock();
                    Iterator iterator = new Iterator(listV2);
                    double item = iterator.next().getKey();
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
                        start++;
                        if (start == operation_number) {
                            WatchThreads.T2 = true;
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                int aux = 0;
                while (!WatchThreads.T1 && !WatchThreads.T2 && !WatchThreads.T3) {
                    Iterator iterator = new Iterator(listV2, aux);
                    long stop = System.nanoTime();
                    System.out.print("Operatia a inceput la: " + stop + " Threadul care se ocupa: " + getName());
                    iterator.print();
                    aux++;
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("-----------------------------");
        Iterator iterator = new Iterator(listV2);
        iterator.print();


    }
}