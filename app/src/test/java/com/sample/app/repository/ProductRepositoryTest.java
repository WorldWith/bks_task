package com.sample.app.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.app.domain.Product;
import com.sample.app.util.AppTestUtil;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void whenFindByProductId_thenReturnProduct() {
		// given
		Product given = AppTestUtil.createProduct("p-q1w2e", "Тестовый продукт", 500000, 3, 12);
		
		entityManager.persist(given);
		entityManager.flush();
		
		// when
		Product found = productRepository.findByProductId(given.getProductId());
		
		// then
		assertThat(found.getProductId()).isEqualTo(given.getProductId());		
	}
	
	
	
}
