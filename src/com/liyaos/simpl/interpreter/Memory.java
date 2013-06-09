package com.liyaos.simpl.interpreter;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 下午1:07
 */
public class Memory extends ArrayList<SimPLObject> {
    private int mem;

    public Memory() {
        super();
        mem = 0;
    }

    public void resetMem() {
        mem = 0;
    }

    public int alloc() {
        add(null);
        return mem++;
    }

    public int alloc(SimPLObject ob) {
        add(ob);
        return mem++;
    }
}
