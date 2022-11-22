package ng.temire.hrbp.test.data;

import ng.temire.hrbp.test.data.domains.entity.Result;
import ng.temire.hrbp.test.data.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DemoData implements ApplicationRunner {

  @Autowired
  private final ResultRepository repo;

  public DemoData(ResultRepository repo) {
    this.repo = repo;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    System.out.println("INSERTING DEMO DATA");
    List<String> classes = Arrays.asList("A1", "A2", "B1", "B2");
    Map<String, List<String>> class_subs = new HashMap<>();
    class_subs.put("A1", Arrays.asList("MATHEMATICS", "ENGLISH", "WRITING", "GENERAL SCIENCE"));
    class_subs.put("A2", Arrays.asList("MATHEMATICS", "ENGLISH", "ECONOMICS", "ARTS"));
    class_subs.put("B1", Arrays.asList("MATHEMATICS", "ENGLISH", "BIOLOGY", "PHYSICS", "CHEMISTRY", "GEOGRAPHY"));
    class_subs.put("B2", Arrays.asList("MATHEMATICS", "ENGLISH", "PAINTING", "DRAWING", "MUSIC", "ACCOUNTING"));

    Map<String, List<String>> names = new HashMap<>();
    names.put("A1", Arrays.asList("Jare Oladipo", "Mason Shettima", "Tobi Dairo", "Yunusa Ogara", "Lucky Ame", "Paul King", "Idris Coleman", "Eze Jare", "Rahman Jago", "Fregene Moses"));
    names.put("A2", Arrays.asList("Eze Mason", "Oladipo Tobi", "Shettima Yunusa", "Dairo Lucky", "Ogara Paul", "Ame Idris", "King Eze", "Coleman Shettima", "Mason Dairo", "Tobi Jare"));
    names.put("B1", Arrays.asList("Jare Eze", "Mason Oladipo", "Tobi Shettima", "Yunusa Dairo", "Lucky Ogara", "Paul Ame", "Idris King", "Eze Coleman", "Shettima Paul", "Dairo Idris"));
    names.put("B2", Arrays.asList("Eze Oladipo", "Oladipo Shettima", "Shettima Dairo", "Dairo Ogara", "Ogara Ame", "Ame King", "King Coleman", "Coleman ", "Paul Dairo", "Idris Victor"));

    for (String _class : classes) {
      List<String> subs = class_subs.get(_class);
      List<String> n = names.get(_class);
      System.out.println("Names: "+n.size());
      for (String sub : subs) {
        students(_class, sub, n);
      }
    }
  }


  public void students(String _class, String sub, List<String> names) {
    for (int i = 0; i < 10; i++) {
      String name = names.get(i);
      for (int q = 0; q < 3; q++) {
        int score = new Random().ints(10, 100)
          .findFirst()
          .getAsInt();
        Result result = new Result();
        result.setClassName(_class);
        result.setSchoolYear("2022/2023");
        result.setStudentId("2022/" + _class + "/" + (i + 1));
        result.setStudentName(name);
        result.setSchoolTerm("" + (q + 1));
        result.setSubjectId(sub.substring(0, 4));
        result.setSubjectName(sub);
        result.setScore(score);
        repo.save(result);
      }
    }
  }
}
