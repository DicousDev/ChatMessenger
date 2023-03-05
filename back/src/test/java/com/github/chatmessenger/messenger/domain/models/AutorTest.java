package com.github.chatmessenger.messenger.domain.models;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AutorTest {

  @Test
  public void deveCriarAutor() {
    Autor autor = Autor.criar("João Victor");
    assertThat(autor.getNome()).isEqualTo("João Victor");
  }

  @Test
  public void deveCriarAutorComNomeComIniciasEmBranco() {
    Autor autor = Autor.criar(" João");
    assertThat(autor.getNome()).isEqualTo("João");
  }

  @Test
  public void deveCriarAutorComNomeComFinaisEmBranco() {
    Autor autor = Autor.criar("João ");
    assertThat(autor.getNome()).isEqualTo("João");
  }

  @Test
  public void deveFalharQuandoCriarAutorComNomeNull() {
    Exception ex = assertThrows(RuntimeException.class, () -> {
      Autor.criar(null);
    });

    assertThat(ex.getMessage()).isEqualTo("Não é possível criar um autor com nome null");
  }

  @Test
  public void deveFalharQuandoCriarAutorComNomeEmBranco() {
    Exception ex = assertThrows(RuntimeException.class, () -> {
      Autor.criar("  ");
    });

    assertThat(ex.getMessage()).isEqualTo("Não é possível criar um autor com nome em branco");
  }
}
