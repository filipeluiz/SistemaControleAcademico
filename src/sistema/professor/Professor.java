/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.professor;

import sistema.pessoa.Pessoa;

/**
 *
 * @author filipe
 */
public class Professor extends Pessoa {
    private String rg;
    private String turma;
    
    public Professor(){}
    
    public Professor(String nome, String cpf, String dataNascimento, char sexo){
        super(nome, cpf, dataNascimento, sexo);
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    @Override
    public String toString() {
        return "\nNome: " + this.getNome() + 
               "\nCPF: " + this.getCpf() + 
               "\nRg: " + this.rg +
               "\nData de Nascimento: " + this.getDataNascimento() + 
               "\nSexo: " + this.getSexo() +
               "\nTurma: " + this.turma +
               "\n" + this.getContato();
    }
}
