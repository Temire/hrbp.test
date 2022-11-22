package ng.temire.hrbp.test.data.domains.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "result")
public class Result {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "result_id")
  private Integer id;

  @Column(name = "score")
  private int score;

  @Column(name = "student_id")
  private String studentId;

  @Column(name = "student_name")
  private String studentName;

  @Column(name = "subject_id")
  private String subjectId;

  @Column(name = "subject_name")
  private String subjectName;

  @Column(name = "class")
  private String className;

  @Column(name = "school_year")
  private String schoolYear;

  @Column(name = "school_term")
  private String schoolTerm;


}
