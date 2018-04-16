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
@Table(name = "api_desc")
public class ApiDesc implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "entity_id")
    @JsonIgnore
    private Long entityId;
    
    @Basic
    @Column(name = "app_name")
    private String appName;
    
    @Basic
    @Column(name = "api_version")
    private String apiVersion;

	public Long getEntityId() {
		return this.entityId;
	}

	public String getAppName() {
		return this.appName;
	}

	public String getApiVersion() {
		return this.apiVersion;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

}
