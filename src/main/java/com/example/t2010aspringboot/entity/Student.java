package com.example.t2010aspringboot.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String rollNumber;
    private String fullName;
    private String phone;
    private String address;
    private LocalDateTime dob;
    private int status;
}
