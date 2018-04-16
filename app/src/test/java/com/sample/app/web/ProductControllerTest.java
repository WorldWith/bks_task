package com.sample.app.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sample.app.domain.Product;
import com.sample.app.domain.Rule;
import com.sample.app.exception.AppException;
import com.sample.app.repository.ProductRepository;
import com.sample.app.repository.RuleRepository;
import com.sample.app.util.AppTestUtil;
import com.sample.app.web.bean.BorrowerForm;
import net.minidev.json.JSONArray;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RuleRepository ruleRepository;
	
	private static final String INVALID_PRODUCT_ID = "qwerty12345";
	
	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void whenFindAllProducts_thenReturnProductList() throws Exception {
		int productListSize = productRepository.findAll().size();
		
		this.mvc.perform(get("/products/"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			.andExpect(jsonPath("$.success", is(true)))
			.andExpect(jsonPath("$.data", instanceOf(JSONArray.class)))
			.andExpect(jsonPath("$.data.length()", is(productListSize)));
	}
	
	@Test
	public void whenFindRulesByProduct_thenReturnRuleList() throws Exception {
		Product product = productRepository.findAll().get(0);
		String productId = product.getProductId();
		
		int rulesListSize = ruleRepository.findListByProductId(productId).size();
		
		StringBuilder sb = new StringBuilder();
		sb.append("/products/").append(productId).append("/rules");
		
		this.mvc.perform(get(sb.toString()))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			.andExpect(jsonPath("$.success", is(true)))
			.andExpect(jsonPath("$.data", instanceOf(JSONArray.class)))
			.andExpect(jsonPath("$.data.length()", is(rulesListSize)));
	}
	
	@Test
	public void whenAddNewRuleToProduct_thenReturnSuccess() throws Exception {
		Product product = productRepository.findAll().get(0);
		String productId = product.getProductId();

		String content = AppTestUtil.createRuleFormJsonStr(65000, false);
		
		StringBuilder sb = new StringBuilder();
		sb.append("/products/").append(productId).append("/rules");
		
		this.mvc.perform(post(sb.toString())
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.content(content)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			.andExpect(jsonPath("$.success", is(true)));
	}
	
	@Test
	public void whenNotAddNewRulesToProduct_thenReturnError() throws Exception {
		String content = AppTestUtil.createRuleFormJsonStr(65000, false);
		
		StringBuilder sb = new StringBuilder();
		sb.append("/products/").append(INVALID_PRODUCT_ID).append("/rules");
		
		this.mvc.perform(post(sb.toString())
						.accept(MediaType.APPLICATION_JSON_UTF8)
						.content(content)
						.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			.andExpect(jsonPath("$.success", is(false)))
			.andExpect(jsonPath("$.error", is(AppException.PRODUCT_NULL + INVALID_PRODUCT_ID)));
	}
	
	@Test
	public void whenDeleteRulesToProduct_thenReturnSuccess() throws Exception {
		Product product = productRepository.findAll().get(0);
		String productId = product.getProductId();

		Rule rule = ruleRepository.findListByProductId(productId).get(0);
		String ruleId = rule.getRuleId();
		
		StringBuilder sb = new StringBuilder();
		sb.append("/products/").append(productId).append("/rules/").append(ruleId);
		
		this.mvc.perform(delete(sb.toString()))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			.andExpect(jsonPath("$.success", is(true)));
	}
	
	@Test
	public void whenFindApplicableProducts_thenReturnProductList() throws Exception {
		BorrowerForm form = AppTestUtil.createBorrowerForm(450000, 66000, true);
		int productListSize = ruleRepository.getApplicableProducts(form).size();
		
		String content = AppTestUtil.creatBorrowerFormJsonStr(450000, 66000, true);
		
		this.mvc.perform(post("/products/apply")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(content)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json;charset=UTF-8"))
			.andDo(print())
			.andExpect(jsonPath("$.success", is(true)))
			.andExpect(jsonPath("$.data", instanceOf(JSONArray.class)))
			.andExpect(jsonPath("$.data.length()", is(productListSize)));
	}
	
}
