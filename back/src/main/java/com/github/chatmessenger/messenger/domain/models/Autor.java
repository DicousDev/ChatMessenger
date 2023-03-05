package com.github.chatmessenger.messenger.domain.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Autor {
  private String nome;

  private Autor(String nome) {

    if(nome == null) {
      throw new RuntimeException("Não é possível criar um autor com nome null");
    }

    nome = nome.trim();
    if(nome.isEmpty()) {
      throw new RuntimeException("Não é possível criar um autor com nome em branco");
    }

    this.nome = nome;
  }

  public static Autor criar(String nome) {
    return new Autor(nome);
  }

  public String getNome() {
    return nome;
  }
}
