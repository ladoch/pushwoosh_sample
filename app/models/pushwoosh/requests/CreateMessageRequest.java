package models.pushwoosh.requests;

import models.data.PushMessage;

import java.util.List;

/**
 * Create message request
 */
public class CreateMessageRequest extends BaseRequest {
    private List<PushMessage> notifications;

    public CreateMessageRequest(List<PushMessage> notifications) {
        this.notifications = notifications;
    }


    public List<PushMessage> getNotifications() {
        return notifications;
    }
}
