package de.hackathlon.restgame.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Color;

import javax.swing.JButton;
import javax.ws.rs.core.Response;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;


public class GUI_Lobby extends JFrame {
	//private JTable table;
	private JPanel contentPane;
	private JTable table_1;
	private JScrollPane scrollPane;
	private int lastSelectedIndex=-1;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI_Lobby frame = new GUI_Lobby(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param client 
	 */
	public GUI_Lobby(final TTTClient client) {
		
		Response resp=client.getMethod("Refresh/Users");
		String usersString=resp.readEntity(String.class);
		ArrayList<String> users=new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(usersString,";");
		
		while(st.hasMoreElements()){
			users.add(st.nextToken());
		}
		
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 13, 244, 227);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		table_1.setForeground(Color.BLACK);
		table_1.setBackground(Color.WHITE);
		table_1.setModel(new DefaultTableModel(
			new String[][] {},
			new String[] {
				"User"
			}
		));
		
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (table_1.getSelectedRow() > -1) {
		            // print first column value from selected row
		            lastSelectedIndex=table_1.getSelectedRow();
		        }
		    }
		});

		
		for(String user:users){
		 ((DefaultTableModel) table_1.getModel()).addRow(new String[]{user});
		}
		
		Startup.pool.submit(new Runnable() {

			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
						
						DefaultTableModel model = new DefaultTableModel(
								new String[][] {}, new String[] { "User" });
						Response resp = client.getMethod("Refresh/Users");
						String usersString = resp.readEntity(String.class);
						final ArrayList<String> users = new ArrayList<String>();

						StringTokenizer st = new StringTokenizer(usersString, ";");

						while (st.hasMoreElements()) {
							users.add(st.nextToken());
						}
						for (String user : users) {
							model.addRow(new String[] { user });
						}

						table_1.setModel(model);
						if(lastSelectedIndex>-1&&lastSelectedIndex<=table_1.getRowCount()){
							table_1.setRowSelectionInterval(lastSelectedIndex, lastSelectedIndex);
						}else if(lastSelectedIndex>-1&&lastSelectedIndex>table_1.getRowCount()){
							table_1.setRowSelectionInterval(table_1.getRowCount()-1, table_1.getRowCount()-1);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}

		);
		
		Startup.pool.submit(new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(5000);
						
						String resp=client.getMethod("PreGame/openRequests").readEntity(String.class);
						System.out.println(resp);
						if(!resp.equals("")){
							int result = JOptionPane.showConfirmDialog(null, 
									   "You got an request from "+resp +". Do you want to play?",null, JOptionPane.YES_NO_OPTION);
									if(result == JOptionPane.YES_OPTION) {
										client.postMethod("PreGame/answerToRequest", "yes;"+resp);
									} else{
										client.postMethod("PreGame/answerToRequest", "no;"+resp);
									}
						};
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		
		Startup.pool.submit(new Runnable() {
			
			public void run() {
				while(true){
					try {
						Thread.sleep(5000);
						
						String resp=client.getMethod("PreGame/getAnswersToRequest").readEntity(String.class);
						System.out.println(resp);
						if(!resp.equals("")){
							if(resp.equals("no")){
								JOptionPane.showMessageDialog(new JFrame(),
									    "Player declined",
									    ":(",
									    JOptionPane.ERROR_MESSAGE);
								
							}
						};
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		
		
		Startup.pool.submit(new Runnable() {
			
			public void run() {
				while(true){
						try {
							Thread.sleep(5000);
							String resp=client.getMethod("Game/amIinARunningGame").readEntity(String.class);
							StringTokenizer st = new StringTokenizer(resp,";");
							String yesOrNo=st.nextToken();
							String boardID=st.nextToken();
							if(yesOrNo.equals("yes"))
							{
								
							new GUI_Board(client,boardID).setVisible(true);
							dispose();
							Thread.currentThread().destroy();
							}

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				}
			}
		});
		JButton bt_invite = new JButton("Invite");
		bt_invite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				DefaultTableModel defaultModel = (DefaultTableModel) table_1.getModel();
//		        Vector newRow = new Vector();
//		        newRow.add("Test1337");
//		        defaultModel.addRow(newRow); 
//		        
				//((DefaultTableModel)table_1.getModel()).addRow(rowData);
				client.postMethod("PreGame/inviteUser",table_1.getValueAt(table_1.getSelectedRow(), 0).toString());
				
		      //  System.out.println(table_1.getModel().getValueAt(table_1.getSelectedRow(), 0));
//				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
//				System.out.println(model.getClass());
			}
		});
		bt_invite.setBounds(279, 17, 141, 25);
		contentPane.add(bt_invite);
		

		

		
	}
}
