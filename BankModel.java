package project3;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class BankModel extends AbstractListModel {
	private ArrayList<Account> accts;
	
	public BankModel() {
		accts = new ArrayList<>();
	}
	
	public Object getElementAt(int arg0) {
		return accts.get(arg0);
	}
	
	public int getSize() {
		return accts.size();
	}
	
	private Account find(int num) {
		for (int i = 0; i < getSize(); i++) {
			if (accts.get(i).getNumber() == num) {
				return accts.get(i);
			}
		}
		return null;
	}
	
	public void add(Account acct) {
		accts.add(acct);
	}
	
	public void delete(Account acct) {
		for (int i = 0; i < getSize(); i++) {
			if (getElementAt(i).equals(acct)) {
				accts.remove(i);
			}
		}
	}
	
	public void update(Account acct) {
		
	}
	
	public void sortByNumber() {
		
	}
	
	public void sortByName() {
		
	}
	
	public void sortByDateOpened() {
		
	}
	
	public void sortByBalance() {
		
	}
	
	public void loadText(String fileName) {
		
	}
	
	public void saveText(String fileName) {
		try {
			PrintWriter out = new PrintWriter(fileName);
			for (Account a : accts) {
				out.write(a.toString());
			}
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadBinary(String fileName) {
		
	}
	
	public void saveBinary(String fileName) {
		
	}
	
	public void loadXML(String fileName) {
		
	}
	
	public void saveXML(String fileName) {
		
	}
}
