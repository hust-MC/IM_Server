package com.mc.im_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable
{
	Socket socket = null;
	BufferedReader in = null;
	OutputStream out = null;
	DataTransmission data;
	String content;

	public ServerThread(Socket socket) throws IOException
	{
		this.socket = socket;
		data = new DataTransmission(socket);
	}

	@Override
	public void run()
	{
		System.out.println("read");
		while ((content = (String) getObj()) != null)
		{
			System.out.println(content);
			for (Socket s : Main.socketList)
			{
				if (s != socket)
				{
					try
					{
						new DataTransmission(s).send(content);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Object getObj()
	{
		Object obj;
		try
		{
			obj = data.rev();
			return obj;
		} catch (Exception e)
		{

			Main.socketList.remove(socket);
			System.out.println("remove success");
		}
		return null;
	}
}
