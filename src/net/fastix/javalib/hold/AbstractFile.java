package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

/**
 * Created by Neutro on 25/01/2015.
 */
public abstract class AbstractFile extends Base {
    private String name;
    private long size;
    private long currentCost;
    private String projectName;

    public AbstractFile(JSONObject json) {
        super(json);
        size = (long) json.get("size");
        currentCost = (long) json.get("cost");
        name = json.containsKey("name") ? (String) json.get("name") : "n/a";
        projectName = (String) json.get("project");
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public long getCurrentCost() {
        return currentCost;
    }

    public String getProjectName() {
        return projectName;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", currentCost=" + currentCost +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
