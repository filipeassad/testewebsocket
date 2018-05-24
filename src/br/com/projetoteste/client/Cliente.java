package br.com.projetoteste.client;

import static br.com.projetoteste.util.JsonUtil.formatMessage;
import java.net.URI;
import java.util.Scanner;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

public class Cliente {
	
	public static final String SERVER = "ws://localhost:8025/ws/chat";

	public static void main(String[] args) throws Exception {
		
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
            session.getBasicRemote().sendText(formatMessage(message, user));
            //session.getBasicRemote().sendObject(mensagem);
        } while (!message.equalsIgnoreCase("quit"));

	}

}
