package com.liyaos.simpl.interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 上午12:57
 */
public class SimPLInt extends SimPLObject {

    private int value;

    public SimPLInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(SimPLObject ob) {
        if (ob instanceof SimPLInt) {
            return ((SimPLInt) ob).getValue() == value;
        }
        return false;
    }

    @Override
    public String toString() {
        return ((Integer)value).toString();
    }
}
