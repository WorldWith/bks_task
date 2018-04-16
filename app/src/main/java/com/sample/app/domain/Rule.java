package com.sample.app.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rules")
public class Rule implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "entity_id")
    @JsonIgnore
    private Long entityId;
	
	@Basic
    @Column(nullable = false, name = "rule_id")
    private String ruleId;
	
	@Basic
    @Column(nullable = false)
    private double salary;
	
	@Basic
    @Column(nullable = false, name = "is_no_debts_rule")
    private boolean isNoDebtsRule;
	
	@ManyToOne(targetEntity=Product.class, optional=false)
    @JoinColumn(name="product_id")
	@JsonIgnore
	private Product product;
	
	public Long getEntityId() {
		return this.entityId;
	}
	
	public String getRuleId() {
		return this.ruleId;
	}
	
	public double getSalary() {
		return this.salary;
	}
	
	public boolean getIsNoDebtsRule() {
		return this.isNoDebtsRule;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setIsNoDebtsRule(boolean isNoDebtsRule) {
		this.isNoDebtsRule = isNoDebtsRule;
	}	
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
