package trabalhofinal;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import trabalhofinal.SecCrit;

public class Ouvinte implements Runnable {

    private Socket con;
    private SecCrit secCrit;
    private int posicao;

    public Ouvinte(Socket conexao, SecCrit b, int pos) {
        super();
        this.con = conexao;
        this.secCrit = b;
        this.posicao = pos;
    }

    public void run() {
        try {
            System.out.println("iniciou");

            String condicao = "";
            DataInputStream inbound;
            inbound = new DataInputStream(this.con.getInputStream());
            byte[] arrayMensagem;
            byte[] arrayMensagemDec;
            do {
                arrayMensagem = new byte[inbound.available()];
                for (int i = 0; i < arrayMensagem.length; i++) {
                    arrayMensagem[i] = inbound.readByte();
                }
                arrayMensagemDec = Encryptor.encrypt(arrayMensagem);
                condicao = new String(arrayMensagemDec);
                if (!condicao.isEmpty()) {
                    if (condicao.contains("adieu")) {
                        secCrit.removerOutbond(posicao);
                        inbound.close();
                        this.con.close();
                        System.out.println(Thread.currentThread().getName() + " fechou a conexao");
                        String nome = condicao.split("-")[1];
                        String msg =  nome+" saiu.";
                        byte[]msgSaida = msg.getBytes();
                        secCrit.enviaMensagem(Encryptor.encrypt(msgSaida));
                    } else {

                        secCrit.enviaMensagem(arrayMensagem);
                        System.out.println(Thread.currentThread().getName() + " recebeu de  " + this.con.getInetAddress() + " no instante " + System.currentTimeMillis() + " : " + condicao);
                    }
                }
            } while (!condicao.contains("adieu"));

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
