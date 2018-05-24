package br.com.projetoteste.client;

import java.text.SimpleDateFormat;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import br.com.projetoteste.model.Mensagem;
import br.com.projetoteste.model.MensagemDecoder;
import br.com.projetoteste.model.MensagemEncoder;

@javax.websocket.ClientEndpoint(encoders = MensagemEncoder.class, decoders = MensagemDecoder.class)
public class ClienteEndpoint {
	
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	@OnOpen
    public void onOpen(Session session) {
        System.out.println(String.format("Connection established. session id: %s", session.getId()));
    }
	
	@OnMessage
	public void onMessage(Mensagem mensagem) {
		System.out.println(String.format("[%s:%s] %s", 				
				simpleDateFormat.format(mensagem.getReceived()), mensagem.getSender(), mensagem.getContent()));		
	}

}
