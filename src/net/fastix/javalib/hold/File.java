package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * General file info
 */
public class File extends AbstractFile {
    private long regularCost;
    private long discount;
    private boolean folder;
    private String link;
    private List<File> innerFiles;

    public File(JSONObject json) {
        super(json);
        folder = json.containsKey("folder") && (boolean) json.get("folder");
        if (!folder) {
            regularCost = (long) json.get("regularcost");
            discount = (long) json.get("discount");
            link = (String) json.get("link");
        } else {
            innerFiles = new ArrayList<>();
            regularCost = (long) json.get("regularcost");
            discount = (long) json.get("discount");
            link = (String) json.get("link");
            JSONArray folderData = (JSONArray) json.get("data");
            for (Object obj : folderData) {
                innerFiles.add(new File((JSONObject) obj));
            }
        }
    }


    /**
     * @return folder
     */
    public boolean isFolder() {
        return folder;
    }

    /**
     * @return regular download cost of file, without discount
     */
    public long getRegularCost() {
        return regularCost;
    }

    /**
     * @return discount value, if is present
     */
    public long getDiscount() {
        return discount;
    }

    /**
     * @return fileshare link
     */
    public String getLink() {
        return link;
    }

    /**
     * @return if link points to folder, here are folder contents
     */
    public List<File> getInnerFiles() {
        return innerFiles;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                ", folder=" + folder +
                ", regularCost=" + regularCost +
                ", discount=" + discount +
                ", link='" + link + '\'' +
                ", innerFiles=" + innerFiles +
                '}';
    }
}
