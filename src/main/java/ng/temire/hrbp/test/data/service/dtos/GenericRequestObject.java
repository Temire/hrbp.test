package ng.temire.hrbp.test.data.service.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GenericRequestObject {

  String student;
  String year;
  String subject;
  String term;
  String _class;

}
