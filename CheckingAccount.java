package project3;

import java.util.GregorianCalendar;

/**********************************************************************
 * Stores information required for a checking account.
 * @author Adam Muglia & Tyler Miller
 * @version 11-04-2015
 *********************************************************************/
public class CheckingAccount extends Account {
	/** Required to implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/** The monthly fee associated with a checking account */
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
		super(num, owner, dateOpened, balance);
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
	 * @return void
	 *****************************************************************/
	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}
	
	/******************************************************************
	 * Checks if two checking accounts are equal via the number 
	 * associated.
	 * @param acct the account that is being compared
	 * @return true if equal; false if not equal
	 *****************************************************************/
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
	
	/******************************************************************
	 * Converts a checking account into a String that lists all info.
	 * @return the String representation of a checking account
	 *****************************************************************/
	public String toString() {
		return super.toString() + getMonthlyFee();
	}
}
