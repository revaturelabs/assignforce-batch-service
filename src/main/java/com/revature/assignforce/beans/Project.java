package com.revature.assignforce.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="PROJECT")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="PROJECT_NAME", nullable=false)
    private String name;

    @Column(name="PROJECT_DESC")
    private String description;

    @Column(name="PROJECT_OWNER", nullable=false)
    private String owner;

    @Column(name="ACTIVE", columnDefinition="default true")
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                active == project.active &&
                name.equals(project.name) &&
                description.equals(project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, active);
    }
}
