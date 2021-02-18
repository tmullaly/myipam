package com.thunderhouse.myipam.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "networks")

public class Network {
		
		@Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private long id;
		
		@Version
	    private Integer version;
		
		@NotBlank
		private String network_address;

		private Integer mask_bits;
		
		public Network() {
			
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

		public String getNetwork_address() {
			return network_address;
		}

		public void setNetwork_address(String network_address) {
			this.network_address = network_address;
		}

		public Integer getMask_bits() {
			return mask_bits;
		}

		public void setMask_bits(Integer mask_bits) {
			this.mask_bits = mask_bits;
		}

		
}
