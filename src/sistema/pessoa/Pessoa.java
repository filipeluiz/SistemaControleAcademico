package sistema.pessoa;

import java.io.Serializable;

/**
 *
 * @author filipe
 */
public abstract class Pessoa implements Serializable {
    protected String nome;
    protected String cpf;
    protected String dataNascimento;
    protected char sexo;
    public Contato contato;
    
    public Pessoa() {
        this.nome = "";
        this.cpf = "";
        this.dataNascimento = "";
        this.sexo = ' ';        
        this.contato = new Contato();        
    }
    
    public Pessoa(String nome, String cpf, String dataNascimento, char sexo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.contato = new Contato();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }
    
    public String getSexoString() {
        if(this.sexo == 'M') {
            return "Masculino";
        }
        else {
            return "Feminino";
        }
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "(" + this.getCpf() + ") - " + this.getNome();
    }
    
//               "\nNome: " + this.getNome() + 
//               "\nCPF: " + this.getCpf() + 
//               "\nData de Nascimento: " + this.getDataNascimento() + 
//               "\nSexo: " + this.getSexo() + 
//               "\n" + this.getContato();    
}
