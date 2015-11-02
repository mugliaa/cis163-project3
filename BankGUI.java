package project3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.*;

public class BankGUI extends JFrame {
	private BankModel model;
	
	private JMenuBar menuBar;
	private JMenu menu;
	
	private JPanel lblPanel;
	private JPanel txtPanel;
	
	private JMenuItem loadBinary;
	private JMenuItem saveBinary;
	private JMenuItem loadText;
	private JMenuItem saveText;
	private JMenuItem loadXML;
	private JMenuItem saveXML;
	private JMenuItem quit;
	
	private JButton add;
	private JButton delete;
	private JButton update;
	private JButton clear;
	
	private JRadioButton checking;
	private JRadioButton savings;
	
	private JLabel lblAccNum;
	private JLabel lblAccOwner;
	private JLabel lblDateOpened;
	private JLabel lblAccBalance;
	private JLabel lblMonFee;
	private JLabel lblIntRate;
	private JLabel lblMinBalance;
	
	private JTextField accNum;
	private JTextField accOwner;
	private JTextField dateOpened;
	private JTextField accBalance;
	private JTextField monFee;
	private JTextField intRate;
	private JTextField minBalance;
	
	private ButtonListener ls;
	
	public BankGUI() {
		model = new BankModel();
		
		ls = new ButtonListener();
		
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		
		loadBinary = new JMenuItem("Load From Binary...");
		saveBinary = new JMenuItem("Save As Binary...");
		loadText = new JMenuItem("Load From Text...");
		saveText = new JMenuItem("Save As Text...");
		loadXML = new JMenuItem("Load From XML...");
		saveXML = new JMenuItem("Save As XML...");
		quit = new JMenuItem("Quit");
		
		loadBinary.addActionListener(ls);
		saveBinary.addActionListener(ls);
		loadText.addActionListener(ls);
		saveText.addActionListener(ls);
		loadXML.addActionListener(ls);
		saveXML.addActionListener(ls);
		quit.addActionListener(ls);
		
		menu.add(loadBinary);
		menu.add(saveBinary);
		menu.addSeparator();
		menu.add(loadText);
		menu.add(saveText);
		menu.addSeparator();
		menu.add(loadXML);
		menu.add(saveXML);
		menu.addSeparator();
		menu.add(quit);
		
		add = new JButton("Add");
		delete = new JButton("Delete");
		update = new JButton("Update");
		clear = new JButton("Clear");
		
		add.addActionListener(ls);
		delete.addActionListener(ls);
		update.addActionListener(ls);
		clear.addActionListener(ls);
		
		checking = new JRadioButton("Checking");
		savings = new JRadioButton("Savings");
		
		checking.addActionListener(ls);
		savings.addActionListener(ls);
		
		lblAccNum = new JLabel("Account Number: ");
		lblAccOwner = new JLabel("Account Owner: ");
		lblDateOpened = new JLabel("Date Opened: ");
		lblAccBalance = new JLabel("Account Balance: ");
		lblMonFee = new JLabel("Monthly Fee: ");
		lblIntRate = new JLabel("Interest Rate: ");
		lblMinBalance = new JLabel("Minimum Balance: ");
		
		accNum = new JTextField();
		accOwner = new JTextField();
		dateOpened = new JTextField();
		accBalance = new JTextField();
		monFee = new JTextField();
		intRate = new JTextField();
		minBalance = new JTextField();
		
		menuBar.add(menu);
		
		lblPanel = new JPanel();
		lblPanel.add(lblAccNum);
		lblPanel.add(lblAccOwner);
		lblPanel.add(lblDateOpened);
		lblPanel.add(lblAccBalance);
		lblPanel.add(lblMonFee);
		lblPanel.add(lblIntRate);
		lblPanel.add(lblMinBalance);
		
		txtPanel = new JPanel();
		txtPanel.add(accNum);
		txtPanel.add(accOwner);
		txtPanel.add(dateOpened);
		txtPanel.add(accBalance);
		txtPanel.add(monFee);
		txtPanel.add(intRate);
		txtPanel.add(minBalance);
		
		add(menuBar, BorderLayout.NORTH);
		
		add(lblPanel, BorderLayout.WEST);
		add(txtPanel, BorderLayout.EAST);
	}
	
	public static void main(String[] args) {
		BankGUI frame = new BankGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane();
		
		frame.setSize(600,800);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == quit) {
				System.exit(0);
			}

			if (arg0.getSource() == add) {
				// GregorianCalendar bollocks
				if (checking.isSelected()) {
					model.add(new CheckingAccount(Integer.parseInt(accNum.getText()), 
							accOwner.getText(), new GregorianCalendar(), 
							Double.parseDouble(accBalance.getText()), Double.parseDouble(monFee.getText())));
				}
				
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
