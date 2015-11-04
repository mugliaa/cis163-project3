package project3;

import java.io.*;
import java.util.*;
import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

/**********************************************************************
 * Provides all the functions for managing multiple bank accounts.
 * Includes functions for adding, updating, deleting, sorting, and 
 * save/loading accounts.
 * @author Adam Muglia & Tyler Miller
 * @version 11-04-2015
 *********************************************************************/
public class BankModel extends AbstractTableModel implements Comparable {
	/** Holds all the accounts to be displayed */
	private ArrayList<Account> accts;
	
	/** Column names for the table */
	String[] columnNames = {"Number", "Owner", "Date Opened", "Balance",
			"Monthly Fee", "Minimum Balance", "Interest Rate"};
	
	/******************************************************************
	 * Instantiates the ArrayList of accounts.
	 *****************************************************************/
	public BankModel() {
		accts = new ArrayList<>();
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalTextPosition(DefaultTableCellRenderer.CENTER);
	}

	/******************************************************************
	 * Sets the column names for the table.
	 * @param col column number being changed
	 * @return column header value
	 *****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}

	/******************************************************************
	 * Determines the number of columns in the table.
	 * @return number of columns
	 *****************************************************************/
	@Override
	public int getColumnCount() {
		return 7;
	}

	/******************************************************************
	 * Determines the number of rows in the table based on the number 
	 * of Accounts in the ArrayList.
	 * @return number of rows
	 *****************************************************************/
	@Override
	public int getRowCount() {
		return accts.size();
	}

	/******************************************************************
	 * Returns the value at the specified index of the table.
	 * @param arg0 column number
	 * @param arg1 row number
	 * @return the value at the index
	 *****************************************************************/
	@Override
	public Object getValueAt(int arg0, int arg1) {
		Account temp = accts.get(arg0);
		String acct = temp.toString();
		String[] values = acct.split("\n");	
		if (values.length <= arg1) {
			return null;
		}
		return values[arg1];
	}

	/******************************************************************
	 * Compares two Objects.
	 * @param o Object to be compared
	 * @return 0
	 *****************************************************************/
	@Override
	public int compareTo(Object o) {
		return 0;
	}

	/******************************************************************
	 * Adds the given account to the ArrayList.
	 * @param acct the account to be added
	 * @return void
	 *****************************************************************/
	public void add(Account acct) {
		accts.add(acct);
		fireTableDataChanged();
	}

	/******************************************************************
	 * Deletes the account at the given index from the ArrayList.
	 * @param index the index of the account being deleted
	 * @return void
	 *****************************************************************/
	public void delete(int index) {
		if (index == -1) {
			return;
		}
		accts.remove(index);
		fireTableDataChanged();
	}

