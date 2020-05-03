/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.aluno;

import sistema.pessoa.*;

/**
 *
 * @author filipe
 */
public class Aluno extends Pessoa {

    public Aluno() {}

    public Aluno(String nome, String cpf, String dataNascimento, char sexo, Contato contato) {
        super(nome, cpf, dataNascimento, sexo, contato);
    }  
}
