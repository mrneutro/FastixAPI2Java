package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Sources holder
 */
public class Sources extends AbstractItemList<String> {
    public Sources(JSONObject json) {
        super(json);
        JSONArray jsonArray = (JSONArray) json.get("allow");
        for (Object obj : jsonArray)
            list.add((String) obj);
    }
}
