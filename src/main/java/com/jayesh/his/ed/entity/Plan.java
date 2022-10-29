package com.jayesh.his.ed.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="PLAN_MASTER")
public class Plan {
	
	@Id
	@GeneratedValue
	@Column(name="PLAN_ID")
	private Integer planId;
	
	@Column(name="PLAN_NAME")
	private String planName;
	
	@Column(name="PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name="PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name="PLAN_CATEGORY_ID")
	private Integer planCatId;
	
	@Column(name="ACTIVE_SW",length = 1)
	private String active_sw;
	
	@Column(name="CREATE_DATE",updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	@Column(name="UPDATE_DATE",insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	@Column(name="CREATED_BY",updatable = false)
	private String createdBy;
	
	@Column(name="UPDATED_BY",insertable=false)
	private String updatedBy;

		

}
