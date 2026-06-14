package com.devforge.devforge_ai_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectDTO {

    private Long id;

    @NotBlank(message = "Title cannot be empty")
    @Size(
            min = 3,
            max = 100,
            message = "Title must be between 3 and 100 characters"
    )
    private String title;

    @NotBlank(
            message = "Description cannot be empty"
    )
    @Size(
            min = 5,
            max = 500000,
            message = "Description must be between 5 and 500000 characters"
    )
    private String description;

    private String blueprint;

    private String architecture;

    private String apiList;

    private boolean generated;

    public ProjectDTO() {
    }

    public ProjectDTO(
            Long id,
            String title,
            String description,
            String blueprint,
            String architecture,
            String apiList,
            boolean generated
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.blueprint = blueprint;
        this.architecture = architecture;
        this.apiList = apiList;
        this.generated = generated;
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(
            String title
    ) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(
            String description
    ) {
        this.description = description;
    }

    public String getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(
            String blueprint
    ) {
        this.blueprint = blueprint;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(
            String architecture
    ) {
        this.architecture = architecture;
    }

    public String getApiList() {
        return apiList;
    }

    public void setApiList(
            String apiList
    ) {
        this.apiList = apiList;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(
            boolean generated
    ) {
        this.generated = generated;
    }
}