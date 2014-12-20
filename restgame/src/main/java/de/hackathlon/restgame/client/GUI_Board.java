package de.hackathlon.restgame.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.ws.rs.core.Response;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GUI_Board extends JFrame {

	private JPanel panel;
	volatile boolean itsmyTurn;

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
	public GUI_Board(final TTTClient client,String boardID , final String player) {
		System.out.println("Borad: " + boardID + " Player: "+ player);
		final String[] content = {"-","-","-","-","-","-","-","-","-"};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 382);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		
		
		
		
		final JButton bt_0 = new JButton("-");
		bt_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 0+";"+sign);
			}
		});
		bt_0.setBounds(109, 42, 80, 76);
		panel.add(bt_0);
		
		final JButton bt_1 = new JButton("-");
		bt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 1+";"+sign);
			}
		});
		bt_1.setBounds(201, 42, 80, 76);
		panel.add(bt_1);
		
		final JButton bt_2 = new JButton("-");
		bt_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 2+";"+sign);
			}
		});
		bt_2.setBounds(293, 42, 80, 76);
		panel.add(bt_2);
		
		final JButton bt_3 = new JButton("-");
		bt_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 3+";"+sign);
			}
		});
		bt_3.setBounds(109, 129, 80, 76);
		panel.add(bt_3);
		
		final JButton bt_4 = new JButton("-");
		bt_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 4+";"+sign);
			}
		});
		bt_4.setBounds(201, 129, 80, 76);
		panel.add(bt_4);
		
		final JButton bt_5 = new JButton("-");
		bt_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 5+";"+sign);
			}
		});
		bt_5.setBounds(293, 131, 80, 76);
		panel.add(bt_5);
		
		final JButton bt_6 = new JButton("-");
		bt_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 6+";"+sign);
			}
		});
		bt_6.setBounds(109, 218, 80, 76);
		panel.add(bt_6);
		
		final JButton bt_7 = new JButton("-");
		bt_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 7+";"+sign);
			}
		});
		bt_7.setBounds(201, 218, 80, 76);
		panel.add(bt_7);
		
		final JButton bt_8 = new JButton("-");
		bt_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sign="O";
				if(player.equals("player1")){
					sign="X";
				}
				client.postMethod("/Game/postAnEntry", 8+";"+sign);
			}
		});
		bt_8.setBounds(293, 218, 80, 76);
		panel.add(bt_8);
		
		
//		JButton[] buttonList;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
//		buttonList[0]=bt_0;
		
		final ArrayList<JButton> buttonList=new ArrayList<JButton>();
		buttonList.add(bt_0);
		buttonList.add(bt_1);
		buttonList.add(bt_2);
		buttonList.add(bt_3);
		buttonList.add(bt_4);
		buttonList.add(bt_5);
		buttonList.add(bt_6);
		buttonList.add(bt_7);
		buttonList.add(bt_8);
		Startup.pool.submit(new Runnable() {
			
			public void run() {
				while(true){
				try {
					Thread.sleep(5000);
					String resp = client.getMethod("Game/getStateOfBoard").readEntity(String.class);
					
				//	System.out.println(resp);
					
					StringTokenizer st = new StringTokenizer(resp,";");
					for(int i=0;i<9;i++){
						content[i]=st.nextToken();
					}
					Boolean turn=new Boolean(st.nextToken());
				
					itsmyTurn=turn;
//					if(turn==true){
//						itsmyTurn=true;
//					}else{
//						itsmyTurn=false;
//					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			}
		});

		Startup.pool.submit(new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
						
						for(int i=0;i<9;i++){
							buttonList.get(i).setText(content[i]);
							if(player.equals("player1")){
								if(itsmyTurn){
											buttonList.get(i).setEnabled(true);

										}else{
											buttonList.get(i).setEnabled(false);
										}
							}else{
								if(itsmyTurn){
											buttonList.get(i).setEnabled(false);

										}else{
											buttonList.get(i).setEnabled(true);
										}
							}

						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
	}
	


}
