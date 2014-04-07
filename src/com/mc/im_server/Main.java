package com.mc.im_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main
{
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	
	public static void main(String[] args) throws IOException
	{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(6666);
		System.out.println("server");
		while(true)
		{
			Socket s = ss.accept();
			System.out.println("accept");
			socketList.add(s);
			new Thread(new ServerThread(s)).start();
		}
	}
}
