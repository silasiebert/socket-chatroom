package trabalhofinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import trabalhofinal.control.TelaClienteController;

public class Cliente {
    
    public static void main(String[] args) {
        
        try {
            Socket clientSocket = new Socket("127.0.0.1", 6666);
            DataInputStream inbound = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream());
            TelaClienteController tela = new TelaClienteController(outbound);
            String mesagemRecebida = "";
            do {
                
                mesagemRecebida = inbound.readUTF();
                if (!mesagemRecebida.isEmpty()) {
                    System.out.println("Cliente recebeu mensgaem " + mesagemRecebida);
                    
                    tela.adicionarMensagemNaTela(mesagemRecebida);
                }
            } while (!mesagemRecebida.contains("Adieu!"));

// fecha streams
            inbound.close();
            outbound.close();
            //fecha socket
            clientSocket.close();
            System.exit(0);
        } catch (IOException e) {
            // TODO: handle exception
        }
    }
}
