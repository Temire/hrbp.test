package ng.temire.hrbp.test.data.service;

import ng.temire.hrbp.test.data.domains.entity.Result;
import ng.temire.hrbp.test.data.service.dtos.GenericResponseDTO;
import ng.temire.hrbp.test.data.repository.ResultRepository;
import ng.temire.hrbp.test.data.service.dtos.ResponseDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
  @Autowired
  private final ResultRepository repository;

  public ResultService(ResultRepository repository){
    this.repository = repository;
  }

  public GenericResponseDTO save(Result result){
    try {
      Result save = repository.save(result);
      return new GenericResponseDTO("00", HttpStatus.OK, "Returned Successfully", save);
    }catch(Exception ex){
      return new GenericResponseDTO("99", HttpStatus.INTERNAL_SERVER_ERROR, "Error Retrieving Records", null);
    }
  }


  public GenericResponseDTO getAllResults(){
    try{
    return new GenericResponseDTO("00", HttpStatus.OK, "Returned Successfully", repository.findAll());
  }catch(Exception ex){
    return new GenericResponseDTO("99", HttpStatus.INTERNAL_SERVER_ERROR, "Error Retrieving Records", null);
  }
  }

  public GenericResponseDTO getStudentResultsByYearTermClass(String year, String term, String studentid, String subject, String stdclass){
    try{
    return new GenericResponseDTO("00", HttpStatus.OK, "Returned Successfully", repository.findAllBySchoolYearAndSchoolTermAndStudentIdAndSubjectIdAndClassName( year,  term,  studentid,  subject,  stdclass));
    }catch(Exception ex){
      return new GenericResponseDTO("00", HttpStatus.INTERNAL_SERVER_ERROR, "Error Retrieving Records", null);
    }
  }

  public GenericResponseDTO getStudentResultsbyTerm(String year, String term, String studentId){
    GenericResponseDTO responseDTO = new GenericResponseDTO();
    ResponseDTO response = new ResponseDTO();
    try{
    List<Result> results = repository.findAllBySchoolYearAndSchoolTermAndStudentId(year, term, studentId);
    if(ObjectUtils.isNotEmpty(results)) {
      response.setResults(results);
      double avg = repository.avgScore(year, term, studentId);
      response.setAvgScore(avg);
      response.setSchoolYear(year);
      response.setSchoolTerm(term);
      response.setStudentName(results.get(0).getStudentName());
      responseDTO.setCode("00");
      responseDTO.setData(response);
      responseDTO.setMessage("Records Found for Student "+studentId);
      responseDTO.setStatus(HttpStatus.OK);
      responseDTO.setMetadata(null);
    }
    else{
      responseDTO.setCode("99");
      responseDTO.setMessage("No records found!");
      responseDTO.setStatus(HttpStatus.NO_CONTENT);
      responseDTO.setMetadata(null);
    }
    return responseDTO;
    }catch(Exception ex){
      return new GenericResponseDTO("00", HttpStatus.INTERNAL_SERVER_ERROR, "Error Retrieving Records", null);
    }
  }


}
