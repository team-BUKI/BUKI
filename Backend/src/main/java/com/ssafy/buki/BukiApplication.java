package com.ssafy.buki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication

public class BukiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BukiApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(UserService userService){
//        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null, "ROLE_MANAGER"));
//            userService.saveRole(new Role(null, "ROLE_ADMIN"));
//            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User("abc@abc.com", SocialType.KAKAO, new ArrayList<>()));
//
//            userService.addRoleToUser(1L, "ROLE_USER");
//            userService.addRoleToUser(1L, "ROLE_ADMIN");
//        };
//    }
}
