package com.liyaos.simpl.types;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lastland
 * Date: 13-6-8
 * Time: 下午3:27
 */
public class NameList extends HashMap<String, SimPLObjectType> {

    private int anonymousCounter = 0;

    public NameList() {
        super();
    }

    public String getNewAnonymousName() {
        return "@" + anonymousCounter++;
    }

    public void resetCounter() {
        anonymousCounter = 0;
    }
}
