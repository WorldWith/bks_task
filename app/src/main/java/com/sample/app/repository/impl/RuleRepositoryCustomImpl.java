package com.sample.app.repository.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sample.app.domain.Product;
import com.sample.app.repository.RuleRepositoryCustom;
import com.sample.app.web.bean.BorrowerForm;

@Repository
public class RuleRepositoryCustomImpl implements RuleRepositoryCustom {
	
    @PersistenceContext
    EntityManager entityManager;

	@Override
	public Set<Product> getApplicableProducts(BorrowerForm form) {
		StringBuilder sb = new StringBuilder();
		sb.append("select p from Rule r join r.product p where (p.maxSum >= ?1 or p.maxSum = 0) and r.salary <= ?2");
		if (form.getIs_debtor()) {
			sb.append(" and r.isNoDebtsRule = false");
		}
		
		Query query = entityManager.createQuery(sb.toString());
		query.setParameter(1, form.getClaim());
		query.setParameter(2, form.getSalary());
		
		return new HashSet<Product>(query.getResultList());
	}

}
