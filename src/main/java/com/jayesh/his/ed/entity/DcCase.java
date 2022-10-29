package com.jayesh.his.ed.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="DC_CASES")
public class DcCase {

	@Id
	private Integer caseId;
	private Long caseNo;
	private Integer appId;
	private Integer planId;
	
	
}
