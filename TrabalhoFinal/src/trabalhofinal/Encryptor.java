/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author sila
 */
public class Encryptor {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String msg = "oi tudo";
//        String nova = encrypt(msg);
//        System.out.println(msg);
//        System.out.println(encrypt(nova));
//        System.out.println(encrypt(nova));

    }

    public static byte[] encrypt(byte[] mensagem) throws UnsupportedEncodingException {
//        byte[] normal = mensagem.getBytes("UTF-8");
//        System.out.println(new String(normal));
        byte[] encriptada = new byte[mensagem.length];
        for (int i = 0; i < mensagem.length; i++) {
            encriptada[i] = (byte) ~mensagem[i];
        }
//        for (int i = 0; i < encriptada.length; i++) {
//            encriptada[i] = (byte) ~encriptada[i];
//        }
//        System.out.println(new String(encriptada));
//        return new String(encriptada);
        return encriptada;
    }

}
