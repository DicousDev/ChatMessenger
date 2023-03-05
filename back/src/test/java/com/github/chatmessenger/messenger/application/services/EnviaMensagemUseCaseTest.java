package com.github.chatmessenger.messenger.application.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.github.chatmessenger.messenger.domain.services.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EnviaMensagemUseCaseTest {

  private ChatService chatService;
  private EnviaMensagemUseCase enviaMensagemUseCase;

  @BeforeEach
  public void setup() {
    chatService = mock(ChatService.class);
    enviaMensagemUseCase = new EnviaMensagemUseCase(chatService);
  }

  @Test
  public void deveChamarServices() {
    enviaMensagemUseCase.enviarMensagem(null);
    verify(chatService, times(1)).enviarMensagem(null);
  }
}
