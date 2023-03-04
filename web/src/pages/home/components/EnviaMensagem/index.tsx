import "./styles.css";

interface EnviaMensagemProps {
  onClick: () => void;
  value: any;
  useState: any;
}

export const EnviaMensagem: React.FC<EnviaMensagemProps> = ({ onClick, value, useState }: EnviaMensagemProps) => {
  return (
    <div className="chat-envia-mensagem-box">
      <input className="chat-envia-mensagem-input" placeholder="Envie uma mensagem" value={value} onChange={e => useState(e.target.value)} />
      <div className="chat-envia-mensagem-botao-box">
        <button className="chat-envia-mensagem-botao" onClick={onClick}>Enviar</button>
      </div>
    </div>
  );
}