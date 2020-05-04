/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.aluno;

import sistema.util.*;

/**
 *
 * @author filipe
 */
public class CadastroAluno {
    private ListaEncadeadaAluno alunos;
    
    public CadastroAluno(ListaEncadeadaAluno alunos) {
        this.alunos = alunos;
    }
    
    public void cadastrar(Aluno aluno) throws AlunoJaCadastradoException, ListaException {
        if(aluno != null) {
            if(alunos.buscar(aluno.getCpf()) != null){
                alunos.inserirOrdenado(aluno);
            }
            else {
                throw new AlunoJaCadastradoException(aluno.getCpf());
            }
        }
    }
    
    public void listar() {
        alunos.exibir();
    }
}
