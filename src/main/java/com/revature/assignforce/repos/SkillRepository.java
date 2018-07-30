package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.SkillIdHolder;

@Repository
public interface SkillRepository extends JpaRepository<SkillIdHolder, Integer>{

}
