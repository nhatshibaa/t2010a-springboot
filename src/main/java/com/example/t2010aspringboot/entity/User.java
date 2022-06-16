package com.example.t2010aspringboot.entity;

import com.example.t2010aspringboot.entity.base.BaseEntity;
import com.example.t2010aspringboot.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    private String id;
    private String fullName;
    private String phone;
    private String email;
    private String identityNumber;
    private String address;
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Column(updatable = false,insertable = false)
    @JsonIgnore
    private Set<Order> orders;
}
