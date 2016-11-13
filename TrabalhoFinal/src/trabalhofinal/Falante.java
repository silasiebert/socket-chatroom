package trabalhofinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalhofinal.Buffer;

public class Falante implements Runnable {

    private Socket con;
    Buffer buffer;

    public Falante(Socket conexao, Buffer b) {
        super();
        this.con = conexao;
        this.buffer = b;
    }

    public void run() {
        DataOutputStream outbound = null;
        try {
            outbound = new DataOutputStream(this.con.getOutputStream());
            String mensagemNova = "Begin";
      
            while (!mensagemNova.equals("parar")) {
                if (!mensagemNova.equals(buffer.getMensagem())) {
                    System.out.println(Thread.currentThread().getName() + " mandou de  " + this.con.getInetAddress() + " no instante " + System.currentTimeMillis() + " : " + mensagemNova);
                    outbound.writeUTF(buffer.getMensagem());
                    mensagemNova = buffer.getMensagem();
                }
                
            }
            outbound.close();
            this.con.close();

        } catch (IOException ex) {
            Logger.getLogger(Falante.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
