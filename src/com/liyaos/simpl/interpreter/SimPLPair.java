package com.liyaos.simpl.interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 上午1:20
 */
public class SimPLPair<Fst, Snd> extends SimPLObject {
    private Fst fst;
    private Snd snd;

    public SimPLPair(Fst fst, Snd snd) {
        this.fst = fst;
        this.snd = snd;
    }

    public Fst fst() {
        return fst;
    }

    public Snd snd() {
        return snd;
    }

    @Override
    public boolean equals(SimPLObject ob) {
        if (ob instanceof SimPLPair) {
            SimPLPair p = (SimPLPair) ob;
            return fst.equals(p.fst()) && snd.equals(p.snd);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pair<" + fst + ", " + snd + ">";
    }
}
