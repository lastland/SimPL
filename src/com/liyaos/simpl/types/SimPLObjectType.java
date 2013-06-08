package com.liyaos.simpl.types;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-5-27
 * Time: 下午3:06
 */
public class SimPLObjectType {
    private Types type = null;
    private SimPLObjectType eltType = null;
    private SimPLObjectType eltType2 = null;

    public SimPLObjectType() {
        this.type = null;
        this.eltType = null;
        this.eltType2 = null;
    }

    public SimPLObjectType(Types type) {
        this.type = type;
        this.eltType = null;
        this.eltType2 = null;
    }

    public SimPLObjectType(Types type, SimPLObjectType eltType) {
        this.type = type;
        this.eltType = eltType;
        this.eltType2 = null;
    }

    public SimPLObjectType(Types type, SimPLObjectType eltType, SimPLObjectType eltType2) {
        this.type = type;
        this.eltType = eltType;
        this.eltType2 = eltType2;
    }

    public static SimPLObjectType createSimPLObject(Types type, SimPLObjectType... eltType) {
        switch (type) {
            case BOOL:
            case INT:
                return new SimPLObjectType(type);
            case LIST:
                return new SimPLObjectType(type, eltType[0]);
            case PAIR:
            case FUNC:
                return new SimPLObjectType(type, eltType[0], eltType[1]);
            default:
                throw new RuntimeException("Error!");
        }
    }

    private boolean isTypeAtom() {
        return (type == Types.BOOL || type == Types.INT);
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public SimPLObjectType getEltType() {
        return eltType;
    }

    public void setEltType(SimPLObjectType eltType) {
        this.eltType = eltType;
    }

    public SimPLObjectType getEltType2() {
        return eltType2;
    }

    public void setEltType2(SimPLObjectType eltType2) {
        this.eltType2 = eltType2;
    }

    public boolean equal(SimPLObjectType t) {
        if (t == null) return false;
        if (isTypeAtom()) {
            return type == t.getType();
        }
        return eltType.equal(t.eltType);
    }

    public boolean toBeInterferred() {
        return (type == null || (!isTypeAtom() &&
                (eltType == null ||
                        (type != Types.LIST && eltType2 == null))));
    }
}
