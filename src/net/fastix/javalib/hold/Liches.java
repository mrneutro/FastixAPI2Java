package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Anti liches domain names
 */
public class Liches extends AbstractItemList<String> {
    public Liches(JSONObject json) {
        super(json);
        JSONArray jsonArray = (JSONArray) json.get("allow");
        for (Object obj : jsonArray)
            list.add((String) obj);
    }
}
