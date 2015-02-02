package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * History files holder
 */
public class HistoryFiles extends AbstractItemList<HistoryFile> {

    public HistoryFiles(JSONObject json) {
        super(json);
        JSONArray jsonArray = (JSONArray) json.get("data");
        if (jsonArray !=null)
            for (Object obj : jsonArray)
                list.add(new HistoryFile((JSONObject) obj));
    }
}
