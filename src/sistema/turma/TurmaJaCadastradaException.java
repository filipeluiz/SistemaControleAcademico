package sistema.turma;

/**
 *
 * @author filipe
 */
public class TurmaJaCadastradaException extends Exception {
    private String codigoTurma;
    private String codigoDisciplina;
    
    public TurmaJaCadastradaException(String codigoTurma, String codigoDisciplina){
        super("Turma e Disciplina já estão cadastradas!");
        this.codigoTurma = codigoTurma;
        this.codigoDisciplina = codigoDisciplina;
    }
    
    public String getCodigoTurma(){
        return codigoTurma;
    }      
    
    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }
}
