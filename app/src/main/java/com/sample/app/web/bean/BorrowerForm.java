package com.sample.app.web.bean;

public class BorrowerForm {

	private double claim;
	private double salary;
	private boolean is_debtor;

	public double getClaim() {
		return claim;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public boolean getIs_debtor() {
		return is_debtor;
	}
	
	public void setClaim(double claim) {
		this.claim = claim;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setIs_debtor(boolean is_debtor) {
		this.is_debtor = is_debtor;
	}

}
