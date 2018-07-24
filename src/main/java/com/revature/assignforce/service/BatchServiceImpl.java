package com.revature.assignforce.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.SkillIdHolder;
import com.revature.assignforce.repos.BatchRepository;

@Transactional
@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;
	
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
		return batchRepository.save(b);
	}

	@Override
	public Batch create(Batch b) {
		Set<SkillIdHolder> skills = b.getSkills();
		b.setSkills(new HashSet<SkillIdHolder>());
		batchRepository.save(b);
		b.setSkills(skills);
		batchRepository.flush();
		return batchRepository.save(b);
	}

	@Override
	public void delete(int id) {
		Batch batch = batchRepository.findById(id).get();
		if ( batch == null ) {
			return;
		}
		batch.setSkills(new HashSet<SkillIdHolder>());
		batchRepository.save(batch);
		batchRepository.deleteById(id);
	}

}
