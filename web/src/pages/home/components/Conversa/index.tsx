import { useEffect, useRef, useState } from "react";
import "./styles.css";
import { EnviaMensagem } from "../EnviaMensagem";
import { Mensagem } from "../Mensagem";
import { Mensagem as MensagemModel } from "../../../../models/mensagem";

export const Conversa: React.FC = () => {

  const scrollMensagens = useRef<HTMLDivElement>({} as HTMLDivElement);
  const [inputMensagem, setInputMensagem] = useState<string>("");
  const [mensagens, setMensagens] = useState<MensagemModel[]>([]);

  useEffect(() => {
    rolaScrollParaFinal();
    document.addEventListener("keypress", handleEnterEnviaMensagem);
  }, []);

  function rolaScrollParaFinal() {
    const scroll = scrollMensagens.current; 
    scroll.scrollTop = scroll.scrollHeight;
  }

  function handleEnterEnviaMensagem(event: any) {
    if(event.key === "Enter") {
      enviaMensagem();
    }
  }

  function enviaMensagem() {

    if(inputMensagem.trim() === "") {
      return;
    }

    console.log("Enviando mensagem");
    setInputMensagem("");
  }

  function handleEnviarMensagem() {
    enviaMensagem();
  }

  function handleInputMensagemChange(mensagem: string) {
    setInputMensagem(mensagem);
  }

  return (
    <div className="chat-conversa-box">
      <div className="chat-mensagens-box" ref={scrollMensagens}>
        <Mensagem nomeDoAutor="Davi" mensagem="Olá, tudo bem?" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="João" mensagem="Olá, eu me chamo João" isUsuarioLogado />
        <Mensagem nomeDoAutor="João" mensagem="Olá, eu me chamo João" isUsuarioLogado />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Olá, tudo bem?" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="João" mensagem="Olá, eu me chamo João" isUsuarioLogado />
        <Mensagem nomeDoAutor="João" mensagem="Olá, eu me chamo João" isUsuarioLogado />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
        <Mensagem nomeDoAutor="Davi" mensagem="Aplicação websocket" />
      </div>
      <EnviaMensagem onClick={handleEnviarMensagem} value={inputMensagem} useState={handleInputMensagemChange} />
    </div>
  );
}