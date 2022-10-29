package com.jayesh.his.ed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayesh.his.ed.binding.EligibilityResponse;
import com.jayesh.his.ed.service.EdService;

@RestController
public class EdRestController {

	@Autowired
	private EdService edService;
	
	@GetMapping("/eligibility/{caseNo}")
	public ResponseEntity<EligibilityResponse> determineEligibility(Long caseNo){
		EligibilityResponse eligibilityResponse = edService.determineEligibility(caseNo);
		return new ResponseEntity<>(eligibilityResponse,HttpStatus.OK);
	}
	
}
