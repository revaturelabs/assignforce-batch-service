package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "SKILLIDHOLDER")
public class SkillIdHolder {

	@Id
	@Column(name = "SKILL_ID")
	private int skillID;

	public SkillIdHolder() {
		super();
	}

	public SkillIdHolder(int skillID) {
		super();
		this.skillID = skillID;
	}

	public int getSkillID() {
		return skillID;
	}

	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}

}