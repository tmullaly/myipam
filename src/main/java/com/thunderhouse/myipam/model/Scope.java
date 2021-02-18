package com.thunderhouse.myipam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "scopes")
public class Scope {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Version
    private Integer version;
	
	@NotBlank
	private String range_lower;
	private String range_upper;
	private String default_lease_time;
	private String max_lease_time;
	private String broadcast_address;
	private String routers;
	private String domain_name_servers;
	private String domain_name;
	
	public Scope() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getRange_lower() {
		return range_lower;
	}

	public void setRange_lower(String range_lower) {
		this.range_lower = range_lower;
	}

	public String getRange_upper() {
		return range_upper;
	}

	public void setRange_upper(String range_upper) {
		this.range_upper = range_upper;
	}

	public String getDefault_lease_time() {
		return default_lease_time;
	}

	public void setDefault_lease_time(String default_lease_time) {
		this.default_lease_time = default_lease_time;
	}

	public String getMax_lease_time() {
		return max_lease_time;
	}

	public void setMax_lease_time(String max_lease_time) {
		this.max_lease_time = max_lease_time;
	}

	public String getBroadcast_address() {
		return broadcast_address;
	}

	public void setBroadcast_address(String broadcast_address) {
		this.broadcast_address = broadcast_address;
	}

	public String getRouters() {
		return routers;
	}

	public void setRouters(String routers) {
		this.routers = routers;
	}

	public String getDomain_name_servers() {
		return domain_name_servers;
	}

	public void setDomain_name_servers(String domain_name_servers) {
		this.domain_name_servers = domain_name_servers;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

}
