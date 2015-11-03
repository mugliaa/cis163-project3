package project3;

import java.awt.*;

import javax.swing.*;

public class CheckingAccountDialogBox {
	public JTextField numberField;
	public JTextField ownerField;
	public JTextField dateOpenedField;
	public JTextField balanceField;
	public JTextField monthlyFeeField;
	
	private JPanel myPanel;
	
	public CheckingAccountDialogBox() {
		numberField = new JTextField(15);
		ownerField = new JTextField(15);
		dateOpenedField = new JTextField(15);
		balanceField = new JTextField(15);
		monthlyFeeField = new JTextField(15);
		
		myPanel = new JPanel();
		
		myPanel.setLayout(new GridLayout(5,1));
		myPanel.add(new JLabel("Number: "));
		myPanel.add(numberField);
		myPanel.add(new JLabel("Owner: "));
		myPanel.add(ownerField);
		myPanel.add(new JLabel("Date Opened: "));
		myPanel.add(dateOpenedField);
		myPanel.add(new JLabel("Balance: "));
		myPanel.add(balanceField);
		myPanel.add(new JLabel("Monthly Fee: "));
		myPanel.add(monthlyFeeField);
	}
	
	public int showDialog() {
		return JOptionPane.showConfirmDialog(null, myPanel, 
				"Add Checking Account", 
				JOptionPane.OK_CANCEL_OPTION);
	}
	
	public void reset() {
		numberField.setText("");
		ownerField.setText("");
		dateOpenedField.setText("");
		balanceField.setText("");
		monthlyFeeField.setText("");
	}
}
