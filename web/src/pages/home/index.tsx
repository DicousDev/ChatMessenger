import { useRef, useState } from "react";
import { Message, over } from "stompjs";
import SockJS from "sockjs-client";
import "./styles.css";
import { UsuarioProps } from "../../models/usuario";
import { Mensagem as MensagemModel } from "../../models/mensagem";
import { Conversa } from "./components/Conversa";

var stompClient: any = null;
export const Home: React.FC = () => {

  const [usuario, setUsuario] = useState<UsuarioProps>({
    nome: "",
    conectado: false
  });
  const [mensagens, setMensagens] = useState<MensagemModel[]>([]);
  const inputNome = useRef<HTMLInputElement>({} as HTMLInputElement);

  function onConnected() {
    console.log("Conectado com sucesso!");
    stompClient.subscribe("/chat/public", onReceberMensagem);
  }

  function onError(error: any) {
    console.error("Erro ao tentar conectar", error);
  }
  
  function onReceberMensagem(payload: Message) {
    const { autor, mensagem } = JSON.parse(payload.body);
    const novaMensagem: MensagemModel = {
      autor,
      mensagem
    };

    mensagens.push(novaMensagem);
    setMensagens([...mensagens]);
  }

  function enviaMensagem(mensagem: string) {
    if(mensagem.trim() === "") {
      console.error("Não é possível enviar mensagem em branco");
      return;
    }

    const payload: MensagemModel = {
      autor: {
        nome: usuario.nome
      },
      mensagem: mensagem
    };

    const input = {
      mensagem: payload
    };
  
    const json: string = JSON.stringify(input);
    stompClient.send("/app/mensagem/public", {}, json);
  }

  function handleEnviarMensagem(inputValue: string) {
    enviaMensagem(inputValue);
  }

  function cadastrarUsuario() {
    const nome: string = inputNome.current.value;
    if(nome.trim() === "") {
      return;
    }

    setUsuario({nome: nome, conectado: true});
    const socket = new SockJS(`http://${import.meta.env.VITE_APP_IP}/ws`);
    stompClient = over(socket);
    stompClient.connect({}, onConnected, onError);
  }

  if(usuario.conectado) {
    return (
      <main className="chat-box">
        <Conversa usuario={usuario} mensagens={mensagens} enviarMensagem={handleEnviarMensagem} />
      </main>
    );
  }

  return (
    <div className="chat-conectar-box">
      <div className="chat-formulario-nome-box">
        <div className="chat-formulario-nome">
          <h1 className="chat-formulario-nome-titulo">Chat Messenger</h1>
          <div className="chat-form-box">
            <input className="chat-form-input" placeholder="Qual seu nome?" ref={inputNome} />
            <button className="chat-form-conectar" onClick={cadastrarUsuario}>Conectar</button>
          </div>
        </div>
      </div>
    </div>
  );
};