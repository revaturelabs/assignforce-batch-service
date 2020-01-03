package com.revature.assignforce.beans.revaturepro;

import java.util.List;

public class RevatureProData {

    private String salesforceId;
    private String description;
    private String name;
    private String startDate;
    private String endDate;
    private String skill;
    private String location;
    private String type;
    private List<RevatureProTrainer> trainer;
    private List<RevatureProCoTrainers> coTrainers;

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RevatureProTrainer> getTrainer() {
        return trainer;
    }

    public void setTrainer(List<RevatureProTrainer> trainer) {
        this.trainer = trainer;
    }

    public List<RevatureProCoTrainers> getCoTrainers() {
        return coTrainers;
    }

    public void setCoTrainers(List<RevatureProCoTrainers> coTrainers) {
        this.coTrainers = coTrainers;
    }
}
