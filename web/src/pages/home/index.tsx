import { Conversa } from "./components/Conversa";
import "./styles.css";

export const Home: React.FC = () => {

  return (
    <main className="chat-box">
      <Conversa />
    </main>
  );
};