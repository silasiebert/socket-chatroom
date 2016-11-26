/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author sila
 */
public class Encryptor {

    public static byte[] encrypt(byte[] mensagem) throws UnsupportedEncodingException {
        byte[] encriptada = new byte[mensagem.length];
        for (int i = 0; i < mensagem.length; i++) {
            encriptada[i] = (byte) ~mensagem[i];
        }
        return encriptada;
    }

}
