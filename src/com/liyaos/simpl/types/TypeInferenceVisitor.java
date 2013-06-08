package com.liyaos.simpl.types;

import com.liyaos.simpl.parser.*;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午2:59
 */
public class TypeInferenceVisitor implements SimPLParserVisitor {

    private Constraints constraints;

    public TypeInferenceVisitor() {
        constraints = new Constraints();
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTLet node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTIf node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTWhile node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTSequence node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTAnonymousFunction node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTCons node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTEq node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTLessThan node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTPlus node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTMinus node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTTimes node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTDivide node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTNegative node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTBool node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTInt node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTNil node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTFst node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTSnd node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTHead node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTTail node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTApplication node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTPair node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object visit(ASTBracket node, Object data) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
