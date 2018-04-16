package com.sample.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sample.app.domain.Rule;

public interface RuleRepository extends JpaRepository<Rule, Long>, RuleRepositoryCustom {
	
	@Query("select r from Rule r join r.product p where p.productId = ?1")
	List<Rule> findListByProductId(String productId);

	@Query("select r from Rule r where r.ruleId = ?1")
	Rule findByRuleId(String ruleId);
	
}
