package com.devforge.devforge_ai_backend.entity;

import jakarta.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String blueprint;

    @Column(columnDefinition = "TEXT")
    private String architecture;

    @Column(columnDefinition = "TEXT")
    private String apiList;

    private boolean generated = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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