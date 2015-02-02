package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fastix service status
 */
public class Status extends Base {
    private List<String> up = new ArrayList<>();
    private List<String> down = new ArrayList<>();
    private List<String> partially = new ArrayList<>();

    public Status(JSONObject json) {
        super(json);
        JSONObject data = (JSONObject) json.get("data");
        if (data.get("up") != null) {
            JSONArray jsonArray = (JSONArray) data.get("up");
            for (Object o : jsonArray) up.add((String) o);
        }
        if (data.get("down") != null) {
            JSONArray jsonArray = (JSONArray) data.get("down");
            for (Object o : jsonArray) down.add((String) o);
        }
        if (data.get("partially") != null) {
            JSONArray jsonArray = (JSONArray) data.get("partially");
            for (Object o : jsonArray) partially.add((String) o);
        }
    }

    /**
     * @return returns list of fully UP fileshares
     */
    public List<String> getUp() {
        return up;
    }

    /**
     * @return returns list of fully DOWN fileshares
     */
    public List<String> getDown() {
        return down;
    }

    /**
     * @return returns list of problemming fileshares
     */
    public List<String> getPartially() {
        return partially;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "up=" + Arrays.toString(up.toArray()) +
                ", down=" + Arrays.toString(down.toArray()) +
                ", partially=" + Arrays.toString(partially.toArray()) +
                '}';
    }
}
