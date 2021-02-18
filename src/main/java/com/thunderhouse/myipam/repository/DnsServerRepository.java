package com.thunderhouse.myipam.repository;

import com.thunderhouse.myipam.model.DnsServer;

import org.springframework.data.repository.CrudRepository;

public interface DnsServerRepository extends CrudRepository<DnsServer, Long> {
	
}
