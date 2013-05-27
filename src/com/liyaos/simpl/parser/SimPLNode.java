package com.liyaos.simpl.parser;

import com.liyaos.simpl.types.Types;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-5-27
 * Time: 上午10:51
 */
public class SimPLNode extends SimpleNode {

    private Types type;

    public SimPLNode(int i) {
        super(i);
    }

    public SimPLNode(SimPLParser p, int i) {
        super(p, i);
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
