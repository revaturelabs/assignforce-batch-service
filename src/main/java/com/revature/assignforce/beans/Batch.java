package com.revature.assignforce.beans;

import java.util.Date;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Batch")
public class Batch {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Batch_ID")
	@SequenceGenerator(name="Batch_ID", sequenceName="Batch_ID_seq", allocationSize=1)
	@Column(name="Batch_ID")
	private int id;

	@Column(name="Batch_Name")
	private String name;
	
	@Column(name="start_Date")
	private Date startDate;
	
	@Column(name="end_Date")
	private Date endDate;
	
	@Column(name="Curriculum_Id")
	private int curriculum;
	
	@Column(name="Trainer_Id")
	private int trainer;
	
	@Column(name="Cotrainer_Id")
	private int cotrainer;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(name="Batch_Skills",
			joinColumns=@JoinColumn(name="Batch_ID"),
			inverseJoinColumns=@JoinColumn(name="Skill_ID"))
	private Set<SkillIdHolder> Skills;
	
	@Column(name="Address_Id")
	private int address;
	
	@Column(name="Building_Id")
	private int building;
	
	@Column(name="Room_Id")
	private int room;
	
	
	public Batch() {
		super();
	}


	public Batch(int id, String name, Date startDate, Date endDate, int curriculum, int trainer, int cotrainer,
			Set<SkillIdHolder> skills, int address, int building, int room) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.curriculum = curriculum;
		this.trainer = trainer;
		this.cotrainer = cotrainer;
		Skills = skills;
		this.address = address;
		this.building = building;
		this.room = room;
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


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getCurriculum() {
		return curriculum;
	}


	public void setCurriculum(int curriculum) {
		this.curriculum = curriculum;
	}


	public int getTrainer() {
		return trainer;
	}


	public void setTrainer(int trainer) {
		this.trainer = trainer;
	}


	public int getCotrainer() {
		return cotrainer;
	}


	public void setCotrainer(int cotrainer) {
		this.cotrainer = cotrainer;
	}


	public Set<SkillIdHolder> getSkills() {
		return Skills;
	}


	public void setSkills(Set<SkillIdHolder> skills) {
		Skills = skills;
	}


	public int getAddress() {
		return address;
	}


	public void setAddress(int address) {
		this.address = address;
	}


	public int getBuilding() {
		return building;
	}


	public void setBuilding(int building) {
		this.building = building;
	}


	public int getRoom() {
		return room;
	}


	public void setRoom(int room) {
		this.room = room;
	}

	
}
