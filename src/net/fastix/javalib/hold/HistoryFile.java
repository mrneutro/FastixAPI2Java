package net.fastix.javalib.hold;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generated HistoryFile
 */
public class HistoryFile extends DirectedFile {
    private String link;
    private long usedTraffic;
    private List<String> ipList = new ArrayList<>();
    private long statUpdated;
    private long linkGenerated;
    private boolean active;

    public HistoryFile(JSONObject json) {
        super(json);
        link = (String) json.get("link");
        usedTraffic = (long) json.get("really_dwnld");
        statUpdated = (long) json.get("statisticupdate");
        linkGenerated = (long) json.get("time");
        active = (boolean) json.get("active");
        JSONArray jIps = (JSONArray) json.get("download_ips");
        if (jIps != null && jIps.size() > 0) {
            for (Object o : jIps) {
                ipList.add((String) o);
            }
        }
    }

    /**
     * @return fileshare link
     */
    public String getLink() {
        return link;
    }

    /**
     * @return downladed data in KB
     */
    public long getUsedTraffic() {
        return usedTraffic;
    }

    /**
     * @return downloading IPS
     */
    public List<String> getIps() {
        return ipList;
    }

    /**
     * @return Timestamp of statistic update
     */
    public long getStatUpdated() {
        return statUpdated;
    }

    /**
     * @return Timstamp of generation
     */
    public long getLinkGenerated() {
        return linkGenerated;
    }

    /**
     * @return if link is downloadable
     */
    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "link='" + link + '\'' +
                ", usedTraffic=" + usedTraffic +
                ", ipList=" + Arrays.toString(ipList.toArray()) +
                ", statUpdated=" + statUpdated +
                ", linkGenerated=" + linkGenerated +
                ", active=" + active +
                '}';
    }
}
