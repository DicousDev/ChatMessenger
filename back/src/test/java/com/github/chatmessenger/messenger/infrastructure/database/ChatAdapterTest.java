package com.github.chatmessenger.messenger.infrastructure.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.github.chatmessenger.messenger.domain.models.Autor;
import com.github.chatmessenger.messenger.domain.models.Mensagem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChatAdapterTest {

  private ChatAdapter chatAdapter;

  @BeforeEach
  public void setup() {
    chatAdapter = new ChatAdapter();
  }

  @Test
  public void deveEnviarMensagem() {
    assertThat(chatAdapter.getTotalMensagens()).isEqualTo(0);
    chatAdapter.enviarMensagem(criaMensagem());
    assertThat(chatAdapter.getTotalMensagens()).isEqualTo(1);
  }

  private Mensagem criaMensagem() {
    Autor autor = Autor.criar("João");
    return Mensagem.criar(autor, "oi teste");
  }
}
