package com.devforge.devforge_ai_backend.repository;

import com.devforge.devforge_ai_backend.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}