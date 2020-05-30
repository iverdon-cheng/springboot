package cn.iverdon.springboot05security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、引入SpringSecurity；
 * 2、编写SpringSecurity的配置；
 * 3、 @EnableWebSecurity  extends WebSecurityConfigurerAdapter
 */
@SpringBootApplication
public class Springboot05SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05SecurityApplication.class, args);
    }

}
