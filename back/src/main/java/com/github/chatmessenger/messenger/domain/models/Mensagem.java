package com.github.chatmessenger.messenger.domain.models;

public class Mensagem {

  private Autor autor;
  private String mensagem;

  private Mensagem(Autor autor, String mensagem) {

    if(autor == null) {
      throw new RuntimeException("Não é possível criar uma mensagem sem autor");
    }

    if(mensagem == null || mensagem.isEmpty()) {
      throw new RuntimeException("Não é possível criar uma mensagem em null ou branco");
    }

    this.autor = autor;
    this.mensagem = mensagem;
  }

  public static Mensagem criar(Autor autor, String mensagem) {
    return new Mensagem(autor, mensagem);
  }

  public String getNomeAutor() {
    return autor.getNome();
  }

  public String getMensagem() {
    return mensagem;
  }
}