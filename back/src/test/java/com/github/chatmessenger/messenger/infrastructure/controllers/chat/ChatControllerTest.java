package com.github.chatmessenger.messenger.infrastructure.controllers.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.chatmessenger.messenger.domain.models.Autor;
import com.github.chatmessenger.messenger.domain.models.Mensagem;
import com.github.chatmessenger.messenger.infrastructure.controllers.chat.inputs.EnviaMensagemInput;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatControllerTest {

  @LocalServerPort
  private Integer port;
  private WebSocketStompClient webSocketStompClient;

  @BeforeEach
  public void setup() {
    List<Transport> transports = List.of(
        new WebSocketTransport(new StandardWebSocketClient())
    );
    SockJsClient sockJsClient = new SockJsClient(transports);
    webSocketStompClient = new WebSocketStompClient(sockJsClient);
  }

  @Test
  public void deveEnviarMensagem() throws Exception {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1);
    webSocketStompClient.setMessageConverter(new StringMessageConverter());
    StompSession session = webSocketStompClient.connectAsync(getWsPath(), new StompSessionHandlerAdapter() {
      @Override
      public Type getPayloadType(StompHeaders headers) {
        return String.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        blockingQueue.add((String) payload);
      }
    }).get(1, TimeUnit.SECONDS);

    session.subscribe("/chat/public", new StompFrameHandler() {
      @Override
      public Type getPayloadType(StompHeaders headers) {
        return String.class;
      }

      @Override
      public void handleFrame(StompHeaders headers, Object payload) {
        blockingQueue.add((String) payload);
      }
    });

    String payload = criaPayload();
    session.send("/app/mensagem/public", payload);

    String response = criaResponse();
    await()
        .atMost(10, TimeUnit.SECONDS)
        .untilAsserted(() -> assertEquals(response, blockingQueue.poll()));
  }

  private String criaResponse() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Mensagem mensagem = criaMensagem();
    return mapper.writeValueAsString(mensagem);
  }

  private String criaPayload() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    Mensagem mensagem = criaMensagem();
    EnviaMensagemInput input = new EnviaMensagemInput(mensagem);
    return mapper.writeValueAsString(input);
  }

  private Mensagem criaMensagem() {
    Autor autor = Autor.criar("João");
    return Mensagem.criar(autor, "oi teste");
  }

  private String getWsPath() {
    return String.format("http://192.168.0.13:%s/ws", port);
  }
}
