package sistema.disciplina;

import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Disciplina implements Serializable {
    private String codigoDisciplina;
    private String nomeDisciplina;
    private int periodo;
    private int cargaHoraria;
    private int credito;
    
    public Disciplina(){}
    
    public Disciplina(String codigoDisciplina, String nomeDisciplina, int periodo, int cargaHoraria, int credito){
        this.codigoDisciplina = codigoDisciplina;
        this.nomeDisciplina = nomeDisciplina;
        this.periodo = periodo;
        this.cargaHoraria = cargaHoraria;
        this.credito = credito;
    }

    public String getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(String codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }
    
    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    @Override
    public String toString() {
        return this.getCodigoDisciplina() + " - " + this.getNomeDisciplina();      
    }
    
//   "\nCódigo da turma: " + this.getCodigoDisciplina() +
//   "\nNome da disciplina: " + this.getNomeDisciplina() +
//   "\nPeríodo: " + this.getPeriodo() + "º" + 
//   "\nCarga horária: " + this.getCargaHoraria() + "h" +
//   "\nCrédito: " + this.getCredito() + 
//   "\n";    
}
