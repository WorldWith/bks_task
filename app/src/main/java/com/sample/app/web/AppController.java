package com.sample.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.service.ApiDescService;
import com.sample.app.web.bean.ApiResponse;

@RestController
public class AppController {
	
	@Autowired
	private ApiDescService apiDescService;

    @GetMapping("/")
    public ApiResponse index() {
    	ApiResponse res = new ApiResponse();
        try {
        	res.setData(apiDescService.getApiDesc());
        	res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			res.setSuccess(false);
		}
        
        return res;
    }
	
}
