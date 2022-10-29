package com.jayesh.his.ed.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "DC_CHILDRENS")
public class DcChildren {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer childId;
	private Long caseNo;
	private LocalDate childDOB;
	private Integer childSSN;

}
