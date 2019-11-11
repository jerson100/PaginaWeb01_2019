/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Jerson
 */
public class Cifrado {
    
    public static void main(String[] args) {
        
        System.out.println(cifradoRot03("BNMSDMSU CD BNMNBDQSD"));
        System.out.println((int)'a');
        System.out.println((char)97);
        
    }
    
    public static String cifradoRot03(String txt){
        String cifrado="",abcMi="abcdefghijklmnñopqrstuvwxyz",aux,abcMa=abcMi.toUpperCase();
        char cr;
        int index;
        for (int i = 0; i < txt.length(); i++) {
            cr = txt.charAt(i);
            index = abcMi.indexOf(cr);
            if(index == -1){
                index = abcMa.indexOf(cr);
                aux = abcMa;
            }else{
                aux = abcMi;
            }
            cifrado += index!=-1?nextPost(aux,index):cr;
        }
        return cifrado;
    }
    
    public static String nextPost(String abc,int index){
        index += 3;
        if(index >= abc.length()){
            index = index - abc.length();
        }
        return String.valueOf(abc.charAt(index));
    }
    
}
