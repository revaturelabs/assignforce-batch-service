package com.revature.assignforce.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer>{

	public List<Batch> findByCurriculum(Integer id);
	
	public List<Batch> findByTrainer(Integer id);
	
	public List<Batch> findByLocation(Integer id);

	public List<Batch> findByLocationAndCurriculum(Integer locId, Integer curId);

	public List<Batch> findByStartDateBetween(LocalDate start, LocalDate end);
}
