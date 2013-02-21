package controllers;

import models.data.PushMessage;
import models.pushwoosh.Pushwoosh;
import play.data.Form;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.results;

public class Application extends Controller {
    public static Result index() {
        Form<PushMessage> form = Form.form(PushMessage.class);
        return ok(index.render(form));
    }

    public static Result send() {
        final Form<PushMessage> form = Form.form(PushMessage.class).bindFromRequest();

        // Validate data
        if (form.hasErrors()) {
            return ok(index.render(form));
        }

        // Send message
        F.Promise<Boolean> promise = Pushwoosh.createMessage(form.get());

        return async(promise.map(new F.Function<Boolean, Result>() {
            @Override
            public Result apply(Boolean success) throws Throwable {
                // Render response
                return ok(results.render(success));
            }
        }));
    }
  
}
