package com.thunderhouse.myipam.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.thunderhouse.myipam.model.Record;

public interface RecordRepository extends JpaRepository<Record, Long> {
	
	 List<Record> findByZoneId(long zoneId);
	 Optional<Record> findByIdAndZoneId(long id, long zoneId);
	
}
