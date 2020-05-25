package sistema.util;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

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
        
        public static boolean isCodigo(String codigo) {
            String CODIGO = codigo.trim().replace(".", "").replace(",", "").replace("-", "").replace("+", "").replace(" ", "");
            Pattern p = Pattern.compile("[A-Za-z]{3}[0-9]{4}");
            Matcher m = p.matcher(CODIGO);
            return m.find() && m.group().equals(CODIGO);
        } 
        
        public static String imprimeCodigo(String codigo){
            String CODIGO = codigo.trim().replace(".", "").replace(",", "").replace("-", "").replace("+", "").replace(" ", "");
            return CODIGO.toUpperCase();
        }   
        
        public static String imprimeCodigoTurma(String turno, int periodo, int nTurno){
            String HORARIO = turno.substring(0,1);     
            return (HORARIO.toUpperCase() + "S" + periodo + "-" + nTurno);
        }
        
        public static boolean isHorarioDia(String dia, int turno) {
            boolean isDia = false;     
            if(dia != null){
                if(turno <= 30) {
                    switch(dia) {
                        case "Segunda-Feira":
                            isDia = true;
                            break;
                        case "Terça-Feira":
                            isDia = true;
                            break;
                        case "Quarta-Feira":
                            isDia = true;
                            break;
                        case "Quinta-Feira":
                            isDia = true;
                            break;
                        case "Sexta-Feira":
                            isDia = true;
                            break;
                        case "Sábado":
                            isDia = true;
                            break;     
                    }
                } else {
                    switch(dia) { // "Segunda - Quarta", "Segunda - Quinta", "Terça - Quinta", "Terça - Sexta", "Quarta - Sexta"
                        case "Segunda - Quarta":
                            isDia = true;
                            break;
                        case "Segunda - Quinta":
                            isDia = true;
                            break;
                        case "Terça - Quinta":
                            isDia = true;
                            break;
                        case "Terça - Sexta":
                            isDia = true;
                            break;
                        case "Quarta - Sexta":
                            isDia = true;
                            break;     
                    }
                }             
            }
            return isDia;  
        }
        
        public static String imprimeHorario(String dia, String turno){
            String DIA;
            int UmDia = 0; 
            int DoisDia = 0;
            String manha1[] = {"AB", "CD", "EF"}; 
            String tarde1[] = {"GH", "IJ", "LM"};
            String noite1[] = {"NO", "PQ"};
            String manha2[] = {"AB", "CD", "EF"}; 
            String tarde2[] = {"GH", "IJ", "LM"};
            String noite2[] = {"NO", "PQ"};  
            Random random = new Random();            
            switch(dia) {
                case "Segunda-Feira":
                    UmDia = 2;
                    break;
                case "Terça-Feira":
                    UmDia = 3;
                    break;
                case "Quarta-Feira":
                    UmDia = 4;
                    break;
                case "Quinta-Feira":
                    UmDia = 5;
                    break;
                case "Sexta-Feira":
                    UmDia = 6;
                    break;
                case "Sábado":
                    UmDia = 7;
                    break;  
                case "Segunda - Quarta":
                    UmDia = 2;
                    DoisDia = 4;
                    break;
                case "Segunda - Quinta":
                    UmDia = 2;
                    DoisDia = 5;
                    break;
                case "Terça - Quinta":
                    UmDia = 3;
                    DoisDia = 5;
                    break;
                case "Terça - Sexta":
                    UmDia = 3;
                    DoisDia = 6;
                    break;
                case "Quarta - Sexta":
                    UmDia = 4;
                    DoisDia = 6;
                    break;                        
            }
            
            if(UmDia != 0 && DoisDia == 0) {                
                if("Manhã".equals(turno)){
                    DIA = UmDia + manha1[random.nextInt(3)];
                }
                else if("Tarde".equals(turno)){
                    DIA = UmDia + tarde1[random.nextInt(3)];
                } 
                else {
                    DIA = UmDia + noite1[random.nextInt(2)];
                }
            }
            else {
                if("Manhã".equals(turno)){
                    DIA = UmDia + manha2[random.nextInt(3)] + " - " + DoisDia + manha2[random.nextInt(3)];
                }
                else if("Tarde".equals(turno)){
                    DIA = UmDia + tarde2[random.nextInt(3)] + " - " + DoisDia + tarde2[random.nextInt(3)];
                } 
                else {
                    DIA = UmDia + noite2[random.nextInt(2)] + " - " + DoisDia + noite2[random.nextInt(2)] ;
                }                
            }
            
            return DIA;  
        }                    
        
        public static String imprimePeriodoLetivo() {
            String letivo;
            Calendar cal = GregorianCalendar.getInstance();
            if((cal.get(Calendar.MONTH) + 1) <= 7){
                letivo = cal.get(Calendar.YEAR) + ".1";
            }
            else {
                letivo = cal.get(Calendar.YEAR) + ".2";
            }
            return letivo;            
        }
        
       public static String converterHorarioParaDia(String h){
            // 2NO - 4PQ
            // 012345678
            String DIA = "error"; 
            String dia = h.substring(0,1); //2           
            String dia2 = "";
            if(h.length() > 3){
                dia2 = h.substring(6,7);    //4            
            }

            if(h.length() <= 3){
                switch(dia){
                    case "2":
                        DIA = "Segunda-Feira";
                        break;
                    case "3":
                        DIA = "Terça-Feira";
                        break;
                    case "4":
                        DIA = "Quarta-Feira";
                        break;   
                    case "5":
                        DIA = "Quinta-Feira";
                        break;   
                    case "6":
                        DIA = "Sexta-Feira";
                        break; 
                    case "7":
                        DIA = "Sábado";
                        break;                           
                }
            }
            else {
                switch(dia + "" + dia2){ 
                    case "24":
                        DIA = "Segunda - Quarta";
                        break;
                    case "25":
                        DIA = "Segunda - Quinta";
                        break;
                    case "35":
                        DIA = "Terça - Quinta";
                        break; 
                    case "36":
                        DIA = "Terça - Sexta";
                        break; 
                    case "46":
                        DIA = "Quarta - Sexta";
                        break;                           
                }                
            }
            return DIA;
        }        
        
        public static String converterHorarioParaTurno(String h){
            // 2NO - 4PQ
            // 012345678            
            String TURNO = "error"; 
            String turno = h.substring(1,3);           
            String turno2 = "";
            if(h.length() > 3){
                turno2 = h.substring(7,9);            
            }

            if(h.length() <= 3){
                switch(turno){
                    case "AB":
                        TURNO = "Manhã";
                        break;
                    case "GH":
                        TURNO = "Tarde";
                        break;
                    case "NO":
                        TURNO = "Noite";
                        break;                                          
                }
            }
            else {
                switch(turno + "" + turno2){
                    case "ABAB":
                        TURNO = "Manhã";
                        break;
                    case "ABCD":
                        TURNO = "Manhã";
                        break;
                    case "CDAB":
                        TURNO = "Manhã";
                        break;  
                    case "CDCD":
                        TURNO = "Manhã";
                        break;                          
                    case "ABEF":
                        TURNO = "Manhã";
                        break;
                    case "EFAB":
                        TURNO = "Manhã";
                        break; 
                    case "EFEF":
                        TURNO = "Manhã";
                        break;                         
                    case "CDEF":
                        TURNO = "Manhã";
                        break; 
                    case "EFCD":
                        TURNO = "Manhã";
                        break;                         
                    case "GHGH":
                        TURNO = "Tarde";
                        break;
                    case "GHIJ":
                        TURNO = "Tarde";
                        break; 
                    case "IJGH":
                        TURNO = "Tarde";
                        break;
                    case "IJIJ":
                        TURNO = "Tarde";
                        break;  
                    case "LMLM":
                        TURNO = "Tarde";
                        break;  
                    case "GHLM":
                        TURNO = "Tarde";
                        break; 
                    case "LMGH":
                        TURNO = "Tarde";
                        break; 
                    case "IJLM":
                        TURNO = "Tarde";
                        break; 
                    case "LMIJ":
                        TURNO = "Tarde";
                        break;                         
                    case "NONO":
                        TURNO = "Noite";
                        break; 
                    case "NOPQ":
                        TURNO = "Noite";
                        break; 
                    case "PQNO":
                        TURNO = "Noite";
                        break;                         
                    case "PQPQ":
                        TURNO = "Noite";
                        break;                         
                }                
            }
            return TURNO;
        }
}
