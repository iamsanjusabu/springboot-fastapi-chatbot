package com.sanjusabu.springbootfastapi.service;

import com.sanjusabu.springbootfastapi.dto.UserRequestDTO;
import com.sanjusabu.springbootfastapi.entity.AppUser;
import com.sanjusabu.springbootfastapi.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public ResponseEntity<AppUser> getUserById(long id) {
        Optional<AppUser> user = userRepository.findById(id);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<AppUser> createUser(UserRequestDTO request) {
        Optional<AppUser> userFromDB = userRepository.findByUsername(request.getUsername());

        if (userFromDB.isEmpty()) {
            AppUser user = new AppUser();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setDateOfBirth(request.getDateOfBirth());
            user.setGender(request.getGender());
            user.setEmail(request.getEmail());
            userRepository.save(user);

            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    public ResponseEntity<AppUser> updateUser(Long id, UserRequestDTO request) {

        Optional<AppUser> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {

            AppUser user = optionalUser.get();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setDateOfBirth(request.getDateOfBirth());
            user.setGender(request.getGender());
            user.setEmail(request.getEmail());

            userRepository.save(user);

            return ResponseEntity.ok(user);
        }

        return ResponseEntity.notFound().build();

    }

    public ResponseEntity<Void> deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty())
            return ResponseEntity.notFound().build();

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
