package sistema.professor;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author filipe
 */
public class CadastroProfessor implements Serializable {
    private LinkedList<Professor> listaProfessor;
    
    public CadastroProfessor(LinkedList<Professor> professor) {
        this.listaProfessor = professor; 
    }

    public LinkedList<Professor> getListaProfessor() {
        return listaProfessor;
    }

    public void setListaProfessor(LinkedList<Professor> professor) {
        this.listaProfessor = professor;
    }
    
    private boolean buscar(String cpf) {
        for(int i = 0; i < listaProfessor.size(); i++) {
            if(listaProfessor.get(i).getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
      
    public void cadastrar(Professor professor) throws ProfessorJaCadastradoException {
        if(professor != null) {
            if(!buscar(professor.getCpf())){
                listaProfessor.add(professor);
            }
            else {
                throw new ProfessorJaCadastradoException(professor.getCpf());
            }
        }
    }
    
    public void remover(int i) {
        listaProfessor.remove(i);
    }    
}
