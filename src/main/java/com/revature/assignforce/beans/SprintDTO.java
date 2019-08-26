package com.revature.assignforce.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Objects;

public class SprintDTO {
    private int id;
    private String url;
    @JsonProperty("created_at")
    private Timestamp createdAt;
    @JsonProperty("updated_at")
    private Timestamp updatedAt;
    @JsonProperty("columns_url")
    private String columnsUrl;
    private String name;
    @JsonProperty("body")
    private String description;
    private String state;

    public SprintDTO() {}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getColumnsUrl() {
        return columnsUrl;
    }

    public void setColumnsUrl(String columnsUrl) {
        this.columnsUrl = columnsUrl;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintDTO sprintDTO = (SprintDTO) o;
        return id == sprintDTO.id &&
                url.equals(sprintDTO.url) &&
                createdAt.equals(sprintDTO.createdAt) &&
                Objects.equals(updatedAt, sprintDTO.updatedAt) &&
                Objects.equals(columnsUrl, sprintDTO.columnsUrl) &&
                name.equals(sprintDTO.name) &&
                description.equals(sprintDTO.description) &&
                state.equals(sprintDTO.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, createdAt, updatedAt, columnsUrl, name, description, state);
    }

    @Override
    public String toString() {
        return "SprintDTO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", columnsUrl='" + columnsUrl + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
