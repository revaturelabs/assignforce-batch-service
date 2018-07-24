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
import com.revature.assignforce.repos.SkillRepository;

@Transactional
@Service
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private SkillRepository skillRepository;

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

	@Override
	public Batch create(Batch b) {
		Set<SkillIdHolder> skills = b.getSkills();
		if (skills == null) {
			skills = new HashSet<>();
		}
		for(SkillIdHolder s : skills) {
			skillRepository.save(s);
		}
		b.setSkills(new HashSet<SkillIdHolder>());
		batchRepository.save(b);
		b.setSkills(skills);
		batchRepository.flush();
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

}
