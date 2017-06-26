package by.pzh.jedi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class MMRSApp {

  public static void main(String[] args) {
    SpringApplication.run(MMRSApp.class, args);
  }

}
