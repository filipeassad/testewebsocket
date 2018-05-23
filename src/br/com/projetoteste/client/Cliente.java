package br.com.projetoteste.client;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Scanner;

import javax.websocket.ClientEndpoint;
import javax.websocket.DeploymentException;
import javax.websocket.EncodeException;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

import br.com.projetoteste.model.Mensagem;

public class Cliente {
	
	public static final String SERVER = "ws://localhost:8025/ws/chat";

	public static void main(String[] args) throws DeploymentException, URISyntaxException, IOException, EncodeException {
		
		ClientManager cliente = ClientManager.createClient();
		String message;
		
		//Conecta no server
		Scanner scanner = new Scanner(System.in);
		System.out.println("<<< Stager Chat >>>");
        System.out.println("Qual é o seu nome ?");
        String user = scanner.nextLine();
        
        Session session = cliente.connectToServer(ClienteEndpoint.class, new URI(SERVER));
        System.out.println("Você está sendo logado como: " + user);
        
        do {
            message = scanner.nextLine();
            Mensagem mensagem = new Mensagem();
            mensagem.setSender(user);
            mensagem.setContent(message);
            mensagem.setReceived(new Date());
            session.getBasicRemote().sendText(message + user);
            //session.getBasicRemote().sendObject(mensagem);
        } while (!message.equalsIgnoreCase("quit"));

	}

}
