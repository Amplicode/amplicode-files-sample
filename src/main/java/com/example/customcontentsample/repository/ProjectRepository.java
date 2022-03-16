package com.example.customcontentsample.repository;

import com.example.customcontentsample.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}