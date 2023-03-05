package com.github.chatmessenger.messenger.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MensagemTest {

  @Test
  public void deveCriarMensagem() {
    Autor autor = Autor.criar("João");
    Mensagem mensagem = Mensagem.criar(autor, "olá teste");
    assertThat(mensagem.getAutor().getNome()).isEqualTo("João");
    assertThat(mensagem.getMensagem()).isEqualTo("olá teste");
  }

  @Test
  public void deveFalharQuandoTentarCriarMensagemSemAutor() {
    Exception ex = assertThrows(RuntimeException.class, () -> {
      Mensagem.criar(null, "olá teste");
    });

    assertThat(ex.getMessage()).isEqualTo("Não é possível criar uma mensagem sem autor");
  }

  @Test
  public void deveFalharQuandoTentarCriarMensagemNull() {
    Autor autor = Autor.criar("João");
    Exception ex = assertThrows(RuntimeException.class, () -> {
      Mensagem.criar(autor, null);
    });

    assertThat(ex.getMessage()).isEqualTo("Não é possível criar uma mensagem null");
  }

  @Test
  public void deveFalharQuandoTentarCriarMensagemEmBranco() {
    Autor autor = Autor.criar("João");
    Exception ex = assertThrows(RuntimeException.class, () -> {
      Mensagem.criar(autor, "  ");
    });

    assertThat(ex.getMessage()).isEqualTo("Não é possível criar uma mensagem em branco");
  }

  @Test
  public void deveCriarMensagemComIniciasEmBranco() {
    Autor autor = Autor.criar("João");
    Mensagem mensagem = Mensagem.criar(autor, " teste");
    assertThat(mensagem.getMensagem()).isEqualTo("teste");
  }

  @Test
  public void deveCriarMensagemComFinaisEmBranco() {
    Autor autor = Autor.criar("João");
    Mensagem mensagem = Mensagem.criar(autor, "teste ");
    assertThat(mensagem.getMensagem()).isEqualTo("teste");
  }
}
