package com.github.chatmessenger.messenger.infrastructure.database;

import com.github.chatmessenger.messenger.domain.models.Mensagem;
import com.github.chatmessenger.messenger.domain.ports.outbound.ChatDatabasePort;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ChatAdapter implements ChatDatabasePort {

  private List<Mensagem> mensagens = new ArrayList<>();

  @Override
  public void enviarMensagem(Mensagem mensagem) {
    mensagens.add(mensagem);
  }
}
