package com.liyaos.simpl.types;
import com.liyaos.simpl.parser.*;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-5-27
 * Time: 上午10:45
 * The most confusing 250 LOC to me.
 */

public class TypeInferenceVisitor implements SimPLParserVisitor {
    private EnvironmentStack es;
    private MemoryModel m;

    private final static SimPLObjectType intType = SimPLObjectType.createSimPLObject(Types.INT);
    private final static SimPLObjectType boolType = SimPLObjectType.createSimPLObject(Types.BOOL);

    public TypeInferenceVisitor() {
        m = new MemoryModel();
        EnvironmentModel e = new EnvironmentModel(m);
        es = new EnvironmentStack(e);
    }

    public TypeInferenceVisitor(EnvironmentStack es, MemoryModel m) {
        this.m = m;
        this.es = es;
    }

    private EnvironmentModel e() {
        return es.peek();
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        throw new RuntimeException("Visiting SimpleNode!");
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        node.setType((SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null));
        return node.getType();
    }

    @Override
    public Object visit(ASTLet node, Object data) {
        String id = ((ASTIdentifier)node.jjtGetChild(0)).getValue();
        es.onion(e(), id, m.alloc());
        SimPLObjectType t = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null);
        m.put(e().E(id), t);

        node.jjtGetChild(0).jjtAccept(this, null);
        node.setType((SimPLObjectType)node.jjtGetChild(2).jjtAccept(this, data));

        es.pop();

        return node.getType();
    }

    @Override
    public Object visit(ASTIf node, Object data) {
        SimPLObjectType t = (SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, boolType);
        SimPLObjectType t1 = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, data);
        SimPLObjectType t2 = (SimPLObjectType)node.jjtGetChild(2).jjtAccept(this, t1);
        if (t1.toBeInterferred() && !t2.toBeInterferred()) {
            node.jjtGetChild(1).jjtAccept(this, t2);
        }
        return t1;
    }

    @Override
    public Object visit(ASTWhile node, Object data) {
        SimPLObjectType t = (SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null);
        t = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null);
        node.setType(t);
        return t;
    }

    @Override
    public Object visit(ASTSequence node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTAnonymousFunction node, Object data) {
        String id = ((ASTIdentifier)node.jjtGetChild(0)).getValue();
        es.onion(e(), id, m.alloc());
        SimPLObjectType t1 = (SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null);
        SimPLObjectType t2 = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null);
        SimPLObjectType t = SimPLObjectType.createSimPLObject(Types.FUNC, t1, t2);
        es.pop();
        return t;
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        SimPLObjectType t = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null);
        m.put(e().E(((ASTIdentifier)node.jjtGetChild(0)).getValue()), t);
        node.setType(t);
        return t;
    }

    @Override
    public Object visit(ASTCons node, Object data) {
        SimPLObjectType t1 = (SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null);
        SimPLObjectType t2 = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null);

    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, boolType);
        node.jjtGetChild(0).jjtAccept(this, boolType);
        return boolType;
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, boolType);
        node.jjtGetChild(0).jjtAccept(this, boolType);
        return boolType;
    }

    @Override
    public Object visit(ASTEq node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTLessThan node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTPlus node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTMinus node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTTimes node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTDivide node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        node.jjtGetChild(1).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTNegative node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, intType);
        return intType;
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        node.jjtGetChild(0).jjtAccept(this, boolType);
        return boolType;
    }

    @Override
    public Object visit(ASTBool node, Object data) {
        return boolType;
    }

    @Override
    public Object visit(ASTInt node, Object data) {
        return intType;
    }

    @Override
    public Object visit(ASTNil node, Object data) {
        return SimPLObjectType.createSimPLObject(Types.LIST, new SimPLObjectType());
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        node.setType(m.M(e().E(node.getValue())));
        return node.getType();
    }

    @Override
    public Object visit(ASTFst node, Object data) {
        return ((SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null)).getEltType();
    }

    @Override
    public Object visit(ASTSnd node, Object data) {
        return ((SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null)).getEltType2();
    }

    @Override
    public Object visit(ASTHead node, Object data) {
        return ((SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null)).getEltType();
    }

    @Override
    public Object visit(ASTTail node, Object data) {
        return ((SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null)).getType();
    }

    @Override
    public Object visit(ASTApplication node, Object data) {
        SimPLObjectType t1 = (SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null);
        SimPLObjectType t2 = (SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null);
        if (t1.getEltType().toBeInterferred()) {
            if (!t2.toBeInterferred()) {
                t1.setEltType(t2);
            }
        }
        if (t1.getEltType2().toBeInterferred()) {
            if (data != null && !((SimPLObjectType)data).toBeInterferred()) {
                t1.setEltType2((SimPLObjectType)data);
            }
        }
        return t1.getEltType2();
    }

    @Override
    public Object visit(ASTPair node, Object data) {
        return SimPLObjectType.createSimPLObject(Types.PAIR,
                ((SimPLObjectType)node.jjtGetChild(0).jjtAccept(this, null)),
                ((SimPLObjectType)node.jjtGetChild(1).jjtAccept(this, null)));
    }

    @Override
    public Object visit(ASTBracket node, Object data) {
        return node.jjtGetChild(0).jjtAccept(this, null);
    }
}
