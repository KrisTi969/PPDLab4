package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Thread{
    public static void main(String[] args) {
        SortedLinkedListV2 listV2 = new SortedLinkedListV2();
        List<Thread> threadList = new ArrayList<>();
        for (double i = 0.0; i< 4.0; i++) {
           if (i == 0.0) {
               Thread t = new Thread(() -> {
                   for (int ii = 0; ii<10; ii++) {
                       listV2.add(ii);
               }
               });
               threadList.add(t);
           }
           if (i == 1.0) {
               Thread t = new Thread(() -> {
                   for (int ii = 10; ii<15; ii++) {
                       listV2.add(ii);
                   }
               });
              // t.start();
               threadList.add(t);
           }
            if (i == 2.0) {
                Thread t = new Thread(() -> {
                    for (int ii = 0; ii<7; ii++) {
                        listV2.delete(1);
                        listV2.delete(3);
                        listV2.delete(5);
                        listV2.delete(7);
                        listV2.delete(9);
                        listV2.delete(11);
                        listV2.delete(13);
                    }
                });
               // t.start();
                threadList.add(t);
            }
            if (i == 3.0) {
                Thread t = new Thread(() -> {
                    Iterator iterator = new Iterator(listV2);
                    while (true) {
                            iterator.print();
                        try {
                            Thread.sleep(10L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
               threadList.add(t);

            }
        }
        for (Thread t: threadList){
            System.out.println(t.getName());
            t.start();
        }
        for (Thread t: threadList) {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      //  Iterator iterator = new Iterator(listV2);
       // iterator.print();
    }
}
