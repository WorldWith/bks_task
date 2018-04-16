package com.sample.app.service;

import java.util.List;
import java.util.Set;

import com.sample.app.domain.Product;
import com.sample.app.domain.Rule;
import com.sample.app.exception.AppException;
import com.sample.app.web.bean.BorrowerForm;
import com.sample.app.web.bean.RuleForm;


public interface RuleService {
	
	List<Rule> getRulesByProduct(String productId);
	
	void addNewRuleToProduct(String productId, RuleForm ruleForm) throws AppException;
	
	void removeRule(String ruleId);
	
	Set<Product> getApplicableProducts(BorrowerForm form);

}
