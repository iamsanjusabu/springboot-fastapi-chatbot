package com.sanjusabu.springbootfastapi.entity;

import com.sanjusabu.springbootfastapi.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "user_table")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    // YYYY-MM-DD
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "email")
    private String email;
}
