package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

import java.io.Serializable;

public abstract class Base implements Serializable {
    private double requestTime;

    public Base(JSONObject json) {
        requestTime = json.containsKey("request") ? (double) json.get("request") : 0;
    }

    /**
     * @return Request time
     */
    public double getRequestTime() {
        return requestTime;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "requestTime=" + requestTime +
                '}';
    }
}
