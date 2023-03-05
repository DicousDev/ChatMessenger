package com.github.chatmessenger.messenger.domain.repositories;

import com.github.chatmessenger.messenger.domain.models.Mensagem;
import com.github.chatmessenger.messenger.domain.ports.outbound.ChatDatabasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRepository {

  private final ChatDatabasePort port;

  public void enviarMensagem(Mensagem mensagem) {
    port.enviarMensagem(mensagem);
  }
}
