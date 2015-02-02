package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Available nodes list
 */
public class Nodes extends AbstractItemList<String> {
    public Nodes(JSONObject json) {
        super(json);
        JSONArray jsonArray = (JSONArray) json.get("data");
        for (Object obj : jsonArray)
            list.add((String) obj);
    }
}
