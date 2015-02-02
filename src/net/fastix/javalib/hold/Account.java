package net.fastix.javalib.hold;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * User account holder
 */
public class Account extends Base {
    private String name;
    private String email;
    private long points;
    private long download;
    private long apiGenTime;
    private String country;
    private long lastLogin;
    private String language;
    private long registerTime;
    private String timeZone;

    public Account(JSONObject json) throws ParseException {
        super(json);
        name = json.containsKey("name") ? json.get("name").toString() : null;
        email = json.containsKey("email") ? json.get("email").toString() : null;
        points = json.containsKey("points") ? ((Number) json.get("points")).longValue() : 0L;
        download = json.containsKey("download") ? ((Number) json.get("download")).longValue() : 0L;
        apiGenTime = json.containsKey("api_gen_time") && json.get("api_gen_time") != null ? ((Number) json.get("api_gen_time")).longValue() : 0L;
        country = json.containsKey("country") && json.get("country") != null ? json.get("country").toString() : null;
        lastLogin = json.containsKey("last_login") && json.get("last_login") != null ? ((Number) json.get("last_login")).longValue() : 0L;
        language = json.containsKey("language") && json.get("language") != null ? (String) json.get("language") : null;
        registerTime = json.containsKey("register_time") && json.get("register_time") != null ? ((Number) json.get("register_time")).longValue() : 0L;
        timeZone = json.get("timezone").toString();
    }

    /**
     * @return User name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return Current balance in micropoints (point*1000)
     */
    public long getPoints() {
        return points;
    }

    /**
     * @return Downloaded data in KB
     */
    public long getDownload() {
        return download;
    }

    /**
     * @return Timestamp when API key was generated or 0 if never
     */
    public long getApiGenTime() {
        return apiGenTime;
    }

    /**
     * @return Country in ISO standart ex. UK, RU
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return Timestamp of last login
     */
    public long getLastLogin() {
        return lastLogin;
    }

    /**
     * @return User selected language in ISO standart
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @return Timestamp of registration
     */
    public long getRegisterTime() {
        return registerTime;
    }

    /**
     * @return Timezone in UTC format ex. +1
     */
    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return super.toString() + "{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", download=" + download +
                ", apiGenTime=" + apiGenTime +
                ", country='" + country + '\'' +
                ", lastLogin=" + lastLogin +
                ", language='" + language + '\'' +
                ", registerTime=" + registerTime +
                ", timeZone='" + timeZone + '\'' +
                '}';
    }
}
