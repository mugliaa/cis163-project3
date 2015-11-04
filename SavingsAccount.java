package project3;

import java.util.GregorianCalendar;

/**********************************************************************
 * Stores information required for a savings account.
 * @author Adam Muglia & Tyler Miller
 * @version 11-04-2015
 *********************************************************************/
public class SavingsAccount extends Account {
	/** Required to implement Serializable */
	private static final long serialVersionUID = 1L;

	/** The minimum balance required for a savings account */
	private double minBalance;
	
	/** The interest rate for a savings account */
	private double interestRate;

	/******************************************************************
	 * Creates a savings account that maintains information on the
	 * minimum balance and interest rate associated.
	 * @param number the unique number used to identify an account
	 * @param owner the name of the account owner
	 * @param dateOpened the date that the account was opened
	 * @param balance the balance of the account
	 * @param minBalance the minimum balance required for the account
	 * @param interestRate the interest rate of the account
	 *****************************************************************/
	public SavingsAccount(int number, String owner,
			GregorianCalendar dateOpened, double balance, 
			double minBalance, double interestRate) {
		super(number, owner, dateOpened, balance);
		this.minBalance = minBalance;
		this.interestRate = interestRate;
	}

	/******************************************************************
	 * Returns the minimum balance for the savings account.
	 * @return the minimum balance of the savings account
	 *****************************************************************/
	public double getMinBalance() {
		return minBalance;
	}

	/******************************************************************
	 * Sets the minimum balance for the savings account.
	 * @param minBalance the minimum balance of the savings account
	 * @return void
	 *****************************************************************/
	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}

	/******************************************************************
	 * Returns the interest rate for the savings account.
	 * @return the interest rate of the savings account
	 *****************************************************************/
	public double getInterestRate() {
		return interestRate;
	}

	/******************************************************************
	 * Sets the interest rate for the savings account.
	 * @param interestRate the interest rate of the savings account
	 * @return void
	 *****************************************************************/
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	/******************************************************************
	 * Checks if two savings accounts are equal via the number 
	 * associated.
	 * @param acct the account that is being compared
	 * @return true if equal; false if not equal
	 *****************************************************************/
	public boolean equals(Object acct){
			if(((Account) acct).getNumber() == this.getNumber())
				return true;
			else
				return false;
	}

	/******************************************************************
	 * Converts a savings account into a String that lists all info.
	 * @return the String representation of a savings account
	 *****************************************************************/
	public String toString() {
		return super.toString() + "-" + "\n" + getMinBalance() + 
				"\n" + getInterestRate();
	}
}
