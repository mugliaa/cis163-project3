package project3;

import java.util.GregorianCalendar;

/**********************************************************************
 * Stores information required for a checking account.
 * @author Adam Muglia & Tyler Miller
 * @version 10-28-2015
 *********************************************************************/
public class CheckingAccount extends Account {
	private static final long serialVersionUID = 1L;
	private double monthlyFee;
	
	/******************************************************************
	 * Creates a checking account that maintains information on the
	 * monthly fee for its ownership.
	 * @param num the unique number used to identify an account
	 * @param owner the name of the account owner
	 * @param dateOpened the date that the account was opened
	 * @param balance the balance of the account
	 * @param monthlyFee the monthly fee of the account
	 *****************************************************************/
	public CheckingAccount (int num, String owner, 
			GregorianCalendar dateOpened, double balance, 
			double monthlyFee) {
		// Calls the constructor for Account
		super(num, owner, dateOpened, monthlyFee);
		this.monthlyFee = monthlyFee;
	}

	/******************************************************************
	 * Returns the monthly fee for the checking account.
	 * @return the monthly fee of the checking account
	 *****************************************************************/
	public double getMonthlyFee() {
		return monthlyFee;
	}

	/******************************************************************
	 * Sets the monthly fee for the checking account.
	 * @param monthlyFee the monthly fee of the checking account
	 *****************************************************************/
	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}
	
	public boolean equals(Object acct) {
		if (acct instanceof CheckingAccount) {
			CheckingAccount temp = (CheckingAccount) acct;
			if (this.getNumber() == temp.getNumber()) {
				return true;
			}
			return false;
		}
		throw new IllegalArgumentException();
	}
	
	public String toString() {
		return super.toString() + getMonthlyFee() + "\n";
	}
}
