package trabalhofinal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author sila
 */
public class SecCrit {

    private HashMap<Integer, DataOutputStream> outboundArray;

    public SecCrit() {
        this.outboundArray = new HashMap<>();
    }

    public void adicionarOutbound(int posicao, DataOutputStream outboud) {
        this.outboundArray.put(posicao, outboud);
    }

    public void removerOutbond(int index) {
        this.outboundArray.remove(index);
    }

    public synchronized void enviaMensagem(byte[] mensagemArray) throws IOException {
        for (DataOutputStream o : this.outboundArray.values()) {
            o.write(mensagemArray, 0, mensagemArray.length);
        }
    }

}
