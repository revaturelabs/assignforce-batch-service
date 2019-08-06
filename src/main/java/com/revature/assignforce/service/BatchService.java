package com.revature.assignforce.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Batch;

public interface BatchService {
	
	List<Batch> getAll();
	Optional<Batch> findById(int id);
	Batch update(Batch b);
	Batch create(Batch b);
	void delete(int id);
	
	List<Batch> getAllByCurriculum(int curriculumId);
	List<Batch> getAllByTrainer(int trainerId);
	List<Batch> getAllByLocation(int locationId);

	List<Batch> getAllByLocationAndCurriculum(int locationId, int curriculumId);

	List<Batch> getAllBatchesStartingBetween(LocalDate startDate, LocalDate endDate);
	List<Batch> getAllBatchesByTrainerStartingBetween(Integer trainerID, LocalDate startDate, LocalDate endDate);
    List<Batch> getAllBatchesByLocationStartingBetween(Integer locationId, LocalDate startDate, LocalDate endDate);
    List<Batch> getAllBatchesByCurriculumStartingBetween(Integer curriculumId, LocalDate startDate, LocalDate endDate);


}
