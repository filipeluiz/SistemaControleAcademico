/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author filipe
 */
public class Date {
    private static final String dataPadrao = "dd/MM/yyyy";
    private static final DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern(dataPadrao);  
    
    //Data converter para String
    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return dataFormato.format(date);
    }    
    
    //String converter para Data
    public static LocalDate parse(String dateString) {
        try {
            return dataFormato.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }  
    
    //Validar Data
    public static boolean validDate(LocalDate date) {
        return date == null;
    }   
}
