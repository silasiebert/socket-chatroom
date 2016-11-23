package trabalhofinal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        SecCrit b = new SecCrit();
        int numConexao = 0;
        try {
            Socket clientSocket;
            serverSocket = new ServerSocket(6666);
            while (true) {
                // declara-se pronto receber conexoes e bloqueia ate recebe-las
                clientSocket = serverSocket.accept();
                (new Thread(new Ouvinte(clientSocket, b, numConexao))).start();
				DataOutputStream outbound = new DataOutputStream(clientSocket.getOutputStream());
                b.adicionarOutbound(numConexao,outbound );
                numConexao++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
