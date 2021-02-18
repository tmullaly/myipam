package com.thunderhouse.myipam.repository;

import com.thunderhouse.myipam.model.Host;

import org.springframework.data.repository.CrudRepository;

public interface HostRepository extends CrudRepository<Host, Long> {
	
}
