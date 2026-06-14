package com.devforge.devforge_ai_backend.service;

import com.devforge.devforge_ai_backend.dto.ProjectDTO;
import com.devforge.devforge_ai_backend.entity.Project;
import com.devforge.devforge_ai_backend.entity.User;
import com.devforge.devforge_ai_backend.exception.ProjectNotFoundException;
import com.devforge.devforge_ai_backend.repository.ProjectRepository;
import com.devforge.devforge_ai_backend.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    private final UserRepository userRepository;

    public ProjectService(
            ProjectRepository projectRepository,
            UserRepository userRepository
    ) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {

        Authentication auth =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email =
                auth.getName();

        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );
    }

    public List<ProjectDTO> getAllProjects() {

        User user =
                getCurrentUser();

        return projectRepository
                .findByUser(user)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO createProject(
            ProjectDTO projectDTO
    ) {

        User user =
                getCurrentUser();

        Project project =
                new Project();

        project.setTitle(
                projectDTO.getTitle()
        );

        project.setDescription(
                projectDTO.getDescription()
        );

        project.setBlueprint(
                projectDTO.getBlueprint()
        );

        project.setArchitecture(
                projectDTO.getArchitecture()
        );

        project.setApiList(
                projectDTO.getApiList()
        );

        project.setGenerated(
                projectDTO.isGenerated()
        );

        project.setUser(user);

        Project saved =
                projectRepository.save(project);

        return convertToDTO(saved);
    }

    public ProjectDTO getProjectById(
            Long id
    ) {

        User user =
                getCurrentUser();

        Project project =
                projectRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ProjectNotFoundException(
                                                "Project not found"
                                        )
                        );

        if (!project.getUser()
                .getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "Access denied"
            );
        }

        return convertToDTO(project);
    }

    public ProjectDTO updateProject(
            Long id,
            ProjectDTO dto
    ) {

        User user =
                getCurrentUser();

        Project project =
                projectRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ProjectNotFoundException(
                                                "Project not found"
                                        )
                        );

        if (!project.getUser()
                .getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "Access denied"
            );
        }

        project.setTitle(
                dto.getTitle()
        );

        project.setDescription(
                dto.getDescription()
        );

        project.setBlueprint(
                dto.getBlueprint()
        );

        project.setArchitecture(
                dto.getArchitecture()
        );

        project.setApiList(
                dto.getApiList()
        );

        project.setGenerated(
                dto.isGenerated()
        );

        Project updatedProject =
                projectRepository.save(
                        project
                );

        return convertToDTO(
                updatedProject
        );
    }

    public void deleteProject(
            Long id
    ) {

        User user =
                getCurrentUser();

        Project project =
                projectRepository.findById(id)
                        .orElseThrow(
                                () ->
                                        new ProjectNotFoundException(
                                                "Project not found"
                                        )
                        );

        if (!project.getUser()
                .getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "Access denied"
            );
        }

        projectRepository.delete(
                project
        );
    }

    private ProjectDTO convertToDTO(
            Project project
    ) {

        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getBlueprint(),
                project.getArchitecture(),
                project.getApiList(),
                project.isGenerated()
        );
    }
}