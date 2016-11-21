/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalhofinal.Encryptor;
import trabalhofinal.view.InterfaceCliente;

/**
 *
 * @author sila
 */
public class TelaClienteController {

    private InterfaceCliente intC;
    private DataOutputStream outbound;
    private boolean ativa;

    public TelaClienteController(DataOutputStream out) {
        this.intC = new InterfaceCliente();
        this.outbound = out;
        inicializarComponentes();
        intC.setVisible(true);
        ativa = true;
    }

    public void inicializarComponentes() {
        intC.btEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Clicou no botao");
                    mandarMensagem();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    public void mandarMensagem() throws IOException {
        LocalTime horaAtual = LocalTime.now();
        int horas = horaAtual.getHour();
        int minutos = horaAtual.getMinute();
        String min = String.valueOf(minutos);
        if (minutos < 10) {
            min = "0" + minutos;
        }
        int segundos = horaAtual.getSecond();
        String mensagem = horas + ":" + min + ":" + segundos + " " + intC.edMensagem.getText();
        if (mensagem.contains("Adieu!")) {
            ativa = false;
        }
        byte[] buffer = mensagem.getBytes();
        buffer = Encryptor.encrypt(buffer);
        outbound.write(buffer, 0, buffer.length);
    }

    public void adicionarMensagemNaTela(String mensagem) {
        String textoMostrado = intC.taMensagens.getText();
        intC.taMensagens.setText(textoMostrado + "\n" + mensagem);
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

}
