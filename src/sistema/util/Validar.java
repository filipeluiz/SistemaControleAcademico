/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.util;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author filipe
 */
public class Validar {
    public static boolean isCPF(String cpf) {
        String CPF = cpf.replace(".", "").replace("-", "");
        // considera-se erro Validar's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
            CPF.equals("22222222222") || CPF.equals("33333333333") ||
            CPF.equals("44444444444") || CPF.equals("55555555555") ||
            CPF.equals("66666666666") || CPF.equals("77777777777") ||
            CPF.equals("88888888888") || CPF.equals("99999999999") ||
            (CPF.length() != 11)){
            return false;
        }
          
        char dig10, dig11;
        int sm, i, r, num, peso;
          
        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
        // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {              
        // converte o i-esimo caractere do Validar em um numero:
        // por exemplo, transforma o caractere '0' no inteiro 0         
        // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int)(CPF.charAt(i) - 48); 
                sm = sm + (num * peso);
                peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
          
        // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(CPF.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
                return true;
            }
            else{
                return false;
            }
        } catch (InputMismatchException erro) {
                return false;
            }
        }
          
        public static String imprimeCPF(String cpf) {
            String CPF = cpf.replace(".", "").replace(",", "").replace("+", "").replace("-", "").replace("/", "");
            String number = "[0-9]+"; 
            
            if(CPF.matches(number)){
                return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));                
            }
            return CPF;
        }  
        
        public static boolean isName(String name) {
            String regex = "[A-Za-z]+";      
            return name.matches(regex) || name.trim().equals("");            
        }
        
        public static boolean isEmail(String email) {
            Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
            Matcher m = p.matcher(email);
            return m.find() && m.group().equals(email);
        }
        
        public static boolean isTelefone(String tel) {
            String TEL = tel.replace("(", "").replace(")", "").replace("-", "").trim().replace(" ", "");
            Pattern p = Pattern.compile("[1-9]{2}(9)[0-9]{8}");
            Matcher m = p.matcher(TEL);
            return m.find() && m.group().equals(TEL);
        }        
        
        public static String imprimeTel(String tel){
            String TEL = tel.trim().replace("(", "").replace(")", "").replace("+", "").replace("-", "").replace("/", "").replace(" ", "");
            String number = "[1-9]{2}(9)[0-9]{8}"; 
            if(TEL.matches(number)){
                return("(" + TEL.substring(0,2) + ") " + TEL.substring(2, 7) + "-" + TEL.substring(7, 11));                
            }
            return TEL;            
        }    
        
        public static boolean isRG(String rg) {
            String RG = rg.trim().replace(".", "").replace(" ", "").replace("/", "").replace("-", "");
            Pattern p = Pattern.compile("[0-9]{7}");
            Matcher m = p.matcher(RG);
            return m.find() && m.group().equals(RG);
        }    
        
        public static String imprimeRG(String rg){
            String RG = rg.trim().replace(".", "").replace(" ", "").replace("/", "").replace("-", "");
            String number = "[0-9]{7}"; 
            if(RG.matches(number)){
                return(RG.substring(0,1) + "." + RG.substring(1,4) + "." + RG.substring(4,7));                
            }
            return RG;            
        }         
}
