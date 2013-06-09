package com.liyaos.simpl.types;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午3:06
 */
public class Constraints extends HashSet<Map.Entry<SimPLObjectType, SimPLObjectType>> {

    public void infer() {
        SimPLObjectType t1, t2;
        int s = 0;
        while (!isEmpty()) {
            for (Iterator<Map.Entry<SimPLObjectType, SimPLObjectType>> it = iterator(); it.hasNext();) {
                Map.Entry<SimPLObjectType, SimPLObjectType> e = it.next();
                t1 = e.getKey();
                t2 = e.getValue();
                SimPLObjectType.infer(t1, t2);
                if (!t1.toBeInferred()) {
                    it.remove();
                }
            }
            if (s == size()) {
                break;
            }
            s = size();
        }
    }

    public void put(SimPLObjectType t1, SimPLObjectType t2) {
        add(new AbstractMap.SimpleEntry<SimPLObjectType, SimPLObjectType>(t1, t2));
    }
}
