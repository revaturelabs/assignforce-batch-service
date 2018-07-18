package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="SKILLIDHOLDER")
public class SkillIdHolder {
	
	@Id
	@Column(name="TRAINERID")
	private int skillID;

	public SkillIdHolder() {
		super();
	}

	public SkillIdHolder(int skillID) {
		super();
		this.skillID = skillID;
	}

	public int getTrainerId() {
		return skillID;
	}

	public void setTrainerId(int skillID) {
		this.skillID = skillID;
	}
	
	
}