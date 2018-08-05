package com.revature.assignforce.beans;

import com.revature.assignforce.validators.IntervalHolder;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Batch")
@IntervalHolder(startInterval ="startDate", endInterval="endDate", message="Start date is not before end date")
public class Batch {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Batch_ID")
	@SequenceGenerator(name = "Batch_ID", sequenceName = "Batch_ID_seq", allocationSize = 1)
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
	private Integer cotrainer;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Batch_Skills", joinColumns = @JoinColumn(name = "Batch_ID"), inverseJoinColumns = @JoinColumn(name = "Skill_ID"))
	private Set<SkillIdHolder> skills;

	@Column(name = "Address_Id")
	@Min(1)
	private Integer location;

	@Column(name = "Class_Size")
	@Min(5)
	@Max(35)
	@NotNull(message="classSize cannot be null")
	private Integer classSize;

	public Batch() {
		super();
	}

	public Batch(int id, String name, LocalDate startDate, LocalDate endDate, Integer curriculum, Integer trainer,
			Integer cotrainer, Set<SkillIdHolder> skills, Integer location, Integer classSize) {
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
		this.classSize = classSize;
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

	public Integer getClassSize() {
		return classSize;
	}

	public void setClassSize(Integer classSize) {
		this.classSize = classSize;
	}

}
