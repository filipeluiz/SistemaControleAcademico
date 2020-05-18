/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.professor;

/**
 *
 * @author filipe
 */
public class ProfessorJaCadastradoException extends Exception {
    private String cpf;
    
    public ProfessorJaCadastradoException(String cpf){
        super("Professor já cadastrado!");
        this.cpf = cpf;
    }
    
    public String getCpf(){
        return cpf;
    }      
}
