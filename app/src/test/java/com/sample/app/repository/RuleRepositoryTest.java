package com.sample.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.app.domain.Product;
import com.sample.app.domain.Rule;
import com.sample.app.util.AppTestUtil;
import com.sample.app.web.bean.BorrowerForm;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RuleRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private RuleRepository ruleRepository;
	
	@Test
	public void whenFindByRuleId_thenReturnRule() {
		// given
		Product product = AppTestUtil.createProduct("pq1w2e", "Тестовый продукт", 500000, 2, 12);
		entityManager.persist(product);
		
		Rule given = AppTestUtil.createRule(product, "r-a1s2d", true, 40000);		
		entityManager.persist(given);
		entityManager.flush();
		
		// when
		Rule found = ruleRepository.findByRuleId(given.getRuleId()); 
		
		// then
		assertThat(found.getRuleId()).isEqualTo(given.getRuleId());
		assertThat(found.getProduct().getProductId()).isEqualTo(given.getProduct().getProductId());
	}
	
	@Test
	public void whenFindByProductId_thenReturnRuleList() {
		// given
		Product product = AppTestUtil.createProduct("pq1w2e", "Тестовый продукт", 500000, 2, 12);
		entityManager.persist(product);
		
		Rule givenFirst = AppTestUtil.createRule(product, "r-a1s2d", true, 35000);		
		entityManager.persist(givenFirst);
		
		Rule givenSecond = AppTestUtil.createRule(product, "r-d3s2a", false, 60000);
		entityManager.persist(givenSecond);
		entityManager.flush();
		
		Object[] ruleIdList = new String[] {givenFirst.getRuleId(), givenSecond.getRuleId()};
		
		// when
		List<Rule> foundList = ruleRepository.findListByProductId(product.getProductId());
		
		// then
		assertThat(foundList.size()).isEqualTo(2);
		assertThat(foundList.get(0).getRuleId()).isIn(ruleIdList);
		assertThat(foundList.get(1).getRuleId()).isIn(ruleIdList);
	}
	
	@Test
	public void whenFindApplicableProducts_thenReturnProductSet() {
		// given
		Product product = AppTestUtil.createProduct("pq1w2e", "Тестовый продукт", 500000, 2, 12);
		entityManager.persist(product);
		
		Rule givenFirst = AppTestUtil.createRule(product, "r-a1s2d", true, 65000);		
		entityManager.persist(givenFirst);
		
		Rule givenSecond = AppTestUtil.createRule(product, "r-d3s2a", false, 80000);
		entityManager.persist(givenSecond);
		entityManager.flush();
		
		BorrowerForm form = AppTestUtil.createBorrowerForm(450000, 66000, true);
		
		// when
		Set<Product> foundSet = ruleRepository.getApplicableProducts(form);
		
		// then
		assertThat(foundSet.size()).isGreaterThan(0);
	}

}
