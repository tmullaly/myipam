package com.thunderhouse.myipam.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "records")
public class Record {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name can not be blank")
	//@Pattern(regexp = "[a-zA-Z]")
	private String name;
	
	@NotBlank(message = "Type can not be blank")
	private String type = "A";
	
	private String class_code = "IN";
	
	@NotNull(message = "TTL can not be blank")
	@Min(300)
	private int ttl = 600;
			
	@NotBlank(message = "Resource Data can not be blank")
	@Pattern(regexp = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$", message = "Not an IPv4 Address")
	private String rdata;
	
	@ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
		
	public Record() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
    
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClass_code() {
		return class_code;
	}

	public void setClass_code(String class_code) {
		this.class_code = class_code;
	}

	public int getTtl() {
		return ttl;
	}

	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public String getRdata() {
		return rdata;
	}

	public void setRdata(String rdata) {
		this.rdata = rdata;
	}
	
    public Zone getZone() {
    	return this.zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
	
	
}
