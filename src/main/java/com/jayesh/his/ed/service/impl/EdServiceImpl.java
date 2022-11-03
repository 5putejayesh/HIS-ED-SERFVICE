package com.jayesh.his.ed.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayesh.his.ed.binding.EligibilityResponse;
import com.jayesh.his.ed.entity.CitizenAppEntity;
import com.jayesh.his.ed.entity.CoTriggerEntity;
import com.jayesh.his.ed.entity.DcCase;
import com.jayesh.his.ed.entity.DcChildren;
import com.jayesh.his.ed.entity.DcIncome;
import com.jayesh.his.ed.entity.EligDtls;
import com.jayesh.his.ed.entity.Plan;
import com.jayesh.his.ed.repo.CitizenAppRepo;
import com.jayesh.his.ed.repo.CoTriggerRepo;
import com.jayesh.his.ed.repo.DcCasesRepo;
import com.jayesh.his.ed.repo.DcChildrenRepo;
import com.jayesh.his.ed.repo.DcIncomeRepo;
import com.jayesh.his.ed.repo.EligDtlsRepo;
import com.jayesh.his.ed.repo.PlanRepo;
import com.jayesh.his.ed.service.EdService;

@Service
public class EdServiceImpl implements EdService {

	@Autowired
	private DcCasesRepo casesRepo;
	
	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private CitizenAppRepo appRepo;
	@Autowired
	private EligDtlsRepo eligDtlsRepo;
	@Autowired
	private CoTriggerRepo coTriggerRepo;
	@Autowired
	private DcIncomeRepo incomeRepo;
	
	@Autowired
	private DcChildrenRepo childrenRepo;
	@Override
	public EligibilityResponse determineEligibility(Long caseNo) {

		Optional<DcCase> findbyCaseNo = casesRepo.findByCaseNo(caseNo);
		Integer planId=null;
		Integer appId=null;
		String planName=null;
		
		if(findbyCaseNo.isPresent()) {
			DcCase dcCase = findbyCaseNo.get();
			planId=dcCase.getPlanId();
			appId=dcCase.getAppId();
		}
		
		Optional<Plan> planEntity = planRepo.findById(planId);
		
		if(planEntity.isPresent()) {
			Plan plan = planEntity.get();
			planName = plan.getPlanName();
		}
		
		Optional<CitizenAppEntity> appEntity = appRepo.findById(appId);
		Integer age=0;
		String holdersName=null;
		CitizenAppEntity citizenAppEntity=null;
		
		if(appEntity.isPresent()) {
			citizenAppEntity=appEntity.get();
			LocalDate dob = citizenAppEntity.getDob();
			LocalDate now = LocalDate.now();
			holdersName=citizenAppEntity.getFullName();
			age = Period.between(dob, now).getYears();
		}
		EligibilityResponse eligibilityRespopnse = executePlanConditions(caseNo, planName, age,holdersName);
		
		EligDtls eligDtls=new EligDtls();
		BeanUtils.copyProperties(eligibilityRespopnse, eligDtls);
		eligDtls.setCaseNo(caseNo);
		eligDtls.setHoldersName(citizenAppEntity.getFullName());
		eligDtls.setSsn(citizenAppEntity.getSsn());
		eligDtls.setEmail(citizenAppEntity.getEmail());
		eligDtls.setGender(citizenAppEntity.getGender());
		eligDtls.setMobile(citizenAppEntity.getPhno());
		
		eligDtlsRepo.save(eligDtls);
		
		CoTriggerEntity coTriggerEntity=new CoTriggerEntity();
		coTriggerEntity.setEligId(eligDtls.getEligId());
		coTriggerEntity.setTriggerStatus("Pending");
		
		coTriggerRepo.save(coTriggerEntity);
		
		
		return eligibilityRespopnse;
	}
	
	private EligibilityResponse executePlanConditions(Long caseNo,String planName,Integer age,String holdersName) {
		EligibilityResponse response=new EligibilityResponse();
		response.setPlanName(planName);
		response.setHoldersName(holdersName);
		
		DcIncome income = incomeRepo.findByCaseNo(caseNo);
		Integer empIncome = income.getEmpIncome();
		if("SNAP".equals(planName)) {
			
			if(empIncome <=300) {
				response.setPlanStatus("AP");
			}
			else {
				response.setPlanStatus("DN");
				response.setDenielReason("High Income");
			}
		}
		else if ("CCAP".equals(planName)) {
			List<DcChildren> childs = childrenRepo.findByCaseNo(caseNo);
			boolean childAgeCondition=true;
			boolean childCntCondition=false;
			
			childCntCondition=childs.size() > 0;
			childAgeCondition = childs.stream().anyMatch(c->Period.between(c.getChildDOB(),LocalDate.now()).getYears()>16);
			
			if(empIncome <=300) {
				if(childCntCondition) {
					if(childAgeCondition) {
						response.setPlanStatus("AP");
					}
					else {
						response.setPlanStatus("DN");
						response.setDenielReason("Child Age is Higher");
					}
					
				}else {
					response.setPlanStatus("DN");
					response.setDenielReason("No Child");
				}
			}else {
				response.setPlanStatus("DN");
				response.setDenielReason("High Income");
			}
			
		}
		if(response.getPlanStatus()=="AP") {
			response.setBenefitAmt(350D);
			response.setPlanStartDate(LocalDate.now());
			response.setPlanEndDate(LocalDate.now().plusMonths(6));
		}
		
		return response;
	}

}
