package br.com.projetoteste.server;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import br.com.projetoteste.model.Mensagem;
import br.com.projetoteste.model.MensagemDecoder;
import br.com.projetoteste.model.MensagemEncoder;

@javax.websocket.server.ServerEndpoint(value="/chat", encoders=MensagemEncoder.class, decoders=MensagemDecoder.class)
public class ServerEndPoint {
	
	static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session){
		System.out.println(String.format("%s entrou na sala do chat.", session.getId()));
		peers.add(session);
	}
	
	@OnMessage
	public void onMessage(Mensagem mensagem, Session session) throws IOException, EncodeException{	
		
		String user = (String) session.getUserProperties().get("user");
        if (user == null) {
            session.getUserProperties().put("user", mensagem.getSender());
        }
        if ("quit".equalsIgnoreCase(mensagem.getContent())) {
            session.close();
        }

        System.out.println(String.format("[%s:%s] %s", session.getId(), mensagem.getReceived(), mensagem.getContent()));

		for(Session peer : peers) {					
			if(!session.getId().equals(peer.getId())) {
				peer.getBasicRemote().sendObject(mensagem);
			}			
		}		
	}
	
	@OnClose
	public void onClose(Session session) throws IOException, EncodeException{		
		System.out.println(String.format("%s saiu da sala.", session.getId()));
		peers.remove(session);
		
		for(Session peer : peers) { //Notifica a saida de um par
			Mensagem mensagem = new Mensagem();
			mensagem.setSender("Server");
			mensagem.setContent(String.format("%s saiu da sala.", (String) session.getUserProperties().get("user")));
			mensagem.setReceived(new Date());
			peer.getBasicRemote().sendObject(mensagem);
		}
		
	}
	

}
