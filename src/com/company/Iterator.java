package com.company;

import com.company.Node;
/**
 * Created by crys_ on 03.11.2017.
 */
public class Iterator implements java.util.Iterator{
    private NodeV2 current;
    SortedLinkedListV2 listV2;

    public Iterator(SortedLinkedListV2 listV2) {
        this.current = listV2.getFirst();
    }

    public NodeV2 next() {
    current = current.getNext();
    return current;
    }
    public boolean hasNext() {
        current = current.getNext();
        if(current.getNext()==null){
            return false;
        }
        return true;
    }
    public double getElement() {
    return current.getKey();
    }
    public void print() {
        NodeV2 node = current;
        while (node != null) {
            System.out.println("Este operatie de ITEREARE " + "Afisare nod: " + node.getKey() + " nod:" + node + " ,next:" + node.getNext());
            node = node.getNext();
        }
    }
}
