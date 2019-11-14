package com.revature.assignforce.beans;

import com.revature.assignforce.validators.IntervalHolder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "BATCH")
public class BatchH2 {

	@Id
	@Column(name = "Batch_ID")
	private int id;

	@Column(name = "Batch_Name")
	@NotNull(message="name cannot be null")
	@Size(min = 1, max = 128)
	private String name;

	@Column(name = "start_Date")
	@NotNull (message="startDate cannot be null")
	private LocalDate startDate;

	@Column(name = "end_Date")
	@NotNull(message="endDate cannot be null")
	private LocalDate endDate;

	@Column(name = "Curriculum_Id")
	@Min(1)
	private Integer curriculum;

	@Column(name = "Trainer_Id")
	@Min(1)
	private Integer trainer;


	@Column(name = "Cotrainer_Id")
	@Min(1)
	private Integer cotrainer;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Batch_Skills", joinColumns = @JoinColumn(name = "Batch_ID"), inverseJoinColumns = @JoinColumn(name = "Skill_ID"))
	private Set<SkillIdHolder> skills;


	@Min(1)
	@Column(name = "LOCATION_ID")
	private Integer location;

	@Column(name = "BUILDING_ID")
	private Integer building;

	@Column(name = "ROOM_ID")
	private Integer room;

	@Column(name = "Class_Size")
	@Min(5)
	@Max(35)
	@NotNull(message="size cannot be null")
	private Integer size;

	public BatchH2() {
		super();
	}


	public BatchH2(int id, String name, LocalDate startDate, LocalDate endDate, Integer curriculum, Integer trainer,
                   Integer cotrainer, Set<SkillIdHolder> skills, Integer location, Integer building, Integer room, Integer size) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.curriculum = curriculum;
		this.trainer = trainer;
		this.cotrainer = cotrainer;
		this.skills = skills;
		this.location = location;
		this.room = room;
		this.size = size;
	}

	public BatchH2(Batch batch) {
		super();
		this.id = batch.getId();
		this.name = batch.getName();
		this.startDate = batch.getStartDate();
		this.endDate = batch.getEndDate();
		this.curriculum = batch.getCurriculum();
		this.trainer = batch.getTrainer();
		this.cotrainer = batch.getCotrainer();
		this.skills = batch.getSkills();
		this.location = batch.getLocation();
		this.room = batch.getRoom();
		this.size = batch.getSize();
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Integer getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Integer curriculum) {
		this.curriculum = curriculum;
	}

	public Integer getTrainer() {
		return trainer;
	}

	public void setTrainer(Integer trainer) {
		this.trainer = trainer;
	}

	public Integer getCotrainer() {
		return cotrainer;
	}

	public void setCotrainer(Integer cotrainer) {
		this.cotrainer = cotrainer;
	}

	public Set<SkillIdHolder> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillIdHolder> skills) {
		this.skills = skills;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public Integer getBuilding() {
		return building;
	}

	public void setBuilding(Integer building) {
		this.building = building;
	}

	public Integer getRoom() {
		return room;
	}

	public void setRoom(Integer room) {
		this.room = room;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
