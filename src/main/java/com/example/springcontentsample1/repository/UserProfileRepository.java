package com.example.springcontentsample1.repository;

import com.example.springcontentsample1.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}