package com.liyaos.simpl.interpreter;

import com.liyaos.simpl.parser.ASTAnd;
import com.liyaos.simpl.parser.SimPLNode;


/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 上午1:21
 */
public class SimPLFunc extends SimPLObject {
    private String id;
    private SimPLNode body;
    private Environment env;

    public SimPLFunc(Environment env, String id, SimPLNode body) {
        this.env = env;
        this.id = id;
        this.body = body;
    }

    public SimPLObject execute(InterpretVisitor visitor, int addr) {
        SimPLObject res;
        if (body instanceof ASTAnd) {
            ASTAnd t = (ASTAnd) body;
            res = (SimPLObject) visitor.visit(t, env.onion(id, addr));
        }
        return (SimPLObject) visitor.visit(body, env.onion(id, addr));
    }

    public void addName(String name, int addr) {
        env = env.onion(name, addr);
    }

    @Override
    public boolean equals(SimPLObject ob) {
        if (ob instanceof SimPLFunc) {
            SimPLFunc f = (SimPLFunc) ob;
            return id == f.id && env.equals(f.env) && body.equals(f.body);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Fun " + id;
    }
}
