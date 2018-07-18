package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Batch;
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
		return batchRepository.save(b);
	}

	@Override
	public void delete(int id) {
		batchRepository.deleteById(id);
	}

}
