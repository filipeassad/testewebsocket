package br.com.projetoteste.model;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MensagemEncoder implements Encoder.Text<Mensagem> {

	@Override
	public void destroy() {		
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public String encode(Mensagem mensagem) throws EncodeException {
		return  Json.createObjectBuilder()
				.add("message", mensagem.getContent())
				.add("sender", mensagem.getSender())
				.add("received", "")
				.build().toString();
	}	

}
