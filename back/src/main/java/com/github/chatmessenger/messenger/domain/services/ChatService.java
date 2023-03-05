package com.github.chatmessenger.messenger.domain.services;

import com.github.chatmessenger.messenger.domain.models.Mensagem;
import com.github.chatmessenger.messenger.domain.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatRepository chatRepository;

  public void enviarMensagem(Mensagem mensagem) {

    if(mensagem == null) {
      throw new NullPointerException("Não é possível enviar mensagem null");
    }

    chatRepository.enviarMensagem(mensagem);
  }
}
