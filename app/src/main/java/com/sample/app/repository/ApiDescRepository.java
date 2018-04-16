package com.sample.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.app.domain.ApiDesc;


public interface ApiDescRepository extends JpaRepository<ApiDesc, Long> {
	
	List<ApiDesc> findAll();
	
}
