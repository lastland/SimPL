package com.liyaos.simpl.types;

import com.liyaos.simpl.parser.*;

import static com.liyaos.simpl.types.Types.*;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午2:59
 */
public class TypeInferenceVisitor implements SimPLParserVisitor {

    private Constraints constraints;
    private NameList nameList;

    private static SimPLObjectType intType = new SimPLObjectType(INT);
    private static SimPLObjectType boolType = new SimPLObjectType(BOOL);
    private static SimPLObjectType unitType = new SimPLObjectType(UNIT);

    public TypeInferenceVisitor() {
        constraints = new Constraints();
        nameList = new NameList();
    }

    private SimPLObjectType getChildType(SimPLNode node, Object data, int number) {
        return (SimPLObjectType)node.jjtGetChild(number).jjtAccept(this, data);
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        SimPLObjectType t = getChildType(node, new AliasList(), 0);
        constraints.infer();
        return nameList;
    }

    @Override
    public Object visit(ASTLet node, Object data) {
        SimPLObjectType t1 = new SimPLObjectType();
        ASTIdentifier id = (ASTIdentifier)node.jjtGetChild(0);
        nameList.put(id.getValue(), t1);
        SimPLObjectType t2 = getChildType(node, data, 1);
        SimPLObjectType.infer(t2, t1);
        return getChildType(node, data, 2);
    }

    @Override
    public Object visit(ASTIf node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(boolType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        SimPLObjectType t3 = getChildType(node, data, 2);
        constraints.put(t2, t3);
        return t2;
    }

    @Override
    public Object visit(ASTWhile node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(boolType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(unitType);
        return unitType;
    }

    @Override
    public Object visit(ASTSequence node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(unitType);
        return getChildType(node, data, 1);
    }

    @Override
    public Object visit(ASTAnonymousFunction node, Object data) {
        ASTIdentifier id = (ASTIdentifier)node.jjtGetChild(0);
        String newName = nameList.getNewAnonymousName();
        AliasList l = new AliasList();
        l.put(id.getValue(), newName);
        SimPLObjectType t1 = new SimPLObjectType();
        nameList.put(newName, t1);
        SimPLObjectType t2 = getChildType(node, ((AliasList)data).onion(l), 1);
        return new SimPLObjectType(FUNC, t1, t2);
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        SimPLObjectType t2 = getChildType(node, data, 1);
        constraints.put(t1, t2);
        return unitType;
    }

    @Override
    public Object visit(ASTCons node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        SimPLObjectType t2 = getChildType(node, data, 1);
        constraints.put(t1, t2.getEltType());
        return t2;
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(boolType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(boolType);
        return boolType;
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(boolType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(boolType);
        return boolType;
    }

    @Override
    public Object visit(ASTEq node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return boolType;
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return boolType;
    }

    @Override
    public Object visit(ASTLessThan node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return boolType;
    }

    @Override
    public Object visit(ASTPlus node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return intType;
    }

    @Override
    public Object visit(ASTMinus node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return intType;
    }

    @Override
    public Object visit(ASTTimes node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return intType;
    }

    @Override
    public Object visit(ASTDivide node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        t1.become(intType);
        SimPLObjectType t2 = getChildType(node, data, 1);
        t2.become(intType);
        return intType;
    }

    @Override
    public Object visit(ASTNegative node, Object data) {
        SimPLObjectType t = getChildType(node, data, 0);
        t.become(intType);
        return intType;
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        SimPLObjectType t = getChildType(node, data, 0);
        t.become(boolType);
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
        return new SimPLObjectType(LIST, new SimPLObjectType());
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        AliasList aliasList = (AliasList)data;
        String name = node.getValue();
        while (aliasList.containsKey(name)) {
            name = aliasList.get(name);
        }
        return nameList.get(name);
    }

    @Override
    public Object visit(ASTFst node, Object data) {
        SimPLObjectType t1 = new SimPLObjectType();
        SimPLObjectType t2 = new SimPLObjectType(PAIR, t1, new SimPLObjectType());
        SimPLObjectType t3 = getChildType(node, data, 0);
        constraints.put(t3, t2);
        return t1;
    }

    @Override
    public Object visit(ASTSnd node, Object data) {
        SimPLObjectType t1 = new SimPLObjectType();
        SimPLObjectType t2 = new SimPLObjectType(PAIR, new SimPLObjectType(), t1);
        SimPLObjectType t3 = getChildType(node, data, 0);
        constraints.put(t3, t2);
        return t1;
    }

    @Override
    public Object visit(ASTHead node, Object data) {
        SimPLObjectType t1 = new SimPLObjectType();
        SimPLObjectType t2 = new SimPLObjectType(LIST, t1);
        SimPLObjectType t3 = getChildType(node, data, 0);
        constraints.put(t3, t2);
        return t1;
    }

    @Override
    public Object visit(ASTTail node, Object data) {
        SimPLObjectType t1 = new SimPLObjectType();
        SimPLObjectType t2 = new SimPLObjectType(LIST, t1);
        SimPLObjectType t3 = getChildType(node, data, 0);
        constraints.put(t3, t2);
        return t3;
    }

    @Override
    public Object visit(ASTApplication node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (t1.getType() == null) {
            t1.setType(FUNC);
            t1.setEltType(t2);
            t1.setEltType2(new SimPLObjectType());
        } else {
            constraints.put(t1.getEltType(), t2);
        }
        return t1.getEltType2();
    }

    @Override
    public Object visit(ASTPair node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        SimPLObjectType t2 = getChildType(node, data, 1);
        return new SimPLObjectType(PAIR, t1, t2);
    }

    @Override
    public Object visit(ASTBracket node, Object data) {
        return getChildType(node, data, 0);
    }

    @Override
    public Object visit(ASTUnit node, Object data) {
        return new SimPLObjectType(UNIT);
    }
}
