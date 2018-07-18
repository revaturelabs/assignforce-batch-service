package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer>{

}
