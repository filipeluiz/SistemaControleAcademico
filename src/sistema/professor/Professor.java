package sistema.professor;

import java.io.Serializable;
import sistema.pessoa.Pessoa;

/**
 *
 * @author filipe
 */
public class Professor extends Pessoa implements Serializable{
    
    public Professor(){}
    
    public Professor(String nome, String cpf, String dataNascimento, char sexo){
        super(nome, cpf, dataNascimento, sexo);
    }
}
