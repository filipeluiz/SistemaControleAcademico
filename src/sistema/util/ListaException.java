/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.util;

/**
 *
 * @author filipe
 */
public class ListaException extends Exception {
    private Exception exception;

    public ListaException (Exception exception) {
        super("Exceção encapsulada");
        this.exception = exception;
    }

    public String getMessage() {
        return exception.getMessage();
    }

    public void printStackTrace() {
        exception.printStackTrace();
    }    
}
