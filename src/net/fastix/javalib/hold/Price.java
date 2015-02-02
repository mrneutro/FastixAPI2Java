package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

/**
 * Single share price
 * <p/>
 * Prices, by Fastix reference, is measured in micropoints,
 * regular point*1000
 */

public class Price {
    private String name;
    private double regularCost;
    private double currentCost;
    private double discountSize;

    public Price(String name, JSONObject json) {
        this.name = name;

        regularCost = ((Number) json.get("regular")).doubleValue();
        discountSize = ((Number) json.get("discount")).doubleValue();
        currentCost = ((Number) json.get("current")).doubleValue();
    }

    /**
     * @return fileShare name
     */
    public String getName() {
        return name;
    }

    /**
     * @return regular cost of 1 gb without discount
     */
    public double getRegularCost() {
        return regularCost;
    }

    /**
     * @return current cost of 1 gb with discount applied (if is)
     */
    public double getCurrentCost() {
        return currentCost;
    }

    /**
     * @return discount size (if is present)
     */
    public double getDiscountSize() {
        return discountSize;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "name='" + name + '\'' +
                ", regularCost=" + regularCost +
                ", currentCost=" + currentCost +
                ", discountSize=" + discountSize +
                '}';
    }
}
