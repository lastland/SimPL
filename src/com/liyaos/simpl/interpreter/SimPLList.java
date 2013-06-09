package com.liyaos.simpl.interpreter;

import com.liyaos.simpl.types.SimPLObjectType;
import com.liyaos.simpl.types.Types;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 上午1:01
 */
public class SimPLList<Node> extends SimPLObject {

    private Node car;
    private SimPLList<Node> cdr;

    public SimPLList() {
        car = null;
        cdr = null;
    }

    public SimPLList(Node car, SimPLList<Node> cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    public Node head() {
        return car;
    }

    public SimPLList<Node> tail() {
        if (cdr == null)
            return this;
        return cdr;
    }

    @Override
    public boolean equals(SimPLObject ob) {
        if (ob instanceof SimPLList) {
            SimPLList lst = (SimPLList) ob;
            return car.equals(lst.head()) && cdr.equals(lst.tail());
        }
        return false;
    }

    @Override
    public String toString() {
        return car + " :: (" + cdr + ")";
    }
}
