package com.liyaos.simpl.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-9
 * Time: 下午12:49
 */
public class Environment extends HashMap<String, Integer> {
    public Environment onion(Environment e) {
        Environment res = (Environment) clone();
        for (Map.Entry<String, Integer> i : e.entrySet()) {
            res.put(i.getKey(), i.getValue());
        }
        return res;
    }

    public Environment onion(String id, Integer addr) {
        Environment res = (Environment) clone();
        res.put(id, addr);
        return res;
    }
}
