package p3;

import java.util.GregorianCalendar;

public class SavingsAccount extends Account {

	private static final long serialVersionUID = 1L;

	private double minBalance;
	private double interestRate;

	public SavingsAccount(int number, String owner,
			GregorianCalendar dateOpened, double balance, double minBalance,
			double interestRate) {
		super(number, owner, dateOpened, balance);
		this.minBalance = minBalance;
		this.interestRate = interestRate;
	}

	public double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public boolean equals(Object acct){
			if(((Account) acct).getNumber() == this.getNumber())
				return true;
			else
				return false;
	
	}

	public String toString() {
		return super.toString() + getMinBalance() + getInterestRate() + "\n";

	}



}
