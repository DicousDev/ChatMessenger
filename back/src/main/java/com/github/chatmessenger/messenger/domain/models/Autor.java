package com.github.chatmessenger.messenger.domain.models;

import java.util.UUID;

public class Autor {

  private UUID id;
  private String nome;

  private Autor(String nome) {

    if(nome == null) {
      throw new RuntimeException("Não é possível criar um autor com nome null");
    }

    nome = nome.trim();
    if(nome.isEmpty()) {
      throw new RuntimeException("Não é possível criar um autor com nome em branco");
    }

    this.id = UUID.randomUUID();
    this.nome = nome;
  }

  public static Autor criar(String nome) {
    return new Autor(nome);
  }

  public UUID getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }
}
