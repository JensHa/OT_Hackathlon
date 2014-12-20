package de.hackathlon.restgame.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Board extends JFrame {

	private JPanel bt_7;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI_Board frame = new GUI_Board();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GUI_Board(TTTClient client,String boardID , String player) {
		System.out.println("Borad: " + boardID + " Player: "+ player);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 382);
		bt_7 = new JPanel();
		bt_7.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bt_7);
		bt_7.setLayout(null);
		
		JButton bt_0 = new JButton("New button");
		bt_0.setBounds(109, 42, 80, 76);
		bt_7.add(bt_0);
		
		JButton bt_1 = new JButton("New button");
		bt_1.setBounds(201, 42, 80, 76);
		bt_7.add(bt_1);
		
		JButton bt_2 = new JButton("New button");
		bt_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bt_2.setBounds(293, 42, 80, 76);
		bt_7.add(bt_2);
		
		JButton bt_3 = new JButton("New button");
		bt_3.setBounds(109, 129, 80, 76);
		bt_7.add(bt_3);
		
		JButton bt_4 = new JButton("New button");
		bt_4.setBounds(201, 129, 80, 76);
		bt_7.add(bt_4);
		
		JButton bt_5 = new JButton("New button");
		bt_5.setBounds(293, 131, 80, 76);
		bt_7.add(bt_5);
		
		JButton bt_6 = new JButton("New button");
		bt_6.setBounds(109, 218, 80, 76);
		bt_7.add(bt_6);
		
		JButton button_6 = new JButton("New button");
		button_6.setBounds(201, 218, 80, 76);
		bt_7.add(button_6);
		
		JButton bt_8 = new JButton("New button");
		bt_8.setBounds(293, 218, 80, 76);
		bt_7.add(bt_8);
	}

}
