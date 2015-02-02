package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Shares holder
 */
public class Shares extends AbstractItemList<String> {
    public Shares(JSONObject json) {
        super(json);
        JSONArray jsonArray = (JSONArray) json.get("allow");
        for (Object obj : jsonArray)
            list.add((String) obj);
    }
}
