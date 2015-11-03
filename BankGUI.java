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
	
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenu menuOptions;
	
	private JMenuItem addCheckingAccount;
	private JMenuItem addSavingsAccount;
	private JMenuItem delete;
	private JMenuItem update;
	private JMenuItem clear;
	private JMenuItem quit;
	
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
		
		ls = new ButtonListener();
		
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuOptions = new JMenu("Options");
		
		addCheckingAccount = new JMenuItem("Add Checking Account...");
		addSavingsAccount = new JMenuItem("Add Savings Account...");
		delete = new JMenuItem("Delete");
		update = new JMenuItem("Update");
		clear = new JMenuItem("Clear");
		quit = new JMenuItem("Quit");
		
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
		
		menuOptions.add(loadBinary);
		menuOptions.add(saveBinary);
		menuOptions.addSeparator();
		menuOptions.add(loadText);
		menuOptions.add(saveText);
		menuOptions.addSeparator();
		menuOptions.add(loadXML);
		menuOptions.add(saveXML);
		
		menuBar.add(menuFile);
		menuBar.add(menuOptions);
		
		add(menuBar, BorderLayout.NORTH);
		add(new JScrollPane(accts), BorderLayout.CENTER);
//		add(accts, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		BankGUI frame = new BankGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane();
		
		frame.setSize(800,800);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == quit) {
				System.exit(0);
			}

			if (arg0.getSource() == addCheckingAccount) {
				
			}
			
			if (arg0.getSource() == addSavingsAccount) {
				
			}

			if (arg0.getSource() == delete) {
				
			}
			
			if (arg0.getSource() == update) {

			}
			
			if (arg0.getSource() == clear) {
				
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
