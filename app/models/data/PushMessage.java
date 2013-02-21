package models.data;

import org.codehaus.jackson.annotate.JsonProperty;
import play.data.validation.Constraints;

import java.util.LinkedList;
import java.util.List;

/**
 * Push message
 */
public class PushMessage {
    @JsonProperty("send_date")
    private String sendDate = "now";

    @JsonProperty("ignore_user_timezone")
    private boolean ignoreUserTimeZone = true;

    @Constraints.Required
    private String content;

    private List<Integer> platforms = new LinkedList<>();

    public PushMessage() {
        platforms.add(1);
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public boolean isIgnoreUserTimeZone() {
        return ignoreUserTimeZone;
    }

    public void setIgnoreUserTimeZone(boolean ignoreUserTimeZone) {
        this.ignoreUserTimeZone = ignoreUserTimeZone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Integer> platforms) {
        this.platforms = platforms;
    }
}
