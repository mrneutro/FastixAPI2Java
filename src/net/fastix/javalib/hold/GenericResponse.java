package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

/**
 * Generic fastix response
 */
public class GenericResponse extends Base {
    private String data;

    public GenericResponse(JSONObject json) {
        super(json);
        data = json.containsKey("data") ? (String) json.get("data") : null;
    }

    /**
     * Method returns request result as String
     *
     * @return
     */
    public String getData() {
        return data;
    }
}
