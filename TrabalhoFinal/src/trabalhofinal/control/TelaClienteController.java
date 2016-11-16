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
import trabalhofinal.view.InterfaceCliente;

/**
 *
 * @author sila
 */
public class TelaClienteController {

    private InterfaceCliente intC;
    private DataOutputStream outbound;

    public TelaClienteController(DataOutputStream out) {
        this.intC = new InterfaceCliente();
        this.outbound = out;
        inicializarComponentes();
        intC.setVisible(true);
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
        int segundos = horaAtual.getSecond();
        String mensagem = horas + ":" + minutos + ":" + segundos + " " + intC.edMensagem.getText();
        outbound.writeUTF(mensagem);
    }
    public void adicionarMensagemNaTela(String mensagem){
        String textoMostrado = intC.taMensagens.getText();
        intC.taMensagens.setText(textoMostrado+"\n"+mensagem);
    }
}