package com.sample.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.domain.ApiDesc;
import com.sample.app.exception.AppException;
import com.sample.app.repository.ApiDescRepository;
import com.sample.app.service.ApiDescService;

@Service
@Transactional(readOnly = true)
public class ApiDescServiceImpl implements ApiDescService {
	
	@Autowired
	private ApiDescRepository apiDescRepo;

	@Override
	public ApiDesc getApiDesc() throws AppException {
		List<ApiDesc> apiDescList = apiDescRepo.findAll();
		if (apiDescList.isEmpty()) {
			throw new AppException(AppException.API_DESC_NULL);
		}
		return apiDescList.get(0);
	}

	
	
}
