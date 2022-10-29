package com.jayesh.his.ed.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayesh.his.ed.entity.DcEducation;

public interface DcEducationRepo extends JpaRepository<DcEducation, Integer> {

	public DcEducation findByCaseNo(Long caseNo);
}
