package sistema.turma;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author filipe
 */
public class CadastroTurma implements Serializable {
    private LinkedList<Turma> listaTurma;
    
    public CadastroTurma(LinkedList<Turma> turma) {
        this.listaTurma = turma; 
    }

    public LinkedList<Turma> getListaTurma() {
        return listaTurma;
    }

    public void setListaTurma(LinkedList<Turma> turma) {
        this.listaTurma = turma;
    }
    
    private boolean buscar(String codigoTurma, String codigoDisciplina) {
        for(int i = 0; i < listaTurma.size(); i++) {
            if(listaTurma.get(i).getCodigoTurma().equals(codigoTurma) && listaTurma.get(i).getCodigoDisciplina().getCodigoDisciplina().equals(codigoDisciplina)){
                return true;
            }
        }
        return false;
    }
      
    public void cadastrar(Turma turma) throws TurmaJaCadastradaException {
        if(turma != null) {
            if(!buscar(turma.getCodigoTurma(), turma.getCodigoDisciplina().getCodigoDisciplina())){
                listaTurma.add(turma);
            }
            else {
                throw new TurmaJaCadastradaException(turma.getCodigoTurma(), turma.getCodigoDisciplina().getCodigoDisciplina());
            }
        }
    }
    
    public void remover(int i) {
        listaTurma.remove(i);
    }       
}
