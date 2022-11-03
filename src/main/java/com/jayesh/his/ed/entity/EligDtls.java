package com.jayesh.his.ed.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "ED_ELIG_DTLS")
public class EligDtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eligId;	
	private Long caseNo;
	private String holdersName;			
	private Long mobile;			
	private String email;	
	@Column(length = 1)
	private String gender;			
	private Long ssn;				
	private String planName;		
	private String planStatus;	
	private Double benefitAmt;
	private String denielReason;
	private LocalDate planStartDate;	
	private LocalDate planEndDate;	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	@Column(insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;		
	private String createdBy;		
	private String updatedBy; 	
}
