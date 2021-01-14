package _03_Chat_Application_networking;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatClient {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start() {
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			System.out.println("os");
			is = new ObjectInputStream(connection.getInputStream());
			System.out.println("is");

			os.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("is it working?");	
		while (connection.isConnected()) {
					try {
						System.out.println("yup");
						JOptionPane.showMessageDialog(null, is.readObject());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
			}

	}

	public void sendMessage() {
		try {
			if (os != null) {
			os.writeObject("MESSAGE SENT FROM CLIENT");
			os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
