package com.example.springcontentsample3.repository;

import com.example.springcontentsample3.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}