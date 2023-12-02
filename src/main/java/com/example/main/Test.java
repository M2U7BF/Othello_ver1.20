package com.example.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Test extends JFrame implements ActionListener, MouseListener{
	  JButton button;

	  public static void main(String args[]){
	    Test frame = new Test("MyTitle");
	    frame.setVisible(true);
	  }

	  Test(String title){
	    setTitle(title);
	    setBounds(100, 100, 600, 400);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    button = new JButton("OK");
	    button.addActionListener(this);
	    button.setPreferredSize(new Dimension(100, 50));

	    JLabel label = new JLabel("Click");
	    label.setHorizontalAlignment(JLabel.CENTER);
	    label.setBorder(new LineBorder(Color.BLUE, 1, true));
	    label.addMouseListener(this);
	    label.setPreferredSize(new Dimension(100, 50));

	    JPanel p = new JPanel();
	    p.add(button);
	    p.add(label);

	    Container contentPane = getContentPane();
	    contentPane.add(p, BorderLayout.CENTER);
	  }

	  public void actionPerformed(ActionEvent e){
	    JOptionPane.showMessageDialog(this, "Hello");
	  }

	  public void mouseClicked(MouseEvent e){
	    button.doClick(500);
	  }

	  public void mouseEntered(MouseEvent e){}
	  public void mouseExited(MouseEvent e){}
	  public void mousePressed(MouseEvent e){}
	  public void mouseReleased(MouseEvent e){}
}
