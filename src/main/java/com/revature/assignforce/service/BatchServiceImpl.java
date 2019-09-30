package com.revature.assignforce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.commands.FindCurriculumCommand;
import com.revature.assignforce.commands.FindLocationCommand;
import com.revature.assignforce.commands.FindSkillsCommand;
import com.revature.assignforce.commands.FindTrainerCommand;
import com.revature.assignforce.repos.BatchRepository;
import com.revature.assignforce.repos.SkillRepository;

@Transactional
@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private FindTrainerCommand findTrainerCommand;
	
	@Autowired
	private FindLocationCommand findLocationCommand;
	
	@Autowired
	private FindCurriculumCommand findCurriculumCommand;
	
	@Autowired
	private FindSkillsCommand findSkillsCommand;

	@Override
	public List<Batch> getAll() {
		return batchRepository.findAll();
	}

	@Override
	public Optional<Batch> findById(int id) {
		return batchRepository.findById(id);
	}

	@Override
	public Batch update(Batch b) {
		for(SkillIdHolder s : b.getSkills()) {
			skillRepository.save(s);
		}
		return batchRepository.save(b);
	}
	
	/**
	 * Saves batch to the repository. The method first checks if there are skills
	 * present, if there is not, make a new HashSet. The references are then validated
	 * and finally it is saved.
	 * @param b - Batch to be saved
	 * @return batch created
	 */
	@Override
	public Batch create(Batch b) {
		Set<SkillIdHolder> skills = b.getSkills();
		if (skills == null) {
			skills = new HashSet<>();
			b.setSkills(skills);
		}
		
		// b = validateReferences(b);
		
		for(SkillIdHolder s : skills) {
			skillRepository.save(s);
		}
		
		return batchRepository.save(b);
	}

	@Override
	public void delete(int id) {
		Optional<Batch> batch = batchRepository.findById(id);
		if (!batch.isPresent()) {
			return;
		}
		batch.get().setSkills(new HashSet<SkillIdHolder>());
		batchRepository.save(batch.get());
		batchRepository.deleteById(id);
	}
	@Override
	public List<Batch> getAllByCurriculum(int curriculumId){
		return batchRepository.findByCurriculum(curriculumId);
	}
	@Override
	public List<Batch> getAllByTrainer(int trainerId){
		return batchRepository.findByTrainer(trainerId);
	}
	@Override
	public List<Batch> getAllByLocation(int locationId){
		return batchRepository.findByLocation(locationId);
	}
	
	/**
	 * Checks for referential integrity. The method will first call FindTrainer
	 * Command and check if the trainer exists, then move on to Location and Curriculum
	 * and finally, filters out all the skills that does not exist
	 * @param b Batch to be checked
	 * @return batch after all, if any, changes are made
	 */
	private Batch validateReferences(Batch b) {
		b = findTrainerCommand.findTrainer(b);
		b = findLocationCommand.findLocation(b);
		b = findCurriculumCommand.findCurriculum(b);
		b.setSkills(b.getSkills().stream().filter((skillIdHolder) -> findSkillsCommand.findSkill(skillIdHolder)).collect(Collectors.toSet()));
		return b;
	}

}
