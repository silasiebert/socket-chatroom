package trabalhofinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import trabalhofinal.Buffer;

public class Ouvinte implements Runnable {

    private Socket con;
    Buffer buffer;

    public Ouvinte(Socket conexao, Buffer b) {
        super();
        this.con = conexao;
        this.buffer = b;
    }

    public void run() {
        try {
            System.out.println("iniciou");
            // Cria stream de entrada
            String condicao = "";
            DataInputStream inbound;
            // Cria stream de entrada
            inbound = new DataInputStream(this.con.getInputStream());

          
            while (!condicao.equals("tchau")) {
                
                // ler mensagens recebidas
                condicao = inbound.readUTF();
                if (!condicao.isEmpty()) {
                    
                    buffer.setMensagem(condicao);
                    System.out.println(Thread.currentThread().getName() + " recebeu de  " + this.con.getInetAddress() + " no instante " + System.currentTimeMillis() + " : " + condicao);
                     
                }
               
                
            }
            //fecha stream de entrada
            inbound.close();
            //fecha stream de saida
          
            //fecha conexao com o cliente
            this.con.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
