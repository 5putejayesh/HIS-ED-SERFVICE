package com.jayesh.his.ed.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name ="DC_INCOME")
public class DcIncome {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer incomeId;
	private Long caseNo;
	private Integer empIncome;
	private Integer propertyIncome;
}
