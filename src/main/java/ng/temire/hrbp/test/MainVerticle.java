package ng.temire.hrbp.test;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import ng.temire.hrbp.test.data.service.ResultService;
import ng.temire.hrbp.test.handlers.CallHandler;
import ng.temire.hrbp.test.routes.APIRoutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainVerticle extends AbstractVerticle {

  @Autowired
  private ResultService resultService;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    CallHandler handler = new CallHandler(resultService);
    APIRoutes routes = new APIRoutes(vertx,handler);
    vertx.createHttpServer().requestHandler(routes.apiRouter())
      .listen(8081)
      // Print the port
      .onSuccess(server -> {
        startPromise.complete();
        System.out.println("HTTP server started on port " + server.actualPort());
      })
      .onFailure(event -> {
        startPromise.fail(event);
        System.out.println("Failed to start HTTP server:" + event.getMessage());
      });
  }
}
