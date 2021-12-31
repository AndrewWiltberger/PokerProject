package MyProject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class MyFrame extends JFrame implements ActionListener{
	JButton button;
	JButton button1;
	JButton button2;
	
	JLabel label;
	JTextField text1;
	JTextField text2;
	

	
	MyFrame() {		
		
		button = new JButton();
		button.setBounds(100, 25, 175, 50);
		button.addActionListener(this);
		button.setText("Select Player 1 Cards");
		button.setFocusable(false);
		
		
		
		button1 = new JButton();
		button1.setBounds(100, 100, 175, 50);
		button1.addActionListener(this);
		button1.setText("Select Player 2 Cards");
		button1.setFocusable(false);
		
		
		button2 = new JButton();
		button2.setBounds(100, 175, 175, 50);
		button2.addActionListener(this);
		button2.setText("Evaluate");
		button2.setFocusable(false);
		
		text1 = new JTextField();
		text1.setPreferredSize(new Dimension(250, 40));
		

		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(250, 40));
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit program on close 
		this.setSize(500, 500); //set x and y dims of frame 
		this.setLayout(new FlowLayout());
		this.add(button);
		this.add(button1);
		this.add(button2);
		this.add(text1);
		this.add(text2);
		this.pack();
		this.setVisible(true);	//make frame visible



		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			
			System.out.println("Player 1 Cards Selected:" + text1.getText());
		}
		if(e.getSource()==button1) {
			System.out.println("Player 2 Cards Selected:" + text2.getText());
		}

		if(e.getSource()==button2) {
			System.out.println("Evaluating...");
			//evaluating
		}

	}
}
