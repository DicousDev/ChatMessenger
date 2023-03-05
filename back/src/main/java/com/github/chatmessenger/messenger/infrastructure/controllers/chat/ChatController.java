package com.github.chatmessenger.messenger.infrastructure.controllers.chat;

import com.github.chatmessenger.messenger.application.services.EnviaMensagemUseCase;
import com.github.chatmessenger.messenger.infrastructure.controllers.chat.inputs.EnviaMensagemInput;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

  private final EnviaMensagemUseCase enviaMensagemUseCase;
  private SimpMessagingTemplate template;

  @MessageMapping("/mensagem/public")
  public void enviaMensagem(@Payload EnviaMensagemInput input) {
    enviaMensagemUseCase.enviarMensagem(input.getMensagem());
    template.convertAndSend("/chat/public", input.getMensagem());
  }
}