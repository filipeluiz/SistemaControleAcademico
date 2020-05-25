package sistema.disciplina;

/**
 *
 * @author filipe
 */
public class DisciplinaJaCadastradaException extends Exception {
    private String codigo;
    
    public DisciplinaJaCadastradaException(String codigo){
        super("Disciplina já cadastrada!");
        this.codigo = codigo;
    }
    
    public String getCodigo(){
        return codigo;
    }        
}
