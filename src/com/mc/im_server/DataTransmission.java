package com.mc.im_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

public class DataTransmission
{
	Socket socket;
	ObjectOutputStream out;
	public DataTransmission(Socket socket) throws IOException
	{
		this.socket = socket;
	}
	
	public void send(Object data) throws IOException
	{
		out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject(data);
		out.flush();
	}
	
	public Object rev() throws StreamCorruptedException, IOException, ClassNotFoundException
	{
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		return in.readObject();
	}
}
