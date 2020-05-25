package sistema.turma;

import java.io.Serializable;
import java.util.Arrays;
import sistema.aluno.Aluno;
import sistema.disciplina.Disciplina;
import sistema.professor.Professor;

/**
 *
 * @author filipe
 */
public class Turma implements Serializable {
    private String codigoTurma;
    private Disciplina codigoDisciplina;
    private String horarios;
    private Professor cpfProfessor;
    private String periodoLetivo;  
    private Aluno[] cpfAlunos;    
    private int qtdMaxAluno;
    private int qtdAluno;
    
    public Turma(){}

    public Turma(String codigoTurma, Disciplina codigoDisciplina, String horarios, Professor cpfProfessor, String periodoLetivo, int qtdMaxAluno, int qtdAluno) {
        this.codigoTurma = codigoTurma;
        this.codigoDisciplina = codigoDisciplina;
        this.horarios = horarios;
        this.cpfProfessor = cpfProfessor;
        this.periodoLetivo = periodoLetivo;
        this.qtdMaxAluno = qtdMaxAluno;
        this.qtdAluno = qtdAluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public Disciplina getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(Disciplina codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Professor getCpfProfessor() {
        return cpfProfessor;
    }

    public void setCpfProfessor(Professor cpfProfessor) {
        this.cpfProfessor = cpfProfessor;
    }

    public String getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(String periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    public Aluno[] getCpfAlunos() {
        return cpfAlunos;
    }

    public void setCpfAlunos(Aluno[] cpfAlunos) {
        this.cpfAlunos = cpfAlunos;
    }
    
    public void CpfAlunos(){
        this.cpfAlunos = new Aluno[this.qtdMaxAluno];
    }

    public int getQtdMaxAluno() {
        return qtdMaxAluno;
    }

    public void setQtdMaxAluno(int qtdMaxAluno) {
        this.qtdMaxAluno = qtdMaxAluno;
    }
    
    public void setQtdAluno(int qtdAluno) {
        this.qtdAluno = qtdAluno;
    }    

    public int getQtdAluno() {
        int qtd = 0;
        for(int i = 0; i < this.getCpfAlunos().length; i++){
            if(this.cpfAlunos[i] != null){
                qtd++;
            }
        }
        this.setQtdAluno(qtd);
        return this.qtdAluno;
    }
    
    public void add(Aluno aluno){
        int posicao = 0;
        for(int i = 0; i < this.getCpfAlunos().length; i++) {
            if(this.cpfAlunos[i] != null) {
                if(this.cpfAlunos[i].getCpf().equals(aluno.getCpf())){
                    break;
                }
                posicao += 1; 
            }
        }
        this.cpfAlunos[posicao] = aluno;
    }   
    
    public void remove(Aluno aluno){
        for(int i = 0; i < this.getCpfAlunos().length; i++) {   
            if(this.cpfAlunos[i] != null){
                if(this.cpfAlunos[i].getCpf().equals(aluno.getCpf())){
                    for(int j = i; j < this.getCpfAlunos().length-1; j++){
                        this.cpfAlunos[j] = this.cpfAlunos[j+1];
                    }
                }                 
            }
        }
        this.cpfAlunos[this.getCpfAlunos().length-1] = null;        
    }
    
    public void listar() {
        for(int i = 0; i < this.getCpfAlunos().length; i++) {
            System.out.print(this.cpfAlunos[i] + ", ");
        }        
    }

    @Override
    public String toString() {
        return "\nCodigo da Turma: " + this.getCodigoTurma() + 
               "\nCodigo da Disciplina: " + this.getCodigoDisciplina() + 
               "\nHorarios: " + this.getHorarios() + 
               "\nCPF do Professor: " + this.getCpfProfessor() + 
               "\nPeriodo Letivo: " + this.getPeriodoLetivo() + 
               "\nCPF dos Alunos: " + Arrays.toString(this.getCpfAlunos()) + 
               "\nQuantidade dos Alunos: " + this.getQtdAluno() + 
               "\n";
    }
}
