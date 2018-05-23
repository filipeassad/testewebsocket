package br.com.projetoteste.model;

import java.io.StringReader;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MensagemDecoder implements Decoder.Text<Mensagem>{

	@Override
	public void destroy() {
	}

	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public Mensagem decode(String textoMensagem) throws DecodeException {
		
		Mensagem mensagem = new Mensagem();
		JsonObject jsonObject = Json.createReader(new StringReader(textoMensagem)).readObject();
		
		mensagem.setContent(jsonObject.getString("message"));
		mensagem.setSender(jsonObject.getString("sender"));
		mensagem.setReceived(new Date());
		
		return mensagem;
	}

	@Override
	public boolean willDecode(String arg0) {
		return false;
	}

}
