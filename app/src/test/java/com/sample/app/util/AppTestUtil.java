package com.sample.app.util;

import com.sample.app.domain.Product;
import com.sample.app.domain.Rule;
import com.sample.app.web.bean.BorrowerForm;
import com.sample.app.web.bean.RuleForm;

public abstract class AppTestUtil {

	public static Product createProduct(String productId, String productName, double maxSum, int maxTerm, float rate) {
		Product product = new Product();
		product.setMaxSum(maxSum);
		product.setMaxTerm(maxTerm);
		product.setName(productName);
		product.setProductId(productId);
		product.setRate(rate);
		return product;
	}
	
	public static Rule createRule(Product product, String ruleId, boolean noDebtsRule, double salary) {
		Rule rule = new Rule();
		rule.setIsNoDebtsRule(noDebtsRule);
		rule.setProduct(product);
		rule.setRuleId(ruleId);
		rule.setSalary(salary);
		return rule;
	}
	
	public static BorrowerForm createBorrowerForm(double claim, double salary, boolean is_debtor) {
		BorrowerForm form = new BorrowerForm();
		form.setClaim(claim);
		form.setSalary(salary);
		form.setIs_debtor(is_debtor);
		return form;
	}
	
	public static String creatBorrowerFormJsonStr(double claim, double salary, boolean is_debtor) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"claim\":").append(claim)
			.append(",\"salary\":").append(salary)
			.append(",\"is_debtor\":").append(is_debtor).append("}");
		return sb.toString();
	}

	public static RuleForm createRuleForm(double salary, boolean isNoDebtsRule) {
		RuleForm form = new RuleForm();
		form.setSalary(salary);
		form.setIsNoDebtsRule(isNoDebtsRule);
		return form;
	}
	
	public static String createRuleFormJsonStr(double salary, boolean isNoDebtsRule) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"salary\":").append(salary)
			.append(",\"isNoDebtsRule\":").append(isNoDebtsRule).append("}");
		return sb.toString();
	}
	
}
