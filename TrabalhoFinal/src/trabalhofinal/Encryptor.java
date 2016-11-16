/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofinal;

import java.util.ArrayList;

/**
 *
 * @author sila
 */
public class Encryptor {

    public static void main(String[] args) {
//        byte[] mensagemNormal = mensagem.getBytes();
//        ArrayList<Byte>mensagemEnc  = new ArrayList<Byte>();
//        
        System.out.println(Integer.parseInt(Integer.toBinaryString(10)));
        //byte input
        String input = Integer.toBinaryString(10);
        int sad = Integer.parseInt(input);
        System.out.println("Binario: "+sad);
        int lenght = input.length();
        String multiplier = "1111111111";
        multiplier = multiplier.substring(0, lenght);
        
        int mInt = Integer.parseInt(multiplier);
        //byte resultante
        int res = sad^mInt;
        System.out.println("Operacao: "+sad+"^"+mInt+"= "+res);
        System.out.println(Integer.parseUnsignedInt(String.valueOf(res),2));
        
    }

    public void encrypt(String mensagem) {
        byte[] mensagemNormal = mensagem.getBytes();
        ArrayList<Byte>mensagemEnc  = new ArrayList<Byte>();
        
        System.out.println(Integer.parseInt(Integer.toBinaryString(5)));
        int sad = Integer.parseInt(Integer.toBinaryString(5));
        int lenght = Integer.toBinaryString(5).length();
        String multiplier = "1111111111";
        multiplier = multiplier.substring(0, lenght);
        int mInt = Integer.parseInt(multiplier);
        System.out.println(sad^mInt);

    }

    public void decrypt(String mensagem) {

    }

}
