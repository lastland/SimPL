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
        return (type == Types.BOOL || type == Types.INT || type == Types.UNIT);
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

    public void become(SimPLObjectType t) {
        this.type = t.getType();
        this.eltType = t.getEltType();
        this.eltType2 = t.getEltType2();
    }

    public static void infer(SimPLObjectType t1, SimPLObjectType t2) {
        // if cannot be inferred
        if (t1.getType() == null && t2.getType() == null)
            return;
        // if don't need to be inferred
        if (t1.equal(t2))
            return;

        // if atom types
        if (t1.isTypeAtom() || t2.isTypeAtom()) {
            if (t1.isTypeAtom() && t2.getType() == null) {
                t2.setType(t1.getType());
            } else if (t2.isTypeAtom() && t1.getType() == null) {
                t1.setType(t2.getType());
            } else {
                return;
            }
        // if aggregated type
        } else {
            if (t1.getType() == null) {
                t1.setType(t2.getType());
            } else if (t2.getType() == null) {
                t2.setType(t1.getType());
            }

            if (t1.getEltType() == null) {
                t1.setEltType(t2.getEltType());
            } else if (t2.getEltType() == null) {
                t2.setEltType(t1.getEltType());
            } else {
                infer(t1.getEltType(), t2.getEltType());
            }

            if (t1.getType() == Types.PAIR || t1.getType() == Types.FUNC) {
                if (t1.getEltType2() == null) {
                    t1.setEltType2(t2.getEltType2());
                } else if (t2.getEltType2() == null) {
                    t2.setEltType2(t1.getEltType2());
                } else {
                    infer(t1.getEltType2(), t2.getEltType2());
                }
            }
        }
    }

    public boolean toBeInferred() {
        return (type == null || (!isTypeAtom() &&
                (eltType == null ||
                        (type != Types.LIST && eltType2 == null))));
    }

    public boolean equal(SimPLObjectType t) {
        if (t == null)
            return false;
        if (type == null || t.getType() == null)
            return false;
        if (isTypeAtom()) {
            return type == t.getType();
        } else {
            if (type != t.getType())
                return false;
            if (!eltType.equal(t.eltType))
                return false;
            if (type != Types.LIST && eltType2 != t.getEltType2())
                return false;
            return true;
        }
    }

    public boolean toBeInterferred() {
        return (type == null || (!isTypeAtom() &&
                (eltType == null ||
                        (type != Types.LIST && eltType2 == null))));
    }
}
