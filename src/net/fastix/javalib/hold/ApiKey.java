package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

public class ApiKey extends Base {
    private String key;
    private long generated;

    public ApiKey(JSONObject json) {
        super(json);
        key = (String) json.get("apikey");
        generated = (long) json.get("generation_time");
    }

    public String getKey() {
        return key;
    }

    public long getGenerated() {
        return generated;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "key='" + key + '\'' +
                ", generated=" + generated +
                '}';
    }
}
