package br.com.projetoteste.server;

import java.util.Scanner;

import javax.websocket.DeploymentException;

public class Server {

	public static void main(String[] args) {
		
		org.glassfish.tyrus.server.Server server = new org.glassfish.tyrus.server.Server("localhost",8025,"/ws",ServerEndPoint.class);
		
		try {
			server.start();
			System.out.println("Pressione qualquer tecla para parar o servidor...");
			new Scanner(System.in).nextLine();
		}catch (DeploymentException e) {
			// TODO: handle exception
		}finally {
			server.stop();
		}
		

	}

}
