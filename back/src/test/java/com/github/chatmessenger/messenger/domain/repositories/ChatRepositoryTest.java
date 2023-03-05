package com.github.chatmessenger.messenger.domain.repositories;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import com.github.chatmessenger.messenger.domain.ports.outbound.ChatDatabasePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChatRepositoryTest {

  private ChatDatabasePort port;
  private ChatRepository chatRepository;

  @BeforeEach
  public void setup() {
    port = mock(ChatDatabasePort.class);
    chatRepository = new ChatRepository(port);
  }

  @Test
  public void deveChamarRepository() {
    chatRepository.enviarMensagem(null);
    verify(port, times(1)).enviarMensagem(null);
  }
}
