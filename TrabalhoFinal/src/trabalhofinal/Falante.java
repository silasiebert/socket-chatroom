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
            byte[] arrayMenasgem = {1, 2, 3};
            do {

                if (!mensagemNova.equalsIgnoreCase(new String(Encryptor.encrypt(buffer.getArrayMensagem())))) {
                    outbound.write(buffer.getArrayMensagem(), 0, buffer.getArrayMensagem().length);
                    arrayMenasgem = buffer.getArrayMensagem();

                    mensagemNova = new String(Encryptor.encrypt(arrayMenasgem));
                    System.out.println(Thread.currentThread().getName() + " mandou de  " + this.con.getInetAddress() + " no instante " + System.currentTimeMillis() + " : " + mensagemNova);

                }
            } while (!mensagemNova.equals("parar"));
            outbound.close();
            this.con.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
