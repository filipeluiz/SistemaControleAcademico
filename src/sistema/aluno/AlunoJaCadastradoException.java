package sistema.aluno;

/**
 *
 * @author filipe
 */
public class AlunoJaCadastradoException extends Exception {
    private String cpf;
    
    public AlunoJaCadastradoException(String cpf){
        super("Aluno já cadastrado!");
        this.cpf = cpf;
    }
    
    public String getCpf(){
        return cpf;
    }    
}
