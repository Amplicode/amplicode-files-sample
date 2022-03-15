package com.example.springcontentsample2.repository;

import com.example.springcontentsample2.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}