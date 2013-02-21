package models.pushwoosh;

import models.data.PushMessage;
import models.pushwoosh.requests.CreateMessageRequest;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.libs.WS;
import play.mvc.Http;

import java.util.Arrays;

/**
 * Pushwoosh client
 */
public class Pushwoosh {
    /**
     * Pushwoosh request wrapper
     */
    private static class Request {
        /**
         * Request data
         */
        private Object request;

        private Request(Object request) {
            this.request = request;
        }

        public Object getRequest() {
            return request;
        }
    }

    /**
     * Pooshwoosh response wrapper
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Response {
        @JsonProperty("status_code")
        private int status;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    // Pushwoosh base url
    private static final String BASE_URL = "https://cp.pushwoosh.com/json/1.3/";

    /**
     * Send single push message
     * @param message Message
     * @return Promise for retrieving results
     */
    public static F.Promise<Boolean> createMessage(PushMessage message) {
        // Create request
        Request request = new Request(new CreateMessageRequest(Arrays.asList(message)));

        Logger.info("Sending request: " + Json.stringify(Json.toJson(request)));

        // Send request
        F.Promise<WS.Response> promise = WS.url(BASE_URL + "createMessage").post(Json.toJson(request));

        return promise.map(new F.Function<WS.Response, Boolean>() {
            @Override
            public Boolean apply(WS.Response response) throws Throwable {
                // Handle response

                if (response.getStatus() != Http.Status.OK) {
                    return false;
                }

                Response body = Json.fromJson(Json.parse(response.getBody()), Response.class);
                return body.getStatus() == Http.Status.OK;
            }
        });
    };
}
