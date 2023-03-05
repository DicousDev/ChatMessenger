package com.github.chatmessenger.messenger.domain.ports.outbound;

import com.github.chatmessenger.messenger.domain.models.Mensagem;

public interface ChatDatabasePort {
  void enviarMensagem(Mensagem mensagem);
}
