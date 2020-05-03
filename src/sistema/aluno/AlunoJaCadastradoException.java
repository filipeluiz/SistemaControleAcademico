/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.aluno;

/**
 *
 * @author filipe
 */
public class AlunoJaCadastradoException extends Exception {
    private String cpf;
    
    public AlunoJaCadastradoException(String cpf){
        super ("Aluno já cadastrado!");
        this.cpf = cpf;
    }
    
    public String getCpf(){
        return cpf;
    }    
}
