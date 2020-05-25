package sistema.aluno;

import java.io.Serializable;
import sistema.pessoa.*;

/**
 *
 * @author filipe
 */
public class Aluno extends Pessoa implements Serializable {
    
    public Aluno() {}

    public Aluno(String nome, String cpf, String dataNascimento, char sexo) {
        super(nome, cpf, dataNascimento, sexo);
    }  
}
