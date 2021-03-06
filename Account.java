package project3;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**********************************************************************
 * Stores bank details for a single account. Information includes 
 * number, owner, opening date, and the balance of the account.
 * @author Adam Muglia & Tyler Miller
 * @version 11-04-2015
 *********************************************************************/
public abstract class Account implements Serializable {
	/** Required to implement Serializable */
	private static final long serialVersionUID = 1L;
	
	/** Unique identifying number for an account */
	private int number;
	
	/** Name of the account owner */
	private String owner;
	
	/** The date that the account was opened */
	private GregorianCalendar dateOpened;
	
	/** The current balance of the account */
	private double balance;
	
	/******************************************************************
	 * Creates an object of type Account that store bank account
	 * information that can be manipulated.
	 * @param num the unique number used to identify an account
	 * @param owner the name of the account owner
	 * @param dateOpened the date that the account was opened
	 * @param balance the balance of the account
	 *****************************************************************/
	public Account (int num, String owner, 
			GregorianCalendar dateOpened, double balance) {
		this.number = num;
		dateOpened.setLenient(false);
		this.owner = owner;
		this.dateOpened = dateOpened;
		this.balance = balance;
	}

	/******************************************************************
	 * Returns the unique number corresponding to an account.
	 * @return the unique number of an account
	 *****************************************************************/
	public int getNumber() {
		return number;
	}

	/******************************************************************
	 * Sets the unique number corresponding to an account.
	 * @param number the unique number being set
	 * @return void
	 *****************************************************************/
	public void setNumber(int number) {
		this.number = number;
	}

	/******************************************************************
	 * Returns the name of the account owner.
	 * @return the owner of the account
	 *****************************************************************/
	public String getOwner() {
		return owner;
	}

	/******************************************************************
	 * Sets the name of the account owner.
	 * @param owner name of the owner being set
	 * @return void
	 *****************************************************************/
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/******************************************************************
	 * Returns the date that the account was opened.
	 * @return the date that the account was opened
	 *****************************************************************/
	public GregorianCalendar getDateOpened() {
		return dateOpened;
	}

	/******************************************************************
	 * Sets the date that the account was opened.
	 * @param dateOpened the date the account was opened
	 * @return void
	 *****************************************************************/
	public void setDateOpened(GregorianCalendar dateOpened) {
		this.dateOpened = dateOpened;
	}

	/******************************************************************
	 * Returns the current balance of the account.
	 * @return the balance of the account
	 *****************************************************************/
	public double getBalance() {
		return balance;
	}

	/******************************************************************
	 * Sets the current balance of the account.
	 * @param balance the balance of the account
	 * @return void
	 *****************************************************************/
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	/******************************************************************
	 * Checks if two accounts are equal via the number associated.
	 * @param acct the account that is being compared
	 * @return true if equal; false if not equal
	 * @throws IllegalArgumentException if Object is not an Account
	 *****************************************************************/
	public boolean equals(Object acct) {
		if (acct instanceof Account) {
			Account temp = (Account) acct;
			if (this.getNumber() == temp.getNumber()) {
				return true;
			}
			return false;
		}
		throw new IllegalArgumentException();
	}
	/******************************************************************
	 * Returns a String representation of a GregorianCalendar.
	 * @param g calendar to be turned into a String
	 * @return date formatted as a String
	 *****************************************************************/
	private String dateToString(GregorianCalendar g) {
		int month = g.get(Calendar.MONTH);
		int day = g.get(Calendar.DAY_OF_MONTH);
		int year = g.get(Calendar.YEAR);
		return "" + (month + 1) + "/" + day + "/" + year;
	}
	
	/******************************************************************
	 * Converts an account into a String that lists all relevant info.
	 * @return the String representation of an account
	 *****************************************************************/
	public String toString() {
		return "" + number + "\n" + owner + "\n" + dateToString(
				dateOpened) + "\n" + balance + "\n";
	}
}
