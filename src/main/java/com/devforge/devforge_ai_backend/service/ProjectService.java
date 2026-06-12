package com.devforge.devforge_ai_backend.service;

import com.devforge.devforge_ai_backend.dto.ProjectDTO;
import com.devforge.devforge_ai_backend.entity.Project;
import com.devforge.devforge_ai_backend.exception.ProjectNotFoundException;
import com.devforge.devforge_ai_backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ProjectNotFoundException(
                                "Project with ID " + id + " not found"));

        return convertToDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {

        Project project = new Project();

        project.setTitle(projectDTO.getTitle());
        project.setDescription(projectDTO.getDescription());

        Project savedProject = projectRepository.save(project);

        return convertToDTO(savedProject);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO updatedProjectDTO) {

        Project project = projectRepository.findById(id)
                .orElseThrow(() ->
                        new ProjectNotFoundException(
                                "Project with ID " + id + " not found"));

        project.setTitle(updatedProjectDTO.getTitle());
        project.setDescription(updatedProjectDTO.getDescription());

        Project updatedProject = projectRepository.save(project);

        return convertToDTO(updatedProject);
    }

    public void deleteProject(Long id) {

        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(
                    "Project with ID " + id + " not found");
        }

        projectRepository.deleteById(id);
    }

    private ProjectDTO convertToDTO(Project project) {

        return new ProjectDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription()
        );
    }
}