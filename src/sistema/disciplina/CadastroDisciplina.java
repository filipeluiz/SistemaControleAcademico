package sistema.disciplina;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author filipe
 */
public class CadastroDisciplina implements Serializable {
    private LinkedList<Disciplina> listaDisciplina;
    
    public CadastroDisciplina(LinkedList<Disciplina> disciplina) {
        this.listaDisciplina = disciplina; 
    }

    public LinkedList<Disciplina> getListaDisciplina() {
        return listaDisciplina;
    }

    public void setListaDisciplina(LinkedList<Disciplina> disciplina) {
        this.listaDisciplina = disciplina;
    }
    
    private boolean buscar(String codigo) {
        for(int i = 0; i < listaDisciplina.size(); i++) {
            if(listaDisciplina.get(i).getCodigoDisciplina().equals(codigo)){
                return true;
            }
        }
        return false;
    }
      
    public void cadastrar(Disciplina disciplina) throws DisciplinaJaCadastradaException {
        if(disciplina != null) {
            if(!buscar(disciplina.getCodigoDisciplina())){
                listaDisciplina.add(disciplina);
            }
            else {
                throw new DisciplinaJaCadastradaException(disciplina.getCodigoDisciplina());
            }
        }
    }
    
    public void remover(int i) {
        listaDisciplina.remove(i);
    }    
}
