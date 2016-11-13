package trabalhofinal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Buffer b = new Buffer();
        try {
            Socket clientSocket;
            serverSocket = new ServerSocket(6666);
            while (true) {
                // declara-se pronto receber conexoes e bloqueia ate recebe-las
                clientSocket = serverSocket.accept();
                (new Thread(new Ouvinte(clientSocket, b))).start();
                (new Thread(new Falante(clientSocket, b))).start();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
