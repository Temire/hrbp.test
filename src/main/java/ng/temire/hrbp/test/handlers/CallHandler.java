package ng.temire.hrbp.test.handlers;

import com.google.gson.Gson;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import ng.temire.hrbp.test.data.domains.entity.Result;
import ng.temire.hrbp.test.data.service.dtos.GenericResponseDTO;
import ng.temire.hrbp.test.data.service.ResultService;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CallHandler {

  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  private static final String APPLICATION_JSON = "application/json";

  private ResultService resultService;

  public CallHandler(ResultService resultService){
    this.resultService = resultService;
  }


  public void save(RoutingContext r) {
    String bodyRequest = r.getBodyAsString().toString();
    Result result = new Gson().fromJson(bodyRequest, Result.class);

    GenericResponseDTO responseDTO = resultService.save(result);
    r.response()
      .setStatusCode(responseDTO.getStatus().value())
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .end(Json.encodePrettily(responseDTO));
  }

  public void all(RoutingContext r) {
    String bodyRequest = r.getBodyAsString().toString();
    Result result = new Gson().fromJson(bodyRequest, Result.class);

    GenericResponseDTO responseDTO = resultService.getAllResults();
    r.response()
      .setStatusCode(responseDTO.getStatus().value())
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .end(Json.encodePrettily(responseDTO));
  }

  public void get_student_results_by_YearTermClass(RoutingContext r) {
    String bodyRequest = r.getBodyAsString().toString();
    JSONObject request = new JSONObject(bodyRequest);

    String year = request.getString("year");
    String term = request.getString("term");
    String studentId = request.getString("student");

    GenericResponseDTO responseDTO = resultService.getStudentResultsbyTerm(year,term,studentId);
    r.response()
      .setStatusCode(responseDTO.getStatus().value())
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .end(Json.encodePrettily(responseDTO));
  }

  public void get_student_results_by_YearTermSubjectClass(RoutingContext r) {
    String bodyRequest = r.getBodyAsString().toString();
    JSONObject request = new JSONObject(bodyRequest);

    String year = request.getString("year");
    String term = request.getString("term");
    String studentId = request.getString("student");

    GenericResponseDTO responseDTO = resultService.getStudentResultsbyTerm(year,term,studentId);
    r.response()
      .setStatusCode(responseDTO.getStatus().value())
      .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
      .end(Json.encodePrettily(responseDTO));
  }

}
