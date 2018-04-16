package com.sample.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.web.bean.ApiResponse;
import com.sample.app.web.bean.BorrowerForm;
import com.sample.app.web.bean.RuleForm;
import com.sample.app.service.ProductService;
import com.sample.app.service.RuleService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private RuleService ruleService;
	
	@GetMapping
	public ApiResponse findAll() {
		ApiResponse res = new ApiResponse();
		try {
        	res.setData(productService.getAllProducts());
        	res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			res.setSuccess(false);
		}
		
		return res;
	}
	
	@GetMapping(value = "/{product_id}/rules")
	public ApiResponse findRulesByProduct(@PathVariable("product_id") String productId) {
		ApiResponse res = new ApiResponse();
		try {
        	res.setData(ruleService.getRulesByProduct(productId));
        	res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			res.setSuccess(false);
		}
		
		return res;
	}
	
	@PostMapping(value = "/{product_id}/rules")
	public ApiResponse addRuleToProduct(@PathVariable("product_id") String productId,
			@RequestBody RuleForm form) {
		ApiResponse res = new ApiResponse();
		try {
			ruleService.addNewRuleToProduct(productId, form);
        	res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			res.setSuccess(false);
		}
		
		return res;
	}
	
	@DeleteMapping(value = "/{product_id}/rules/{rule_id}")
	public ApiResponse removeRuleToProduct(@PathVariable("product_id") String productId,
			@PathVariable("rule_id") String ruleId) {
		ApiResponse res = new ApiResponse();
		try {
        	ruleService.removeRule(ruleId);
        	res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			res.setSuccess(false);
		}
		
		return res;
	}
	
	@PostMapping(value = "/apply")
	public ApiResponse getApplicableProducts(@RequestBody BorrowerForm form) {
		ApiResponse res = new ApiResponse();
		try {
			res.setData(ruleService.getApplicableProducts(form));
			res.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			res.setError(e.getMessage());
			res.setSuccess(false);
		}
		
		return res;
	}
	

}
