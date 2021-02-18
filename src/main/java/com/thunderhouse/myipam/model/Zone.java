package com.thunderhouse.myipam.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;



@Entity
@Table(name = "zones")
public class Zone {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Version
    private Integer version;
	
	//@NotBlank
	//@NotNull
	//@NotEmpty
	//@Pattern(regexp = "[a-zA-Z]")
	private String name = "domain.com";
	
	@NotBlank
	private String mname;
	
	private String rname = "hostmaster.domain.com";
	private long serial = generateTodaySerial();
	private int refresh = 7200;
	private int retry = 3600;
	private int expire = 3600;;
	private int minimum_ttl = 3600;

	@OneToMany(mappedBy = "zone", cascade = {
	    CascadeType.ALL
	 })
	private List<Record> records;
	
	public Zone() {
		
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public int getRefresh() {
		return refresh;
	}

	public void setRefresh(int refresh) {
		this.refresh = refresh;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public int getMinimum_ttl() {
		return minimum_ttl;
	}

	public void setMinimum_ttl(int minimum_ttl) {
		this.minimum_ttl = minimum_ttl;
	}

	public long dateToSerial() {
		return 2021010600;
	}
	
	public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
    
	public long generateTodaySerial() {
		String pattern = "yyyyMMdd00";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		long newSerial = Long.parseLong(date);
		return newSerial;
	}
	
	public long updateSerial(long serial) {
		long todaySerial = generateTodaySerial();
		long newSerial = 0;
		if (serial < todaySerial) {
			newSerial = todaySerial;	
		} else {
			newSerial = serial + 1;
		}
		return newSerial;
	}
	
}
