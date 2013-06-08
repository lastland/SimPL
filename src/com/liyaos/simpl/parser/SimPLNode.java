package com.liyaos.simpl.parser;

import com.liyaos.simpl.types.SimPLObjectType;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-5-27
 * Time: 上午10:51
 */
public class SimPLNode extends SimpleNode {

    private SimPLObjectType type = null;

    public SimPLNode(int i) {
        super(i);
    }

    public SimPLNode(SimPLParser p, int i) {
        super(p, i);
    }

    public SimPLObjectType getType() {
        return type;
    }

    public void setType(SimPLObjectType type) {
        this.type = type;
    }
}
