package com.liyaos.simpl.types;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-5-27
 * Time: 上午11:06
 */

public class TypeErrorException extends RuntimeException {
    public TypeErrorException(SimPLObjectType type, SimPLObjectType expected) {
        super("Type error detected! " + expected.toString() +
                " expected but " + type.toString() + " found!");
    }

    public TypeErrorException(Types type, Types expected) {
        super("Type error detected! " + expected.toString() +
                " expected but " + type.toString() + " found!");
    }
}
