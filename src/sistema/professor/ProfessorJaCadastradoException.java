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
