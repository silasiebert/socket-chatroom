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
            String mensagemEncryptada;
            byte[] arrayMensagem = {};
            do {

                if (inbound.available() > 0) {
                    System.out.println("Cliente start with :" + inbound.available() + " avaible bytes");
                    arrayMensagem = new byte[inbound.available()];
                    for (int i = 0; i < arrayMensagem.length; i++) {

                        arrayMensagem[i] = inbound.readByte();
                        System.out.println("Byte received");
                    }
                    // ler mensagens recebidas
                    mensagemEncryptada = new String(arrayMensagem);
                    arrayMensagem = Encryptor.encrypt(arrayMensagem);
                    mesagemRecebida = new String(arrayMensagem);
                    if (!mesagemRecebida.isEmpty()) {
                        System.out.println("Cliente recebeu mensagem " + mesagemRecebida);

                        tela.adicionarMensagemNaTela(mesagemRecebida + " ----> " + mensagemEncryptada);
                    }
                }
            } while (tela.isAtiva());

            // fecha streams
            inbound.close();
            outbound.close();
            //fecha socket
            clientSocket.close();
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
