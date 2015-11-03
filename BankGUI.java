package project3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

public class BankGUI extends JFrame {
	private BankModel model;
	
//	private JList<Account> accts;
	private JTable accts;
	
//	private JOptionPane addChecking;
//	private JOptionPane addSavings;
	
	private CheckingAccountDialogBox addChecking;
	private SavingsAccountDialogBox addSavings;
	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuSort;
	private JMenu menuOptions;
	
	private JMenuItem addCheckingAccount;
	private JMenuItem addSavingsAccount;
	private JMenuItem delete;
	private JMenuItem update;
	private JMenuItem clear;
	private JMenuItem quit;
	
	private JMenuItem sortByNumber;
	private JMenuItem sortByName;
	private JMenuItem sortByDateOpened;
	private JMenuItem sortByBalance;
	
	private JMenuItem loadBinary;
	private JMenuItem saveBinary;
	private JMenuItem loadText;
	private JMenuItem saveText;
	private JMenuItem loadXML;
	private JMenuItem saveXML;
	
	private ButtonListener ls;
	
	public BankGUI() {
		model = new BankModel();
		
		accts = new JTable(model);
//		accts = new JList<Account>(model);
		
//		addChecking = new JOptionPane();
//		addSavings = new JOptionPane();
		
		addChecking = new CheckingAccountDialogBox();
		addSavings = new SavingsAccountDialogBox();
		
		ls = new ButtonListener();
		
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuSort = new JMenu("Sort");
		menuOptions = new JMenu("Options");
		
		addCheckingAccount = new JMenuItem("Add Checking Account...");
		addSavingsAccount = new JMenuItem("Add Savings Account...");
		delete = new JMenuItem("Delete");
		update = new JMenuItem("Update");
		clear = new JMenuItem("Clear");
		quit = new JMenuItem("Quit");
		
		sortByNumber = new JMenuItem("By Number...");
		sortByName = new JMenuItem("By Name...");
		sortByDateOpened = new JMenuItem("By Date...");
		sortByBalance = new JMenuItem("By Balance...");
		
		loadBinary = new JMenuItem("Load From Binary...");
		saveBinary = new JMenuItem("Save As Binary...");
		loadText = new JMenuItem("Load From Text...");
		saveText = new JMenuItem("Save As Text...");
		loadXML = new JMenuItem("Load From XML...");
		saveXML = new JMenuItem("Save As XML...");
		
		addCheckingAccount.addActionListener(ls);
		addSavingsAccount.addActionListener(ls);
		delete.addActionListener(ls);
		update.addActionListener(ls);
		clear.addActionListener(ls);
		quit.addActionListener(ls);
		
		sortByNumber.addActionListener(ls);
		sortByName.addActionListener(ls);
		sortByDateOpened.addActionListener(ls);
		sortByBalance.addActionListener(ls);
		
		loadBinary.addActionListener(ls);
		saveBinary.addActionListener(ls);
		loadText.addActionListener(ls);
		saveText.addActionListener(ls);
		loadXML.addActionListener(ls);
		saveXML.addActionListener(ls);
		
		menuFile.add(addCheckingAccount);
		menuFile.add(addSavingsAccount);
		menuFile.addSeparator();
		menuFile.add(delete);
		menuFile.add(update);
		menuFile.add(clear);
		menuFile.addSeparator();
		menuFile.add(quit);
		
		menuSort.add(sortByNumber);
		menuSort.add(sortByName);
		menuSort.add(sortByDateOpened);
		menuSort.add(sortByBalance);
		
		menuOptions.add(loadBinary);
		menuOptions.add(saveBinary);
		menuOptions.addSeparator();
		menuOptions.add(loadText);
		menuOptions.add(saveText);
		menuOptions.addSeparator();
		menuOptions.add(loadXML);
		menuOptions.add(saveXML);
		
		menuBar.add(menuFile);
		menuBar.add(menuSort);
		menuBar.add(menuOptions);
		
		add(menuBar, BorderLayout.NORTH);
		add(new JScrollPane(accts), BorderLayout.CENTER);
//		add(accts, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		BankGUI frame = new BankGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane();
		
		frame.setSize(800,500);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == quit) {
				System.exit(0);
			}

			if (arg0.getSource() == addCheckingAccount) {
				int temp = addChecking.showDialog();
				if (temp == JOptionPane.OK_OPTION) {
					String tempNum = addChecking.numberField.getText();
					int num = Integer.parseInt(tempNum);
					String owner = addChecking.ownerField.getText();
					String tempDateOpened = 
							addChecking.dateOpenedField.getText();
					String[] s = tempDateOpened.split("/");
					int s0 = Integer.parseInt(s[0]);
					int s1 = Integer.parseInt(s[1]);
					int s2 = Integer.parseInt(s[2]);
					GregorianCalendar c = 
							new GregorianCalendar(s2, s0, s1);
					String tempBal = addChecking.balanceField.getText();
					double balance = Double.parseDouble(tempBal);
					String tempMonthlyFee = 
							addChecking.monthlyFeeField.getText();
					double monthlyFee = Double.parseDouble(
							tempMonthlyFee);
					model.add(new CheckingAccount(num, owner, c, 
							balance, monthlyFee));
					addChecking.reset();
				}	
			}
			
			if (arg0.getSource() == addSavingsAccount) {
				int temp = addSavings.showDialog();
				if (temp == JOptionPane.OK_OPTION) {
					String tempNum = addSavings.numberField.getText();
					int num = Integer.parseInt(tempNum);
					String owner = addSavings.ownerField.getText();
					String tempDateOpened = 
							addSavings.dateOpenedField.getText();
					String[] s = tempDateOpened.split("/");
					int s0 = Integer.parseInt(s[0]);
					int s1 = Integer.parseInt(s[1]);
					int s2 = Integer.parseInt(s[2]);
					GregorianCalendar c = 
							new GregorianCalendar(s2, s0, s1);
					String tempBal = addSavings.balanceField.getText();
					double balance = Double.parseDouble(tempBal);
					String tempMin = 
							addSavings.minimumBalanceField.getText();
					double minimumBal = Double.parseDouble(tempMin);
					String tempInterest = 
							addSavings.interestRateField.getText();
					double intRate = Double.parseDouble(tempInterest);
					
					model.add(new SavingsAccount(num, owner, c, balance, 
							minimumBal, intRate));
					addSavings.reset();
				}
				
			}

			if (arg0.getSource() == delete) {
				model.delete(accts.getSelectedRow());
			}
			
			if (arg0.getSource() == update) {
				
			}
			
			if (arg0.getSource() == clear) {
				model.clear();
			}
			
			if (arg0.getSource() == sortByNumber) {
				model.sortByNumber();
			}
			
			if (arg0.getSource() == sortByName) {
				model.sortByName();
			}
			
			if (arg0.getSource() == sortByDateOpened) {
				model.sortByDateOpened();
			}
			
			if (arg0.getSource() == sortByBalance) {
				model.sortByBalance();
			}
			
			if (arg0.getSource() == loadBinary) {
				model.loadBinary("accounts");
			}
			
			if (arg0.getSource() == saveBinary) {
				model.saveBinary("accounts");
			}

			if (arg0.getSource() == loadText) {
				model.loadText("accounts");
			}

			if (arg0.getSource() == saveText) {
				model.saveText("accounts");
			}

			if (arg0.getSource() == loadXML) {
				model.loadXML("accounts");
			}

			if (arg0.getSource() == saveXML) {
				model.saveXML("accounts");
			}

		}
	}
}
