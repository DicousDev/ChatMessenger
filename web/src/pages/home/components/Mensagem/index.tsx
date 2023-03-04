import "./styles.css";

interface MensagemProps {
  nomeDoAutor: string;
  mensagem: string;
  isUsuarioLogado?: boolean;
}

export const Mensagem: React.FC<MensagemProps> = ({ nomeDoAutor, mensagem, isUsuarioLogado }: MensagemProps) => {

  if(isUsuarioLogado) {
    return (
      <div className="chat-mensagem-box-usuario">
        <div className="chat-mensagem-usuario-suporte" />
        <div className="chat-mensagem-box chat-mensagem-usuario">
          <span className="chat-mensagem-autor">{nomeDoAutor}</span>
          <p className="chat-mensagem">{mensagem}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="chat-mensagem-box chat-mensagem-outro">
      <span className="chat-mensagem-autor">{nomeDoAutor}</span>
      <p className="chat-mensagem">{mensagem}</p>
    </div>
  );
}