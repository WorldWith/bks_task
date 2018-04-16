package com.sample.app.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "entity_id")
    @JsonIgnore
    private Long entityId;
	
	@Basic
    @Column(nullable = false, name = "product_id")
    private String productId;
	
    @Basic
    @Column(nullable = false, name = "name")
    private String name;
    
    @Basic
    @Column(nullable = false, name = "max_sum")
    private double maxSum;
    
    @Basic
    @Column(nullable = false, name = "max_term")
    private int maxTerm;
    
    @Basic
    @Column(nullable = false)
    private float rate;
	
	public Long getEntityId() {
		return this.entityId;
	}
	
	public String getProductId() {
		return this.productId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getMaxSum() {
		return this.maxSum;
	}
	
	public int getMaxTerm() {
		return this.maxTerm;
	}
	
	public float getRate() {
		return this.rate;
	}
	
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMaxSum(double maxSum) {
		this.maxSum = maxSum;
	}
	
	public void setMaxTerm(int maxTerm) {
		this.maxTerm = maxTerm;
	}
	
	public void setRate(float rate) {
		this.rate = rate;
	}
	
}
