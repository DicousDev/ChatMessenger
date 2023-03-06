import { useRef, useState, useEffect } from "react";
import "./styles.css";
import { Mensagem as MensagemModel } from "../../../../models/mensagem";
import { UsuarioProps } from "../../../../models/usuario";
import { EnviaMensagem } from "../EnviaMensagem";
import { Mensagem } from "../Mensagem";

interface ConversaProps {
  usuario: UsuarioProps;
  mensagens: MensagemModel[];
  enviarMensagem: (inputValue: string) => void;
}

export const Conversa: React.FC<ConversaProps> = ({ usuario, mensagens, enviarMensagem }: ConversaProps) => {

  const scrollMensagens = useRef<HTMLDivElement>({} as HTMLDivElement);
  const [inputMensagem, setInputMensagem] = useState<string>("");

  useEffect(() => {
    const scroll = scrollMensagens.current;
    scroll.scrollTop = scroll.scrollHeight;
  }, [mensagens]);

  function handleEnviarMensagem() {
    enviarMensagem(inputMensagem);
    setInputMensagem("");
  }

  function handleInputMensagemChange(mensagem: string) {
    setInputMensagem(mensagem);
  }

  return (
    <div className="chat-conversa-box">
      <div className="chat-mensagens-box" ref={scrollMensagens}>
        <Mensagem nomeDoAutor="Administrador" mensagem="Bem vindo ao chat!" />
        {
          mensagens.map((mensagem, index) => (
            <Mensagem key={index} nomeDoAutor={mensagem.autor.nome} mensagem={mensagem.mensagem} isUsuarioLogado={usuario.nome === mensagem.autor.nome} />
          ))
        }
      </div>
      <EnviaMensagem onClick={handleEnviarMensagem} value={inputMensagem} useState={handleInputMensagemChange} />
    </div>
  );
}