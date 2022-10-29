package com.jayesh.his.ed.service;

import com.jayesh.his.ed.binding.EligibilityResponse;

public interface EdService {
	
	public EligibilityResponse determineEligibility(Long caseNo);

}
