package de.hackathlon.restgame.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.ws.rs.client.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GUI_Login extends JFrame {

	private JPanel contentPane;
	private JTextField tf_Input;
	private JLabel lb_Port;
	private JTextField tf_Port;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GUI_Login(final TTTClient client) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 137);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tf_Input = new JTextField();
		tf_Input.setBounds(80, 53, 151, 22);
		contentPane.add(tf_Input);
		tf_Input.setColumns(10);
		tf_Input.setText("0.0.0.0");
		
		JLabel lb_Server = new JLabel("Server-IP");
		lb_Server.setBounds(12, 56, 56, 16);
		contentPane.add(lb_Server);
		
		JButton bt_Connect = new JButton("Connect");
		bt_Connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String ipAdressOfServer=tf_Input.getText();
				int port=Integer.valueOf(tf_Port.getText());
				try {
					client.setTarget(ipAdressOfServer,port);
					new GUI_Lobby(client).setVisible(true);
					client.postMethod("PreGame/Login");
					dispose();
				//	System.out.println(client.getMethod("/testClass/test").readEntity(String.class));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		bt_Connect.setBounds(370, 52, 97, 25);
		contentPane.add(bt_Connect);
		
		lb_Port = new JLabel("Port");
		lb_Port.setBounds(275, 56, 31, 16);
		contentPane.add(lb_Port);
		
		tf_Port = new JTextField();
		tf_Port.setText("8080");
		tf_Port.setBounds(310, 53, 48, 22);
		contentPane.add(tf_Port);
		tf_Port.setColumns(10);
	}

}
