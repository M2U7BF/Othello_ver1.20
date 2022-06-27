package ui.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame {
    JButton startButton;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    CardLayout layout;
    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    
    public static String title = "othello"; 

    public static void main(String[] args) {
        View frame = new View("go");
        frame.setVisible(true);
    }

    View(String title) {
        setTitle(title);
        setBounds(200, 100, 700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        startButton = new JLabel("クリック");
        startButton = new JButton("START");
        panel1 = new JPanel();
        panel1.setLayout(null);
        startButton.setBounds(300, 500, 100, 50);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel2");
            }
        });
        JLabel label = new JLabel("OTHELLO");
	    label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
	    label.setHorizontalAlignment(JLabel.CENTER);
	    label.setBounds(150, 200, 400, 100);
	    panel1.add(startButton);
	    panel1.add(label);

        panel2 = new JPanel();
        label2 = new JLabel("二枚目");
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel3");
            }
        });
        panel2.add(label2);

        panel3 = new JPanel();
        label3 = new JLabel("三枚目");
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel4");
            }
        });
        panel3.add(label3);
        
        panel4 = new JPanel();
        label4 = new JLabel("4枚目");
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(getContentPane(), "panel1");
            }
        });
        panel4.add(label4);

        layout = new CardLayout();

        Container contentPane = getContentPane();
        contentPane.setLayout(layout);
        contentPane.add(panel1, "panel1");
        contentPane.add(panel2, "panel2");
        contentPane.add(panel3, "panel3");
        contentPane.add(panel4, "panel4");
    }

}


//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//
//public class View extends JFrame implements ActionListener, MouseListener{
//	  JButton button;
//	  public static String topTitle = "titleです";
//
//	  public static void main(String args[]){
//	    View frame = new View(topTitle);
//	    frame.setVisible(true);
//	    Thread.sleep(1000);
//	    frame.change(new )
//	  }
//
//	  View(String title){
//		JPanel p = new JPanel();
//		p.setLayout(null);
//		
//	    setTitle(title);
//	    setBounds(200, 100, 700, 800);
//	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	    button = new JButton("START");
//	    button.addActionListener(this);
//	    button.setBounds(300, 500, 100, 50);
//
//	    JLabel label = new JLabel("OTHELLO");
//	    label.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 50));
//	    label.setHorizontalAlignment(JLabel.CENTER);
//	    label.setBounds(150, 200, 400, 100);
//	    
//	    p.add(button);
//	    p.add(label);
//
//	    Container contentPane = getContentPane();
//	    contentPane.add(p, BorderLayout.CENTER);
//	  }
//
//	  
//	  
//	  public void actionPerformed(ActionEvent e){
////	    JOptionPane.showMessageDialog(this, "Hello");
//	  }
//
//	  public void mouseClicked(MouseEvent e){
//	    button.doClick(500);
//	  }
//
//	  public void mouseEntered(MouseEvent e){}
//	  public void mouseExited(MouseEvent e){}
//	  public void mousePressed(MouseEvent e){}
//	  public void mouseReleased(MouseEvent e){}
//}



