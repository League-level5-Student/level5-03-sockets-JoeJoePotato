package _02_Chat_Application;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import _03_Chat_Application_networking.ChatClient;
import _03_Chat_Application_networking.ChatServer;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

JButton button= new JButton("Send a message");
	
	ChatServer server;
	ChatClient client;
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?");
		if(response == JOptionPane.YES_OPTION){
			server = new ChatServer(80);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				server.sendMessage();
			});
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			client = new ChatClient(ipStr, 80);
			button.addActionListener((e)->{
				client.sendMessage();
			});
			add(button);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}
	}
}
