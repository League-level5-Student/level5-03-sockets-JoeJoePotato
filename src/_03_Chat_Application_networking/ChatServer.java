package _03_Chat_Application_networking;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ChatServer {
	private int port;

	private ServerSocket server;
	private Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatServer(int port) {
		this.port = port;
	}

	public void start() {
		try {
			server = new ServerSocket(port, 100);

			connection = server.accept();
			System.out.println("it worked at least a little");
			os = new ObjectOutputStream(connection.getOutputStream());

			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			while (connection.isConnected()) {
				try {
					JOptionPane.showMessageDialog(null, is.readObject());

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}

	public void sendMessage() {
		try {
			System.out.println("lol");
				os.writeObject("MESSAGE SENT FROM SERVER");
				os.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
