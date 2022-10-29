package com.jayesh.his.ed.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="DC_EDUCATION")
public class DcEducation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eduId;
	private Long caseNo;
	private String highestQual;
	private Integer gradYear;
	private String universityName;

}
