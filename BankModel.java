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
