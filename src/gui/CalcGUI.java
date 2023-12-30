package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import textProcessor.TextProcessor;

public class CalcGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static JButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
	btnC, btnOpenPar, btnClosePar, btnDiv, btnMult, btnSub, btnAdd, btnEq, btnComma, btnPow;
	
	private static JTextField tf = new JTextField();
	
	public CalcGUI(int width, int height) {
		this.setTitle("Calculator");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setFocusable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		addUI();
		this.setVisible(true);
	}
	
	private void addUI() {
		
		// For the layout
		this.setLayout(new GridLayout(0, 1));
		
		JPanel row2 = new JPanel();
		row2.setLayout(new GridLayout(0, 4));
		
		JPanel row3 = new JPanel();
		row3.setLayout(new GridLayout(0, 4));
		
		JPanel row4 = new JPanel();
		row4.setLayout(new GridLayout(0, 4));
		
		JPanel row5 = new JPanel();
		row5.setLayout(new GridLayout(0, 4));
		
		JPanel row6 = new JPanel();
		row6.setLayout(new GridLayout(0, 4));
		
		this.add(tf);
		this.add(row2);
		this.add(row3);
		this.add(row4);
		this.add(row5);
		this.add(row6);
		
		tf.setEditable(false);
		
		btn0 = new JButton("0");
		btn1 = new JButton("1");
		btn2 = new JButton("2");
		btn3 = new JButton("3");
		btn4 = new JButton("4");
		btn5 = new JButton("5");
		btn6 = new JButton("6");
		btn7 = new JButton("7");
		btn8 = new JButton("8");
		btn9 = new JButton("9");
		
		btnC = new JButton("C");
		btnOpenPar = new JButton("(");
		btnClosePar = new JButton(")");
		btnDiv = new JButton("÷");
		btnMult = new JButton("x");
		btnSub = new JButton("-");
		btnAdd = new JButton("+");
		btnEq = new JButton("=");
		btnComma = new JButton(".");
		btnPow = new JButton("^");
		
		row2.add(btnC);
		row2.add(btnOpenPar);
		row2.add(btnClosePar);
		row2.add(btnDiv);
		
		row3.add(btn7);
		row3.add(btn8);
		row3.add(btn9);
		row3.add(btnMult);
		
		row4.add(btn4);
		row4.add(btn5);
		row4.add(btn6);
		row4.add(btnSub);
		
		row5.add(btn1);
		row5.add(btn2);
		row5.add(btn3);
		row5.add(btnAdd);
		
		row6.add(btn0);
		row6.add(btnComma);
		row6.add(btnPow);
		row6.add(btnEq);
		
		addActions();
	}

	private void addActions() {
		// Add every button to btns
		LinkedList<JButton> btns = new LinkedList<JButton>();
		
		btns.add(btnC);
		btns.add(btnOpenPar);
		btns.add(btnClosePar);
		btns.add(btnDiv);
		
		btns.add(btn7);
		btns.add(btn8);
		btns.add(btn9);
		btns.add(btnMult);
		
		btns.add(btn4);
		btns.add(btn5);
		btns.add(btn6);
		btns.add(btnSub);
		
		btns.add(btn1);
		btns.add(btn2);
		btns.add(btn3);
		btns.add(btnAdd);
		
		btns.add(btn0);
		btns.add(btnComma);
		btns.add(btnPow);
		btns.add(btnEq);
		
		for(int i = 0; i < btns.size(); i++) {
			if(btns.get(i).getText() != "C" && btns.get(i).getText() != "=") {
				// Almost every button prints their text
				btns.get(i).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton source = (JButton) e.getSource();
						tf.setText(tf.getText() + source.getText());
					}
					
				});
			} // to clear the text field
			else if(btns.get(i).getText() == "C") {
				btns.get(i).addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						tf.setText("");
					}
					
				});
			}
		}
		// Add action to button '='
		btns.getLast().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Equals();
				
			}
			
		});
	}

	public String getTFText() {
		return tf.getText();
	}
	
	public void Equals() {
		TextProcessor tp = new TextProcessor();
		try {
			tf.setText(String.valueOf(tp.process(getTFText())));
		} catch(Exception e) {
			tf.setText("Syntax Error");
		}
	}
}
