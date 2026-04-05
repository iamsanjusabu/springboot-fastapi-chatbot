package com.sanjusabu.springbootfastapi.component;

import com.sanjusabu.springbootfastapi.entity.AppUser;
import com.sanjusabu.springbootfastapi.enums.Gender;
import com.sanjusabu.springbootfastapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            AppUser user = new AppUser();

            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("adminSanju1234"));
            user.setDateOfBirth(LocalDate.of(2006, 9, 16));
            user.setGender(Gender.MALE);
            user.setEmail("sanjusabu@icloud.com");

            userRepository.save(user);
        }
    }
}
