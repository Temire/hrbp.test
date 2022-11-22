package ng.temire.hrbp.test.routes;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import ng.temire.hrbp.test.exceptions.RecordNotFoundException;
import ng.temire.hrbp.test.handlers.CallHandler;

public class APIRoutes {

  private Vertx vertx;
  private CallHandler handlers;

  public APIRoutes(Vertx vertx, CallHandler handlers) {
    this.vertx = vertx;
    this.handlers = handlers;
  }

  public Router apiRouter(){
    Router router = Router.router(vertx);

    router.get("/results").produces("application/json").handler(handlers::all);

    router.post("/results").consumes("application/json").consumes("application/json")
      .handler(BodyHandler.create()).handler(handlers::save);

    router.post("/get-student-results/subject/term/class").consumes("application/json").produces("application/json")
      .handler(BodyHandler.create()).handler(handlers::get_student_results_by_YearTermSubjectClass);

    router.post("/get-student-all-results/term/").consumes("application/json").produces("application/json")
      .handler(BodyHandler.create()).handler(handlers::get_student_results_by_YearTermClass);

    //PS:  To Test the Deployment
    router.get("/hello").handler(rc -> rc.response().end("Hello from my Temire's route"));

    return router;
  }

}
