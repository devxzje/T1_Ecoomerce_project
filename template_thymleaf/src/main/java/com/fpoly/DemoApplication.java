package com.fpoly;

import com.fpoly.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
         SpringApplication.run(DemoApplication.class, args);
//        System.out.println(app.getBean(UserRepository.class) == null ? "vcc" : "Ngon");
    }

}
