package ng.temire.hrbp.test.data.service.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ng.temire.hrbp.test.data.domains.entity.Result;

import java.util.List;

@Data
@Getter
@Setter
public class ResponseDTO {

  List<Result> results;

  double avgScore;

  String studentName;

  String schoolYear;

  String schoolTerm;
}
