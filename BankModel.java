package project3;

import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

public class BankModel extends AbstractTableModel implements Comparable {
	private ArrayList<Account> accts;
	String[] columnNames = {"Number", "Owner", "Date Opened", "Balance",
			"Monthly Fee", "Minimum Balance", "Interest Rate"};

	public BankModel() {
		accts = new ArrayList<>();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return accts.size();
	}

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

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	public void add(Account acct) {
		accts.add(acct);
		fireTableDataChanged();
	}

	public void delete(int index) {
		accts.remove(index);
		fireTableDataChanged();
	}

	public void update(int index) {
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
						new GregorianCalendar(s2, s0, s1);
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
	
	private String dateToString(GregorianCalendar g) {
		int day = g.get(Calendar.DAY_OF_MONTH);
		int month = g.get(Calendar.MONTH);
		int year = g.get(Calendar.YEAR);
		return "" + month + "/" + day + "/" + year;
	}

	public void clear() {
		accts.clear();
		fireTableDataChanged();
	}

	public void sortByNumber() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return Integer.compare(x.getNumber(), y.getNumber());
			}
		});
		fireTableDataChanged();
	}



	public void sortByName() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return (x.getOwner().compareTo((y.getOwner())));
			}
		});
		fireTableDataChanged();
	}

	public void sortByDateOpened() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return (x.getDateOpened().compareTo(y.getDateOpened()));
			}
		});
		fireTableDataChanged();
	}

	public void sortByBalance() {
		Collections.sort(accts, new Comparator<Account>() {
			@Override public int compare(Account x, Account y) {
				return Double.compare(x.getBalance(), y.getBalance());
			}
		});
		fireTableDataChanged();
	}

	public void loadText() {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		
	}

	public void saveText() {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileName);
			for (Account a : accts) {
				out.write(a.toString());
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		out.close();
		fireTableDataChanged();
	}

	public void loadBinary() throws IOException, 
	ClassNotFoundException {
		
		String fileName = JOptionPane.showInputDialog("File Name: ");
		
		// Read from disk using FileInputStream
		FileInputStream f_in = new 
				FileInputStream(fileName);

		// Read object using ObjectInputStream
		ObjectInputStream obj_in = 
				new ObjectInputStream (f_in);

		// Read an object
		Object obj = obj_in.readObject();

		System.out.println(obj);	//  this is what holds all the 
									//  account information

		if (obj instanceof Vector)
		{
			// Cast object to a Vector
			Vector vec = (Vector) obj;
			System.out.println(obj);
		}
		fireTableDataChanged();
	}

	public void saveBinary() throws IOException{
		String fileName = JOptionPane.showInputDialog("File Name: ");
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(accts);
		fireTableDataChanged();
		out.close();
	}

	public void loadXML() {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		fireTableDataChanged();
	}

	public void saveXML() {
		String fileName = JOptionPane.showInputDialog("File Name: ");
		fireTableDataChanged();
	}
}
