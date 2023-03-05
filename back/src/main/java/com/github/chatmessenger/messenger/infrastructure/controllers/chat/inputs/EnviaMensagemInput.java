package com.github.chatmessenger.messenger.infrastructure.controllers.chat.inputs;

import com.github.chatmessenger.messenger.domain.models.Mensagem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EnviaMensagemInput {

  private Mensagem mensagem;
}
