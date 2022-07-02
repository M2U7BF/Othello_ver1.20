package ui.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PreparationView {
	public JLabel orderLabel;
	public JButton orderButton;
	public JButton startButton;
	
	PreparationView(JPanel panel2){
	  panel2.setLayout(null);
	  orderLabel = new JLabel();
	  orderLabel.setBounds(200, 300, 400, 50);
	  orderLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
	  orderButton = new JButton("順番を決める");
	  orderButton.setBounds(300, 500, 100, 50);
	  startButton = new JButton("ゲームスタート");
	  startButton.setBounds(400, 500, 150, 50);
	  startButton.setVisible(false);
	  orderLabel.setVisible(false);
	  
	  panel2.add(orderLabel);
	  panel2.add(startButton);
	  panel2.add(orderButton);
	}
	
	public void orderDecided() {
		orderLabel.setVisible(true);
		startButton.setVisible(true);
		orderButton.setVisible(false);
	}
}
