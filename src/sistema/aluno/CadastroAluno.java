/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.aluno;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author filipe
 */
public class CadastroAluno implements Serializable {
    private LinkedList<Aluno> listaAlunos;
    
    public CadastroAluno(LinkedList<Aluno> alunos) {
        this.listaAlunos = alunos; 
    }

    public LinkedList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(LinkedList<Aluno> alunos) {
        this.listaAlunos = alunos;
    }
    
    private boolean buscar(String cpf) {
        for(int i = 0; i < listaAlunos.size(); i++) {
            if(listaAlunos.get(i).getCpf().equals(cpf)){
                return true;
            }
        }
        return false;
    }
      
    public void cadastrar(Aluno aluno) throws AlunoJaCadastradoException {
        if(aluno != null) {
            if(!buscar(aluno.getCpf())){
                listaAlunos.add(aluno);
            }
            else {
                throw new AlunoJaCadastradoException(aluno.getCpf());
            }
        }
    }
    
    public void remover(int i) {
        listaAlunos.remove(i);
    }
}
