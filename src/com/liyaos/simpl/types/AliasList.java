package com.liyaos.simpl.types;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午3:59
 */
public class AliasList extends HashMap<String, String> {

    public AliasList onion(AliasList list) {
        AliasList res = (AliasList)this.clone();
        res.putAll(list);
        return res;
    }

    public AliasList onion(String s1, String s2) {
        AliasList res = (AliasList)this.clone();
        res.put(s1, s2);
        return res;
    }
}
