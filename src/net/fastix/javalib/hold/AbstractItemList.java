package net.fastix.javalib.hold;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractItemList<T> extends Base {
    protected List<T> list = new ArrayList<>();

    public AbstractItemList(JSONObject json) {
        super(json);
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "list=" + Arrays.toString(list.toArray()) +
                '}';
    }
}
