package com.sample.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.app.domain.Product;
import com.sample.app.repository.ProductRepository;
import com.sample.app.repository.RuleRepository;
import com.sample.app.util.AppTestUtil;
import com.sample.app.web.bean.RuleForm;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleServiceImplTest {
	
	@Autowired
	private RuleService ruleService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RuleRepository ruleRepository;
	
	@Test
	public void whenAddNewRuleToProduct_thenRuleListSizeIncrease() throws Exception {
		// given
		Product product = productRepository.findAll().get(0);
		String productId = product.getProductId();
		
		RuleForm ruleForm = AppTestUtil.createRuleForm(40000, true);
		
		int ruleListSizeBefore = ruleRepository.findListByProductId(productId).size();
		
		// when
		ruleService.addNewRuleToProduct(productId, ruleForm);
		int ruleListSizeAfter = ruleRepository.findListByProductId(productId).size();
		
		//then
		assertThat(ruleListSizeAfter).isEqualTo(ruleListSizeBefore + 1);
	}

}
