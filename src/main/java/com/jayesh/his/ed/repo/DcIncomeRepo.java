package com.jayesh.his.ed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayesh.his.ed.entity.DcIncome;

public interface DcIncomeRepo extends JpaRepository<DcIncome, Integer> {
	public DcIncome findByCaseNo(Long caseNo);
}
