package com.company;

import java.util.ArrayList;
import com.company.ThreadCompleteListener;
import com.company.NotifyingThread;
public abstract class Runnable implements ThreadCompleteListener {
    public void Start(String[] args) {
        SortedLinkedListV2 listV2 = new SortedLinkedListV2();
       // ArrayList<SortedLinkedListV2_Runnable_Course_Grain> threadList = new ArrayList<>();
        ArrayList<SortedLinkedListV2_Runnable_Fine_Grain_Add> threadList = new ArrayList<>();
        ArrayList<NotifyingThread> threadlist = new ArrayList<>();
        for (double i = 0.0; i< 4.0; i++) {
            if (i == 0.0) {
              //  threadList.add(new SortedLinkedListV2_Runnable_Fine_Grain_Add(listV2,0,11,"Adaugare"));
               // threadList.add(new SortedLinkedListV2_Runnable_Course_Grain(listV2,0,11,"Adaugare"));
                threadlist.add(new SortedLinkedListV2_Runnable_Fine_Grain_Add(listV2,0,11,"Adaugare"));
            }
            if (i == 1.0) {
                //threadList.add(new SortedLinkedListV2_Runnable_Fine_Grain_Add(listV2,11,16,"Adaugare"));
                //threadList.add(new SortedLinkedListV2_Runnable_Course_Grain(listV2,0,11,"Adaugare"));
                threadlist.add(new SortedLinkedListV2_Runnable_Fine_Grain_Add(listV2,11,16,"Adaugare"));
            }
            if (i == 2.0) {
                threadList.add(new SortedLinkedListV2_Runnable_Fine_Grain_Add(listV2,7,"Stergere")); // aici trebuie sa ne asiguram ca valoarea ce urmeaza a fi stearsa exista in lista
               // threadList.add(new SortedLinkedListV2_Runnable_Course_Grain(listV2,7,"Stergere"));
            }
              if (i == 3.0) {
              //  threadList.add(new SortedLinkedListV2_Runnable_Fine_Grain_Add(listV2, "Iteratie"));
                  //threadList.add(new SortedLinkedListV2_Runnable_Course_Grain(listV2, "Iteratie"));
            }
        }
        for (NotifyingThread t: threadlist){
            t.start();
            t.addListener(this);
            System.out.println("--ajungem");
        }
        for (Thread t: threadList) {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
              //  System.out.println("--crapation");
            }
        }

        System.out.println("--ajungem");
        System.out.println("-----------------------------");
        Iterator iterator = new Iterator(listV2);
        iterator.print();

    }
}
