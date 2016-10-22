package org.thothlab.devilsvault.CustomerModel;



public class CreditAccount extends BankAccount {
	
	private int Id;
	private int interset;
	private int credit_card_no;
	
	private double availBalance;
	private double lastBillAmount;
	private int dueDateTimestamp;
	private float apr;
	
	public int getInterset() {
		return interset;
	}

	public void setInterset(int interset) {
		this.interset = interset;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCredit_card_no() {
		return credit_card_no;
	}

	public void setCredit_card_no(int credit_card_no) {
		this.credit_card_no = credit_card_no;
	}


	
	public CreditAccount() {
		this.setAccountType(AccountType.CREDIT);
	}
	
	public double getAvailBalance() {
		return availBalance;
	}
	
	public void setAvailBalance(double availBalance) {
		this.availBalance = availBalance;
	}
	
	public double getLastBillAmount() {
		return lastBillAmount;
	}
	
	public void setLastBillAmount(double lastBillAmount) {
		this.lastBillAmount = lastBillAmount;
	}
	
	public int getDueDateTimestamp() {
		return dueDateTimestamp;
	}
	
	public void setDueDateTimestamp(int dueDateTimestamp) {
		this.dueDateTimestamp = dueDateTimestamp;
	}
	
	public float getApr() {
		return apr;
	}
	
	public void setApr(float apr) {
		this.apr = apr;
	}
	
	

}
