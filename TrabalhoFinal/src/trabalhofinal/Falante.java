package trabalhofinal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import trabalhofinal.SecCrit;

public class Falante implements Runnable {

    private Socket con;
    SecCrit secCrit;

    public Falante(Socket conexao, SecCrit b) {
        super();
        this.con = conexao;
        this.secCrit = b;
    }

    public void run() {
        DataOutputStream outbound = null;
        try {
            outbound = new DataOutputStream(this.con.getOutputStream());
            String mensagemNova = "Begin";
            byte[] arrayMenasgem = {1, 2, 3};
            do {

                if (!mensagemNova.equalsIgnoreCase(new String(Encryptor.encrypt(secCrit.getArrayMensagem())))) {
                    outbound.write(secCrit.getArrayMensagem(), 0, secCrit.getArrayMensagem().length);
                    arrayMenasgem = secCrit.getArrayMensagem();

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
