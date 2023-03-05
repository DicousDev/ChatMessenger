package com.github.chatmessenger.messenger.application.services;

import com.github.chatmessenger.messenger.domain.models.Mensagem;
import com.github.chatmessenger.messenger.domain.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnviaMensagemUseCase {

  private final ChatService chatServices;

  public void enviarMensagem(Mensagem mensagem) {
    chatServices.enviarMensagem(mensagem);
  }
}
