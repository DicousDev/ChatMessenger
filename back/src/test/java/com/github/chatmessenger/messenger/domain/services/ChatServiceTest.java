package com.github.chatmessenger.messenger.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.github.chatmessenger.messenger.domain.models.Autor;
import com.github.chatmessenger.messenger.domain.models.Mensagem;
import com.github.chatmessenger.messenger.domain.repositories.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChatServiceTest {

  private ChatService chatService;
  private ChatRepository chatRepository;

  @BeforeEach
  public void setup() {
    chatRepository = mock(ChatRepository.class);
    chatService = new ChatService(chatRepository);
  }

  @Test
  public void deveEnviarMensagem() {
    Mensagem mensagem = criaMensagem();
    chatService.enviarMensagem(mensagem);
    verify(chatRepository, times(1)).enviarMensagem(mensagem);
  }

  @Test
  public void deveFalharQuandoTentarEnviarMensagemNull() {
    Exception exception = assertThrows(RuntimeException.class, () -> {
      chatService.enviarMensagem(null);
    });

    assertThat(exception.getMessage()).isEqualTo("Não é possível enviar mensagem null");
  }

  private Mensagem criaMensagem() {
    Autor autor = Autor.criar("João");
    return Mensagem.criar(autor, "oi teste");
  }
}
