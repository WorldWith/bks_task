package com.sample.app.repository;

import java.util.Set;

import com.sample.app.domain.Product;
import com.sample.app.web.bean.BorrowerForm;

public interface RuleRepositoryCustom {

	Set<Product> getApplicableProducts(BorrowerForm form);
	
}
