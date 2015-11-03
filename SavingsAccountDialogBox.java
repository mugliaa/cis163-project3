package project3;

import java.awt.GridLayout;

import javax.swing.*;

public class SavingsAccountDialogBox {
	public JTextField numberField;
	public JTextField ownerField;
	public JTextField dateOpenedField;
	public JTextField balanceField;
	public JTextField minimumBalanceField;
	public JTextField interestRateField;
	
	private JPanel myPanel;
	
	public SavingsAccountDialogBox() {
		numberField = new JTextField(15);
		ownerField = new JTextField(15);
		dateOpenedField = new JTextField(15);
		balanceField = new JTextField(15);
		minimumBalanceField = new JTextField(15);
		interestRateField = new JTextField(15);
		
		myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(6,1));
		myPanel.add(new JLabel("Number: "));
		myPanel.add(numberField);
		myPanel.add(new JLabel("Owner: "));
		myPanel.add(ownerField);
		myPanel.add(new JLabel("Date Opened: "));
		myPanel.add(dateOpenedField);
		myPanel.add(new JLabel("Balance: "));
		myPanel.add(balanceField);
		myPanel.add(new JLabel("Minimum Balance: "));
		myPanel.add(minimumBalanceField);
		myPanel.add(new JLabel("Interest Rate: "));
		myPanel.add(interestRateField);
	}
	
	public int showDialog() {
		return JOptionPane.showConfirmDialog(null, myPanel, 
				"Add Savings Account", 
				JOptionPane.OK_CANCEL_OPTION);
	}
}
