package sistema.pessoa;

/**
 *
 * @author filipe
 */
public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String dataNascimento;
    protected char sexo;
    protected Contato contato;
    
    public Pessoa(){}
    
    public Pessoa(String nome, String cpf, String dataNascimento, char sexo, Contato contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.contato = contato;
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
        return "Nome: " + this.getNome() + "\n" +
               "CPF: " + this.getCpf() + "\n" +
               "Data de Nascimento: " + this.getDataNascimento() + "\n" +
               "Sexo: " + this.getSexo() + "\n" +
               this.getContato();
    }
}
