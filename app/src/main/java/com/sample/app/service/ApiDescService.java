package com.sample.app.service;

import com.sample.app.domain.ApiDesc;
import com.sample.app.exception.AppException;

public interface ApiDescService {
	
	ApiDesc getApiDesc() throws AppException;

}
