package com.ilp.enity;

import java.util.ArrayList;

public class LoanAccount extends Product {
	
	private double chequeDepositCharge;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		chequeDepositCharge = 0.3;
	}

	public double getChequeDepositCharge() {
		return chequeDepositCharge;
	}

	public void setChequeDepositCharge(double chequeDepositCharge) {
		this.chequeDepositCharge = chequeDepositCharge;
	}
	
	

}
