package com.example.t2010aspringboot.repository;

import com.example.t2010aspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
