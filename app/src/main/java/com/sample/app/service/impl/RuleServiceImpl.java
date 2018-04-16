package com.sample.app.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.app.domain.Product;
import com.sample.app.domain.Rule;
import com.sample.app.exception.AppException;
import com.sample.app.repository.ProductRepository;
import com.sample.app.repository.RuleRepository;
import com.sample.app.service.RuleService;
import com.sample.app.util.AppUtil;
import com.sample.app.web.bean.BorrowerForm;
import com.sample.app.web.bean.RuleForm;

@Service
@Transactional
public class RuleServiceImpl implements RuleService {
	
	@Autowired
	private RuleRepository ruleRepo;
	@Autowired
	private ProductRepository productRepo;

	private static final String RULE_ID_PREFIX = "r";
	
	@Override
	public List<Rule> getRulesByProduct(String productId) {
		return ruleRepo.findListByProductId(productId);
	}

	@Override
	public void addNewRuleToProduct(String productId, RuleForm ruleForm) throws AppException {
		Product product = productRepo.findByProductId(productId);
		if (product == null) {
			throw new AppException(AppException.PRODUCT_NULL + productId);
		}
		
		Rule newRule = new Rule();
		newRule.setIsNoDebtsRule(ruleForm.getIsNoDebtsRule());
		newRule.setProduct(product);
		newRule.setRuleId(this.createRuleId());
		newRule.setSalary(ruleForm.getSalary());
		ruleRepo.save(newRule);
	}

	@Override
	public void removeRule(String ruleId) {
		Rule rule = ruleRepo.findByRuleId(ruleId);
		if (rule != null) {
			ruleRepo.delete(rule);
		}
	}
	
	@Override
	public Set<Product> getApplicableProducts(BorrowerForm form) {
		return ruleRepo.getApplicableProducts(form);
	}
	
	private String createRuleId() {
		Rule rule;
		String ruleId;
		do {
			ruleId = AppUtil.generateId(RULE_ID_PREFIX);
			rule = ruleRepo.findByRuleId(ruleId);
		} while (rule != null);
		
		return ruleId; 
	}
	
}
