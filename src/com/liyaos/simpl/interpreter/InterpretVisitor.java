package com.liyaos.simpl.interpreter;

import com.liyaos.simpl.parser.*;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 上午12:48
 */
public class InterpretVisitor implements SimPLParserVisitor {

    private Memory memory;

    public InterpretVisitor() {
        this.memory = new Memory();
    }

    private SimPLObject getChild(SimPLNode node, Object data, int i) {
        return (SimPLObject) node.jjtGetChild(i).jjtAccept(this, data);
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        return node.jjtAccept(this, data);
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        memory.resetMem();
        memory.clear();
        Environment environment = new Environment();
        return getChild(node, environment, 0);
    }

    @Override
    public Object visit(ASTLet node, Object data) {
        ASTIdentifier id = (ASTIdentifier) node.jjtGetChild(0);
        SimPLObject ob = getChild(node, data, 1);
        int addr = memory.alloc(ob);
        Environment env = (Environment) data;
        if (ob instanceof SimPLFunc) {
            ((SimPLFunc) ob).addName(id.getValue(), addr);
        }
        return getChild(node, env.onion(id.getValue(), addr), 2);
    }

    @Override
    public Object visit(ASTIf node, Object data) {
        SimPLBool b = (SimPLBool) getChild(node, data, 0);
        if (b.getValue()) {
            return getChild(node, data, 1);
        } else {
            return getChild(node, data, 2);
        }
    }

    @Override
    public Object visit(ASTWhile node, Object data) {
        SimPLBool b = (SimPLBool) getChild(node, data, 0);
        SimPLUnit u = new SimPLUnit();
        while (b.getValue()) {
            u = (SimPLUnit) getChild(node, data, 1);
            b = (SimPLBool) getChild(node, data, 0);
        }
        return u;
    }

    @Override
    public Object visit(ASTSequence node, Object data) {
        getChild(node, data, 0);
        return getChild(node, data, 1);
    }

    @Override
    public Object visit(ASTAnonymousFunction node, Object data) {
        ASTIdentifier id = (ASTIdentifier) node.jjtGetChild(0);
        return new SimPLFunc((Environment) data,
                id.getValue(), (SimPLNode) node.jjtGetChild(1));
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        ASTIdentifier id = (ASTIdentifier) node.jjtGetChild(0);
        Environment env = (Environment) data;
        memory.set(env.get(id.getValue()), getChild(node, data, 1));
        return new SimPLUnit();
    }

    @Override
    public Object visit(ASTCons node, Object data) {
        SimPLObject e = getChild(node, data, 0);
        SimPLList l = (SimPLList) getChild(node, data, 1);
        return new SimPLList(e, l);
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        SimPLBool e1 = (SimPLBool) getChild(node, data, 0);
        if (e1.getValue()) {
            SimPLBool e2 = (SimPLBool) getChild(node, data, 1);
            return e2.getValue();
        }
        return false;
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        SimPLBool e1 = (SimPLBool) getChild(node, data, 0);
        if (!e1.getValue())  {
            SimPLBool e2 = (SimPLBool) getChild(node, data, 1);
            return e2.getValue();
        }
        return true;
    }

    @Override
    public Object visit(ASTEq node, Object data) {
        SimPLObject e1 = getChild(node, data, 0);
        SimPLObject e2 = getChild(node, data, 1);
        if (e1.equals(e2)) {
            return new SimPLBool(true);
        }
        return new SimPLBool(false);
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) {
        SimPLInt e1 = (SimPLInt) getChild(node, data, 0);
        SimPLInt e2 = (SimPLInt) getChild(node, data, 1);
        if (e1.getValue() > e2.getValue()) {
            return new SimPLBool(true);
        }
        return new SimPLBool(false);
    }

    @Override
    public Object visit(ASTLessThan node, Object data) {
        SimPLInt e1 = (SimPLInt) getChild(node, data, 0);
        SimPLInt e2 = (SimPLInt) getChild(node, data, 1);
        if (e1.getValue() < e2.getValue()) {
            return new SimPLBool(true);
        }
        return new SimPLBool(false);
    }

    @Override
    public Object visit(ASTPlus node, Object data) {
        SimPLInt e1 = (SimPLInt) getChild(node, data, 0);
        SimPLInt e2 = (SimPLInt) getChild(node, data, 1);
        return e1.plus(e2);
    }

    @Override
    public Object visit(ASTMinus node, Object data) {
        SimPLInt e1 = (SimPLInt) getChild(node, data, 0);
        SimPLInt e2 = (SimPLInt) getChild(node, data, 1);
        return e1.minus(e2);
    }

    @Override
    public Object visit(ASTTimes node, Object data) {
        SimPLInt e1 = (SimPLInt) getChild(node, data, 0);
        SimPLInt e2 = (SimPLInt) getChild(node, data, 1);
        return e1.times(e2);
    }

    @Override
    public Object visit(ASTDivide node, Object data) {
        SimPLInt e1 = (SimPLInt) getChild(node, data, 0);
        SimPLInt e2 = (SimPLInt) getChild(node, data, 1);
        return e1.divide(e2);
    }

    @Override
    public Object visit(ASTNegative node, Object data) {
        SimPLInt e = (SimPLInt) getChild(node, data, 0);
        return (new SimPLInt(0)).minus(e);
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        SimPLBool e = (SimPLBool) getChild(node, data, 0);
        return new SimPLBool(!e.getValue());
    }

    @Override
    public Object visit(ASTBool node, Object data) {
        return new SimPLBool(node.getValue());
    }

    @Override
    public Object visit(ASTInt node, Object data) {
        if (node.isUndef())
            return new SimPLInt();
        return new SimPLInt(node.getValue());
    }

    @Override
    public Object visit(ASTNil node, Object data) {
        return new SimPLList<SimPLObject>();
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        Environment env = (Environment) data;
        return memory.get(env.get(node.getValue()));
    }

    @Override
    public Object visit(ASTFst node, Object data) {
        SimPLPair e = (SimPLPair) getChild(node, data, 0);
        return e.fst();
    }

    @Override
    public Object visit(ASTSnd node, Object data) {
        SimPLPair e = (SimPLPair) getChild(node, data, 0);
        return e.snd();
    }

    @Override
    public Object visit(ASTHead node, Object data) {
        SimPLList e = (SimPLList) getChild(node, data, 0);
        return e.head();
    }

    @Override
    public Object visit(ASTTail node, Object data) {
        SimPLList e = (SimPLList) getChild(node, data, 0);
        return e.tail();
    }

    @Override
    public Object visit(ASTApplication node, Object data) {
        SimPLFunc f = (SimPLFunc) getChild(node, data, 0);
        return f.execute(this, memory.alloc(getChild(node, data, 1)));
    }

    @Override
    public Object visit(ASTPair node, Object data) {
        SimPLObject e1 = getChild(node, data, 0);
        SimPLObject e2 = getChild(node, data, 1);
        return new SimPLPair(e1, e2);
    }

    @Override
    public Object visit(ASTBracket node, Object data) {
        return getChild(node, data, 0);
    }

    @Override
    public Object visit(ASTUnit node, Object data) {
        return new SimPLUnit();
    }
}
