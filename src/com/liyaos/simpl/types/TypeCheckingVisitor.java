package com.liyaos.simpl.types;

import com.liyaos.simpl.parser.*;

import static com.liyaos.simpl.types.Types.*;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午8:23
 */
public class TypeCheckingVisitor implements SimPLParserVisitor {

    private NameList nameList;

    private static SimPLObjectType intType = new SimPLObjectType(INT);
    private static SimPLObjectType boolType = new SimPLObjectType(BOOL);
    private static SimPLObjectType unitType = new SimPLObjectType(UNIT);

    public TypeCheckingVisitor(NameList nameList) {
        this.nameList = nameList;
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
        nameList.resetCounter();
        AliasList aliasList = new AliasList();
        getChildType(node, aliasList, 0);
        return null;
    }

    @Override
    public Object visit(ASTLet node, Object data) {
        getChildType(node, data, 1);
        return getChildType(node, data, 2);
    }

    @Override
    public Object visit(ASTIf node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(boolType)) {
            throw new TypeErrorException(t1, boolType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        SimPLObjectType t3 = getChildType(node, data, 2);
        if (!t2.equal(t3)) {
            throw new TypeErrorException(t2, t3);
        }
        return t2;
    }

    @Override
    public Object visit(ASTWhile node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(boolType)) {
            throw new TypeErrorException(t1, boolType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(unitType)) {
            throw new TypeErrorException(t2, unitType);
        }
        return t2;
    }

    @Override
    public Object visit(ASTSequence node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(unitType)) {
            throw new TypeErrorException(t1, unitType);
        }
        return getChildType(node, data, 1);
    }

    @Override
    public Object visit(ASTAnonymousFunction node, Object data) {
        AliasList aliasList = new AliasList();
        ASTIdentifier id = (ASTIdentifier)node.jjtGetChild(0);
        String newName = nameList.getNewAnonymousName();
        aliasList.put(id.getValue(), newName);
        return new SimPLObjectType(FUNC, nameList.get(newName),
                getChildType(node, ((AliasList)data).onion(aliasList), 1));
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t1.equal(t2)) {
            throw new TypeErrorException(t1, t2);
        }
        return unitType;
    }

    @Override
    public Object visit(ASTCons node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (t2.getEltType() == null) {
            t2.setEltType(t1);
        }
        if (!t1.equal(t2.getEltType())) {
            throw new TypeErrorException(t1, t2.getEltType());
        }
        return t2;
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(boolType)) {
            throw new TypeErrorException(t1, boolType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(boolType)) {
            throw new TypeErrorException(t2, boolType);
        }
        return boolType;
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(boolType)) {
             throw new TypeErrorException(t1, boolType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(boolType)) {
             throw new TypeErrorException(t2, boolType);
        }
        return boolType;
    }

    @Override
    public Object visit(ASTEq node, Object data) {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return boolType;
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return boolType;
    }

    @Override
    public Object visit(ASTLessThan node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return boolType;
    }

    @Override
    public Object visit(ASTPlus node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return intType;
    }

    @Override
    public Object visit(ASTMinus node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return intType;
    }

    @Override
    public Object visit(ASTTimes node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return intType;
    }

    @Override
    public Object visit(ASTDivide node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (!t1.equal(intType)) {
            throw new TypeErrorException(t1, intType);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t2.equal(intType)) {
            throw new TypeErrorException(t2, intType);
        }
        return intType;
    }

    @Override
    public Object visit(ASTNegative node, Object data) throws TypeErrorException {
        SimPLObjectType t = getChildType(node, data, 0);
        if (!t.equal(intType)) {
            throw new TypeErrorException(t, intType);
        }
        return intType;
    }

    @Override
    public Object visit(ASTNot node, Object data) throws TypeErrorException {
        SimPLObjectType t = getChildType(node, data, 0);
        if (!t.equal(boolType)) {
            throw new TypeErrorException(t, boolType);
        }
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
        return new SimPLObjectType(LIST);
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
    public Object visit(ASTFst node, Object data) throws TypeErrorException {
        SimPLObjectType t = getChildType(node, data, 0);
        if (t.getType() != PAIR) {
            throw new TypeErrorException(t.getType(), PAIR);
        }
        return t.getEltType();
    }

    @Override
    public Object visit(ASTSnd node, Object data) throws TypeErrorException {
        SimPLObjectType t = getChildType(node, data, 0);
        if (t.getType() != PAIR) {
            throw new TypeErrorException(t.getType(), PAIR);
        }
        return t.getEltType2();
    }

    @Override
    public Object visit(ASTHead node, Object data) throws TypeErrorException {
        SimPLObjectType t = getChildType(node, data, 0);
        if (t.getType() != LIST) {
            throw new TypeErrorException(t.getType(), LIST);
        }
        return t.getEltType();
    }

    @Override
    public Object visit(ASTTail node, Object data) throws TypeErrorException {
        SimPLObjectType t = getChildType(node, data, 0);
        if (t.getType() != LIST) {
            throw new TypeErrorException(t.getType(), LIST);
        }
        return t;
    }

    @Override
    public Object visit(ASTApplication node, Object data) throws TypeErrorException {
        SimPLObjectType t1 = getChildType(node, data, 0);
        if (t1.getType() != FUNC) {
            throw new TypeErrorException(t1.getType(), FUNC);
        }
        SimPLObjectType t2 = getChildType(node, data, 1);
        if (!t1.getEltType().equal(t2)) {
            throw new TypeErrorException(t1.getEltType(), t2);
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
