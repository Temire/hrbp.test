package ng.temire.hrbp.test;

import javax.annotation.PostConstruct;

import ng.temire.hrbp.test.data.domains.entity.Result;
import ng.temire.hrbp.test.data.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.vertx.core.Vertx;

import java.util.*;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("ng.temire.hrbp.test.data.repository")
@EntityScan("ng.temire.hrbp.test.data.domains.entity")
@ComponentScan(basePackages = { "ng.temire.hrbp.test" })
public class HRBPVertxSpringApplication {

  @Autowired
  private MainVerticle serverVerticle;

//  @Autowired
//  Vertx vertx;

  public static void main(String[] args) {
    SpringApplication.run(HRBPVertxSpringApplication.class, args);
  }

  @PostConstruct
  public void deployVerticle() {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(serverVerticle);
    System.out.println("Deployment done successfully!");
  }

}
