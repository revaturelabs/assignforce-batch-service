package com.revature.assignforce.beans.revaturepro;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RevatureProBatchDTO {

    private int statusCode;
    private String description;
    private List<RevatureProData> data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RevatureProData> getData() {
        return data;
    }

    public void setData(List<RevatureProData> data) {
        this.data = data;
    }
}
