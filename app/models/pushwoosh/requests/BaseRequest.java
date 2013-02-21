package models.pushwoosh.requests;

import play.Configuration;

/**
 * Base pushwoosh request
 */
public class BaseRequest {
    private String application;
    private String auth;

    public BaseRequest() {
        application = Configuration.root().getString("pushwoosh.application_code", "");
        auth = Configuration.root().getString("pushwoosh.auth_token", "");
    }

    public String getApplication() {
        return application;
    }

    public String getAuth() {
        return auth;
    }
}
