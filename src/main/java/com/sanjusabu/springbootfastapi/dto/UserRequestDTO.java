package com.sanjusabu.springbootfastapi.dto;

import com.sanjusabu.springbootfastapi.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String email;
}