	/******************************************************************
	 * Updates the account at the given index in the ArrayList.
	 * @param index the index of the account being updated
	 * @return void
	 *****************************************************************/
	public void update(int index) {
		if (index == -1) {
			return;
		}
		Account temp = accts.get(index);
		if (temp instanceof CheckingAccount) {
			CheckingAccountDialogBox c = new CheckingAccountDialogBox();
			c.numberField.setText(Integer.toString(temp.getNumber()));
			c.ownerField.setText(temp.getOwner());
			c.dateOpenedField.setText(dateToString(
					temp.getDateOpened()));
			c.balanceField.setText(Double.toString(temp.getBalance()));
			c.monthlyFeeField.setText(Double.toString(((
					CheckingAccount)temp).getMonthlyFee()));
			
			int temp1 = c.showDialog();
			if (temp1 == JOptionPane.OK_OPTION) {
				
				if (c.numberField.getText().equals("") || 
						c.ownerField.getText().equals("") || 
						c.dateOpenedField.getText().equals("") ||
						c.balanceField.getText().equals("") ||
						c.monthlyFeeField.getText().equals("")) {
					return;
				}

				String tempNum = c.numberField.getText();
				int num = Integer.parseInt(tempNum);
				String owner = c.ownerField.getText();
				String tempDateOpened = 
						c.dateOpenedField.getText();
				String[] s = tempDateOpened.split("/");
				int s0 = Integer.parseInt(s[0]);
				int s1 = Integer.parseInt(s[1]);
				int s2 = Integer.parseInt(s[2]);
				GregorianCalendar cal = 
						new GregorianCalendar(s2, s0 - 1, s1);
				String tempBal = c.balanceField.getText();
				double balance = Double.parseDouble(tempBal);
				String tempMonthlyFee = 
						c.monthlyFeeField.getText();
				double monthlyFee = Double.parseDouble(
						tempMonthlyFee);
				
				accts.get(index).setNumber(num);
				accts.get(index).setOwner(owner);
				accts.get(index).setDateOpened(cal);
				accts.get(index).setBalance(balance);
				((CheckingAccount)accts.get(index)).setMonthlyFee(
						monthlyFee);
				
				c.reset();
			}	
		}
		else {
			SavingsAccountDialogBox s = new SavingsAccountDialogBox();
			s.numberField.setText(Integer.toString(temp.getNumber()));
			s.ownerField.setText(temp.getOwner());
			s.dateOpenedField.setText(dateToString(
					temp.getDateOpened()));
			s.balanceField.setText(Double.toString(temp.getBalance()));
			s.minimumBalanceField.setText(Double.toString(((
					SavingsAccount)temp).getMinBalance()));
			s.interestRateField.setText(Double.toString(((
					SavingsAccount)temp).getInterestRate()));
			
			int t = s.showDialog();
			
			if (t == JOptionPane.OK_OPTION) {

				if (s.numberField.getText().equals("") || 
						s.ownerField.getText().equals("") || 
						s.dateOpenedField.getText().equals("") ||
						s.balanceField.getText().equals("") ||
						s.minimumBalanceField.getText().equals("") ||
						s.interestRateField.getText().equals("")) {
					return;
				}
				
				if (Integer.parseInt(
						s.numberField.getText()) < 0 ||
						Double.parseDouble(
						s.balanceField.getText()) < 0 ||
						Double.parseDouble(s.
						minimumBalanceField.getText()) < 0.0 || 
						Double.parseDouble(s.
							interestRateField.getText()) < 0.0) {
					return;
				}

				String tempNum = s.numberField.getText();
				int num = Integer.parseInt(tempNum);
				String owner = s.ownerField.getText();
				String tempDateOpened = 
						s.dateOpenedField.getText();
				String[] e = tempDateOpened.split("/");
				int s0 = Integer.parseInt(e[0]);
				int s1 = Integer.parseInt(e[1]);
				int s2 = Integer.parseInt(e[2]);
				GregorianCalendar c = 
						new GregorianCalendar(s2, s0, s1);
				String tempBal = s.balanceField.getText();
				double balance = Double.parseDouble(tempBal);
				String tempMin = 
						s.minimumBalanceField.getText();
				double minimumBal = Double.parseDouble(tempMin);
				String tempInterest = 
						s.interestRateField.getText();
				double intRate = Double.parseDouble(tempInterest);
				
				accts.get(index).setNumber(num);
				accts.get(index).setOwner(owner);
				accts.get(index).setDateOpened(c);
				accts.get(index).setBalance(balance);
				((SavingsAccount)accts.get(index)).setMinBalance(
						minimumBal);
				((SavingsAccount)accts.get(index)).setInterestRate(
						intRate);
				
				s.reset();
			}
		}
		fireTableDataChanged();
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
	 * Clears the ArrayList of all Accounts.
	 * @return void
	 *****************************************************************/
	public void clear() {
		accts.clear();
		fireTableDataChanged();
	}

	/******************************************************************
	 * Sorts the ArrayList by number.
	 * @return void
	 *****************************************************************/
	public void sortByNumber() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return Integer.compare(x.getNumber(), y.getNumber());
			}
		});
		fireTableDataChanged();
	}

	/******************************************************************
	 * Sorts the ArrayList by name.
	 * @return void
	 *****************************************************************/
	public void sortByName() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return (x.getOwner().compareTo((y.getOwner())));
			}
		});
		fireTableDataChanged();
	}

	/******************************************************************
	 * Sorts the ArrayList by date opened.
	 * @return void
	 *****************************************************************/
	public void sortByDateOpened() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return (x.getDateOpened().compareTo(y.getDateOpened()));
			}
		});
		fireTableDataChanged();
	}

	/******************************************************************
	 * Sorts the ArrayList by balance.
	 * @return void
	 *****************************************************************/
	public void sortByBalance() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return Double.compare(x.getBalance(), y.getBalance());
			}
		});
		fireTableDataChanged();
	}

	/******************************************************************
	 * Loads a text file into the table.
	 * @return void
	 * @throws IOException if error reading the file
	 *****************************************************************/
	public void loadText() throws IOException {
		accts.clear();
		fireTableDataChanged();
		String fileName = JOptionPane.showInputDialog("File Name: ");
		BufferedReader br = new BufferedReader(
				new FileReader(fileName));
		// Holds a single line of data
		String line = br.readLine();
		// Holds a single account's data
		String temp = line;
		// Separated account data
		String[] accountDetails;
		while (line != null) {
			if (line.equals("")) {
				accountDetails = temp.split("\n");
				temp = "";
				if (accountDetails[4].equals("-")) {
					int num = Integer.parseInt(accountDetails[0]);
					String owner = accountDetails[1];
					
					String[] s = accountDetails[2].split("/");
					int s0 = Integer.parseInt(s[0]);
					int s1 = Integer.parseInt(s[1]);
					int s2 = Integer.parseInt(s[2]);
					GregorianCalendar c = 
							new GregorianCalendar(s2, s0, s1);
					double bal = Double.parseDouble(accountDetails[3]);
					double minBal = Double.parseDouble(
							accountDetails[5]);
					double intRate = Double.parseDouble(
							accountDetails[6]);
					
					accts.add(new SavingsAccount(
							num, owner, c, bal, minBal, intRate));
				}
				else {
					int num = Integer.parseInt(accountDetails[0]);
					String owner = accountDetails[1];
					
					String[] s = accountDetails[2].split("/");
					int s0 = Integer.parseInt(s[0]);
					int s1 = Integer.parseInt(s[1]);
					int s2 = Integer.parseInt(s[2]);
					GregorianCalendar c = 
							new GregorianCalendar(s2, s0, s1);
					double bal = Double.parseDouble(accountDetails[3]);
					double monFee = Double.parseDouble(
							accountDetails[4]);
					
					accts.add(new CheckingAccount(
							num, owner, c, bal, monFee));
				}
			}
			line = br.readLine();
			if (temp.equals("")) {
				temp = temp + line;
			}
			else {
				temp = temp + "\n" + line;
			}
		}
		fireTableDataChanged();
	}

	/******************************************************************
	 * Saves a text file from the data in the table.
	 * @return void
	 *****************************************************************/
	public void saveText() {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileName);
			for (Account a : accts) {
				out.write(a.toString() + "\n" + "\n");
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		out.close();
		fireTableDataChanged();
	}

	/******************************************************************
	 * Loads a binary file into the table.
	 * @return void
	 * @throws IOException if error reading the file
	 * @throws ClassNotFoundException if class not found
	 *****************************************************************/
	public void loadBinary() throws IOException, 
	ClassNotFoundException {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		
		FileInputStream f_in = new FileInputStream(fileName);

		ObjectInputStream obj_in = new ObjectInputStream(f_in);

		accts = (ArrayList<Account>) obj_in.readObject();
		fireTableDataChanged();
	}

	/******************************************************************
	 * Saves a binary file from the data in the table.
	 * @return void
	 * @throws IOException if error saving the file
	 *****************************************************************/
	public void saveBinary() throws IOException{
		String fileName = JOptionPane.showInputDialog("File Name: ");
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(accts);
		fireTableDataChanged();
		out.close();
	}

	/******************************************************************
	 * Loads an XML file into the table.
	 * @return void
	 *****************************************************************/
	public void loadXML() {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		fireTableDataChanged();
	}

	/******************************************************************
	 * Saves an XML file from the data in the table.
	 * @return void
	 * @throws ParserConfigurationException if parse error occurs
	 *****************************************************************/
	public void saveXML() throws ParserConfigurationException {
		String fileName = JOptionPane.showInputDialog("File Name: ");

		DocumentBuilderFactory documentFactory = 
				DocumentBuilderFactory.newInstance();	

		DocumentBuilder documentBuilder = 
				documentFactory.newDocumentBuilder();

		// define root elements
		Document document = (Document) documentBuilder.newDocument();
		Element rootElement = (Element) ((org.w3c.dom.Document) 
				document).createElement("account");
		
		// define account type elements
		Element checking = (Element) ((org.w3c.dom.Document)
				document).createElement("Checking");
		((Node) rootElement).appendChild((Node) checking);
		
		// add attributes to checking
		Attr number = ((org.w3c.dom.Document) document)
				.createAttribute("number");
		Attr owner = ((org.w3c.dom.Document) document)
				.createAttribute("owner");

		
		fireTableDataChanged();
	}
}
