package sistema.pessoa;

import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Contato implements Serializable{
    private String email;
    private String telefone;
    private String endereco;
    
    public Contato() {
    }    

    public Contato(String email, String telefone, String endereco) {
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "E-mail: " + this.getEmail() + "\n" +
               "Telefone: " + this.getTelefone() + "\n" +
               "Endereço: " + this.getEndereco() + "\n";
    }
    
    
}
