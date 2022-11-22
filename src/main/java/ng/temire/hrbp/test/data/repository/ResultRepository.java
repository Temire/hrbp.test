package ng.temire.hrbp.test.data.repository;

import ng.temire.hrbp.test.data.domains.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Integer> {

  @Query("select r from Result r " +
    "where r.schoolYear = ?1 and r.schoolTerm = ?2 and r.studentId = ?3 and r.subjectId = ?4")
  Optional<Result> findAllBySchoolYearAndSchoolTermAndStudentIdAndSubjectId(String year, String term, String id, String sid);

  @Query("select r from Result r " +
    "where r.schoolYear = ?1 and r.schoolTerm = ?2 and r.studentId = ?3 and r.subjectId = ?4 and r.className = ?5")
  Optional<Result> findAllBySchoolYearAndSchoolTermAndStudentIdAndSubjectIdAndClassName(String year, String term, String id, String sid, String _class);

  @Query("select r from Result r " +
    "where r.schoolTerm = ?1 and r.studentId = ?2 and r.subjectId = ?3 and r.className = ?4")
  List<Result> findAllBySchoolTermAndStudentIdAndSubjectIdAndClassName(String term, String id, String sid, String _class);

  @Query("select r from Result r " +
    "where r.schoolYear = ?1 and r.schoolTerm = ?2 and r.studentId = ?3 and r.subjectName = ?4")
  Optional<Result> findAllBySchoolYearAndSchoolTermAndStudentIdAndSubjectName(String year, String term, String id, String sid);

  @Query("select r from Result r where r.schoolYear = ?1 and r.schoolTerm = ?2 and r.studentId = ?3")
  List<Result> findAllBySchoolYearAndSchoolTermAndStudentId(String year, String term, String id);

  @Query("select r from Result r where r.schoolYear = ?1 and r.schoolTerm = ?2 and r.studentName = ?3")
  List<Result> findAllBySchoolYearAndSchoolTermAndStudentName(String year, String term, String id);

  @Query("select AVG(r.score) from Result r where r.schoolYear = ?1 and r.schoolTerm = ?2 and r.studentId = ?3")
  double avgScore(String year, String term, String id);
}
