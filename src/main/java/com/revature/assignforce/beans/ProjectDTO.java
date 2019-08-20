package com.revature.assignforce.beans;

import java.util.Arrays;
import java.util.Objects;

public class ProjectDTO {
    private int id;
    private String name;
    private String owner;
    private String url;
    private String description;
    private String[] sprints;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getSprints() {
        return sprints;
    }

    public void setSprints(String[] sprints) {
        this.sprints = sprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return id == that.id &&
                name.equals(that.name) &&
                owner.equals(that.owner) &&
                url.equals(that.url) &&
                description.equals(that.description) &&
                Arrays.equals(sprints, that.sprints);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, owner, url, description);
        result = 31 * result + Arrays.hashCode(sprints);
        return result;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", sprints=" + Arrays.toString(sprints) +
                '}';
    }
}
