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
//		accts.add(new CheckingAccount(122, "Adam Muglia", 
//				new GregorianCalendar (5,5,2012), 155.50, 
//				12.50));
//		accts.add(new SavingsAccount(211, "Tyler Miller", 
//				new GregorianCalendar (5,5,2012), 52.22, 
//				12.50, 2.7));
	}

	//	private Account find(int num) {
	//		for (int i = 0; i < getSize(); i++) {
	//			if (accts.get(i).getNumber() == num) {
	//				return accts.get(i);
	//			}
	//		}
	//		return null;
	//	}

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
		// TODO Auto-generated method stub
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

	public void update(Account acct) {
		fireTableDataChanged();
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

	public void loadText(String fileName) {

	}

	public void saveText(String fileName) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileName);
			for (Account a : accts) {
				out.write(a.toString());
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
		fireTableDataChanged();
	}

	public void loadBinary(String fileName) {
		fireTableDataChanged();
	}

	public void saveBinary(String fileName) {
		fireTableDataChanged();
	}

	public void loadXML(String fileName) {
		fireTableDataChanged();
	}

	public void saveXML(String fileName) {
		fireTableDataChanged();
	}
}
