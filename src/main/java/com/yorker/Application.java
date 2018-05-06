package com.yorker; /**
 * Created by gyk on 2016/9/21.
 *
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.support.SpringBootServletInitializer;


//@EnableJpaRepositories(basePackages = "com.yorker")
@SpringBootApplication
public class Application {//extends SpringBootServletInitializer {

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        setRegisterErrorPageFilter(false);
//        return builder.sources(Application.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        }
}
