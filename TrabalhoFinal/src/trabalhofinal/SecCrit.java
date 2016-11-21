/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;


/**
 *
 * @author sila
 */
public class SecCrit {

//    private String mensagem;
    private byte[] arrayMensagem = {};

//    
//    public SecCrit() {
//        mensagem = "";
//    }
//
//    public String getMensagem() {
//        System.out.println("Pegou mensagem " + mensagem);
//        return mensagem;
//    }
//
//    public synchronized void setMensagem(String mensagem) {
//        System.out.println("Setou mensagem " + mensagem);
//        this.mensagem = mensagem;
//    }
    public byte[] getArrayMensagem() {
        return arrayMensagem;
    }

    public synchronized void setArrayMensagem(byte[] arrayMensagem) {
        this.arrayMensagem = arrayMensagem;
    }

}
