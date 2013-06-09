package com.liyaos.simpl.interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 上午12:59
 */
public class SimPLBool extends SimPLObject {

    private boolean value;

    public SimPLBool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(SimPLObject ob) {
        if (ob instanceof SimPLBool) {
            return ((SimPLBool) ob).getValue() == value;
        }
        return false;
    }

    @Override
    public String toString() {
        return ((Boolean)value).toString();
    }
}
