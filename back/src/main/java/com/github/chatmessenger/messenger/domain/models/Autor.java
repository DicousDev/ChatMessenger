package com.github.chatmessenger.messenger.domain.models;

import java.util.UUID;

public class Autor {

  private UUID id;
  private String nome;

  private Autor(String nome) {

    if(nome == null || nome.isEmpty()) {
      throw new RuntimeException("Não é possível criar um autor sem nome");
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
