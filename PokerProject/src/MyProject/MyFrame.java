package MyProject;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.DimensionUIResource;

public class MyFrame extends JFrame implements ActionListener{
	JButton button;
	JButton button1;
	JButton button2;
	JButton flopButton;
	JButton turnButton;
	JButton riverButton;

    public static final DecimalFormat df = new DecimalFormat("0.00");
	
	JLabel label;
	JTextField text1;
	JTextField text2;
	JTextField textFlop;
	JTextField textTurn;
	JTextField textRiver;


	
	JPanel resultsFrame;
	JTextField results1;
	JTextField results2;
	JTextField ties;

	JTextField iterationsResults;

	ArrayList<JTextField> textList = new ArrayList<JTextField>();


	
	Border borderRed;
	Border borderGreen;
	Border borderBlack;
	
	
	Deck d;
	Player p1;
	Player p2;
	Evaluator evaluator;
	//variables used to hold combos of
	//5 or 2 cars 
	Integer[] cards5 = new Integer[5];
	Integer[] cards2 = new Integer[2];
	//holds cards set on flop turn and river
	Integer[] flop = new Integer[3];
	Integer turn;
	Integer river;
	Boolean isFlop = false;
	Boolean isTurn = false;
	Boolean isRiver = false;
	Boolean isPlayer1 = false;
	Boolean isPlayer2 = false;

	

	

	
	MyFrame() {		
		button = new JButton();
		button.setBounds(100, 25, 175, 50);
		button.addActionListener(this);
		button.setText("Select Player 1 Cards");
		button.setFocusable(false);
		
		borderRed = BorderFactory.createLineBorder(new Color(186, 13, 13), 1);
        borderGreen = BorderFactory.createLineBorder(new Color(2, 171, 75), 1);
        borderBlack = BorderFactory.createLineBorder(Color.BLACK, 1);

		

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
		
		flopButton = new JButton();
		flopButton.setBounds(100, 175, 175, 50);
		flopButton.addActionListener(this);
		flopButton.setText("Set Flop Cards:");
		flopButton.setFocusable(false);
		
		turnButton = new JButton();
		turnButton.setBounds(100, 175, 175, 50);
		turnButton.addActionListener(this);
		turnButton.setText("Set Turn Card:");
		turnButton.setFocusable(false);
		
		riverButton = new JButton();
		riverButton.setBounds(100, 175, 175, 50);
		riverButton.addActionListener(this);
		riverButton.setText("Set River Card:");
		riverButton.setFocusable(false);
		
		textFlop =new JTextField();
		textFlop.setPreferredSize(new Dimension(250, 40));
		textFlop.setBounds(150, 250, 250, 40);
		textList.add(textFlop);
		
		textTurn =new JTextField();
		textTurn.setPreferredSize(new Dimension(250, 40));
		textTurn.setBounds(150, 250, 250, 40);
		textList.add(textTurn);


		textRiver =new JTextField();
		textRiver.setPreferredSize(new Dimension(250, 40));
		textRiver.setBounds(150, 250, 250, 40);
		textList.add(textRiver);

		
				
		text1 = new JTextField();
		text1.setBounds(500, 500, 250, 40);
		textList.add(text1);


		text2 = new JTextField();
		text2.setPreferredSize(new Dimension(250, 40));
		text2.setBounds(150, 250, 250, 40);
		textList.add(text2);

		
		results1 = new JTextField();
		//results1.setBorder(borderBlack);
		results1.setBounds(150, 250, 250, 40);
		textList.add(results1);


		
		results2 = new JTextField();
		//results2.setBorder(borderBlack);
		results2.setBounds(150, 250, 250, 40);
		textList.add(results2);

		ties = new JTextField();
		ties.setBounds(150, 250, 250, 40);
		textList.add(ties);
		
		resultsFrame = new JPanel();
		resultsFrame.setLayout(new GridLayout(2,1));
		resultsFrame.add(results1);
		resultsFrame.add(ties);
		resultsFrame.add(results2);
		

		
        


		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit program on close 
		this.setSize(600, 325); //set x and y dims of frame 
		this.add(button);
		this.add(text1);
		this.add(button1);
		this.add(text2);
		this.add(flopButton);
		this.add(textFlop);
		this.add(turnButton);
		this.add(textTurn);
		this.add(riverButton);
		this.add(textRiver);
		this.add(button2);
		this.add(resultsFrame);
		this.setLayout(new GridLayout(6,2));

		//this.pack();
		this.setVisible(true);	//make frame visible


		d = new Deck();
		p1 = new Player(1);
		p2 = new Player(2);
		evaluator = new Evaluator();
		
		

		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			
			System.out.println("Player 1 Cards Selected:" + text1.getText());
			
			//if the input is wrong turn button red
			if(!p1.selectCardsFromString(d, text1.getText())) {
				text1.setBorder(borderRed);
				System.out.println("RED");
				isPlayer1 = false;

			} else {
				text1.setBorder(borderGreen);
				System.out.println("GREEN");
				isPlayer1 = true;

			}
		}
		else if(e.getSource()==button1) {
			System.out.println("Player 2 Cards Selected:" + text2.getText());
			
			if(!p2.selectCardsFromString(d, text2.getText())) {
				text2.setBorder(borderRed);
				System.out.println("RED");
				isPlayer2 = false;

			} else {
				text2.setBorder(borderGreen);
				System.out.println("GREEN");
				isPlayer2 = true;

			}
		}
		else if(e.getSource()==button2 && isPlayer1 && isPlayer2) {
			clearResults();
			TypeFTR typeFTR = new TypeFTR();
			Double totalRuns;
			Integer[] playerCards = {p1.c1, p1.c2, p2.c1, p2.c2};


			if(isRiver) {
				System.out.println("Calculating equity...");

				Integer[] cards1 = {p1.c1, p1.c2, evaluator.flop[0], evaluator.flop[1], 
									evaluator.flop[2], evaluator.turn, evaluator.river};
				Integer[] cards2 = {p2.c1, p2.c2, evaluator.flop[0], evaluator.flop[1], 
									evaluator.flop[2], evaluator.turn, evaluator.river};
				
				int rank1 = evaluator.getRankFrom7(cards1);
				int rank2 = evaluator.getRankFrom7(cards2);

				if(rank1 == rank2) {
					evaluator.player1Wins = 1;
					evaluator.player2Wins = 1;
				}
				else if(rank1 < rank2) {
					evaluator.player1Wins = 2;
					evaluator.player2Wins = 0;
				}
				else if(rank1 > rank2) {
					evaluator.player1Wins = 0;
					evaluator.player2Wins = 2;
				}
				totalRuns = 2.0;
				evaluator.tracker = 0;

			}
			else if(isTurn) {
				System.out.println("Calculating equity over all rivers...");
				evaluator.generateAllR(d.getCards(), playerCards);
				totalRuns = evaluator.player1Wins + evaluator.player2Wins + evaluator.Ties;
				evaluator.tracker = 0;

			}
			else if(isFlop) {
				System.out.println("Calculating equity over all turns and rivers...");

				//evals all turns and rivers given a flop and 2 hands
				System.out.println("Cards:" + d.getCards().size()+ " Player cards:" + playerCards.length);
				/*
				 * 
				 * always set tracker to 0 after using a generate function
				 */				
				evaluator.generateAllTR(d.getCards(), cards2, 0, d.getCards().size()-1, 0, 2, playerCards);
				totalRuns = evaluator.player1Wins + evaluator.player2Wins + evaluator.Ties;
				evaluator.tracker = 0;
			}
			else {	
			//eval all flop turns and rivers
				System.out.println("Calculating equity over all flops turns and rivers...");
				
				
				evaluator.generateAllCC(d.getCards(), cards5, 0, d.getCards().size()-1, 0, 5, typeFTR, p1.c1, p1.c2, p2.c1, p2.c2);
				totalRuns = evaluator.player1Wins + evaluator.player2Wins + evaluator.Ties;
				evaluator.tracker = 0;
			
			}
			System.out.println("Player 1 wins:" + evaluator.player1Wins);
			System.out.println("Player 2 wins:" + evaluator.player2Wins);
			System.out.println("Total iterations:" + totalRuns);
			System.out.println("Added iterations:" + (evaluator.player1Wins + evaluator.player2Wins + evaluator.Ties));

			//System.out.println("Time taken:" + duration/1000.0 + " seconds");

			double eq1 = evaluator.player1Wins / totalRuns * 100.0;
			double eq2 = evaluator.player2Wins / totalRuns * 100;
			double eqTies = evaluator.Ties / totalRuns * 100;
			
			System.out.println("Player 1 equity:" + eq1);
			System.out.println("Player 2 equity:" + eq2);
			results1.setText("Player 1 Equity:" + df.format(eq1) + "%");
			results2.setText("Player 2 Equity:" + df.format(eq2) + "%");
			ties.setText("Ties:" + df.format(eqTies) + "%");
			evaluator.player1Wins = 0;
			evaluator.player2Wins = 0;
			evaluator.Ties = 0;

			d = new Deck();
			p1 = new Player(1);
			p2 = new Player(2);
			evaluator = new Evaluator();
			for(JTextField t : textList) {
				t.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
			}
			
			isFlop = false;
			isTurn = false;
			isRiver = false;
			isPlayer1 = false;
			isPlayer2 = false;

			//evaluating
		}
		else if(e.getSource()==flopButton) {
			if(!evaluator.setFlop(textFlop.getText(), d)) {
				textFlop.setBorder(borderRed);
				isFlop = false;
			}
			else {
				textFlop.setBorder(borderGreen);
				isFlop = true;
			}
			
		}
		else if(e.getSource()==turnButton && isFlop) {
			if(!evaluator.setTurn(textTurn.getText(), d)) {
				textTurn.setBorder(borderRed);
				isTurn = false;
			}
			else {
				textTurn.setBorder(borderGreen);
				isTurn = true;
			}
		}
		else if(e.getSource()==riverButton && isTurn) {
			if(!evaluator.setRiver(textRiver.getText(), d)) {
				textRiver.setBorder(borderRed);
				isRiver = false;
			}
			else {
				textRiver.setBorder(borderGreen);
				isRiver = true;
			}
		}

	}
	
	public void clearResults() {
		System.out.println("----------------clearing-------------");
		results1.setText(" bro wtf");
		results2.setText(" asdasdasdasdasd");

	}
}
