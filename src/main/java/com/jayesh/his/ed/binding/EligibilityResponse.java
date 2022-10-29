package com.jayesh.his.ed.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EligibilityResponse {

	private String holdersName;	
	private String planName;		
	private String planStatus;	
	private Double benefitAmt;
	private String denielReason;
	private LocalDate planStartDate;	
	private LocalDate planEndDate;
}
