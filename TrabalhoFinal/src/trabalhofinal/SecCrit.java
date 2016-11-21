/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sila
 */
public class SecCrit {

    private ArrayList<DataOutputStream> outboundArray;

    public SecCrit() {
        this.outboundArray = new ArrayList<>();
    }

    public void adicionarOutbound(int posicao, DataOutputStream outboud) {
        this.outboundArray.add(posicao, outboud);
    }

    public void removerOutbond(int index) {
        this.outboundArray.remove(0);
    }

    public synchronized void enviaMensagem(byte[] mensagemArray) throws IOException {
        for (DataOutputStream o : this.outboundArray) {
            o.write(mensagemArray, 0, mensagemArray.length);
        }
    }

}
