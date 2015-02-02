package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * Prices holder
 */
public class Prices extends AbstractItemList<Price> {
    public Prices(JSONObject json) {
        super(json);
        list = new ArrayList<>();
        JSONObject prices = (JSONObject) json.get("data");

        for (Object i : prices.keySet()) {
            list.add(new Price((String) i, (JSONObject) prices.get((String) i)));
        }
    }
}
