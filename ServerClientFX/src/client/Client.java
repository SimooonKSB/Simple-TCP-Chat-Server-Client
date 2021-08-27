package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends Socket implements Runnable{
	
	public String message;
	private BufferedReader reader;
	private BufferedWriter writer;

	public Client(String hostname, int port, String message) throws IOException {
		super(hostname, port);
		this.message = message;
		reader = new BufferedReader(new InputStreamReader(getInputStream()));
		writer = new BufferedWriter(new OutputStreamWriter(getOutputStream()));
	} 
	
	@Override
	public void run() {
		while(!isClosed()) {
			try {
				System.out.println(message);
				message = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendMsg(String message) {
		try {
			writer.write(message + "\n");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
