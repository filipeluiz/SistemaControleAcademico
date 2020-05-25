package sistema.gui.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import sistema.disciplina.Disciplina;
import sistema.professor.Professor;
import sistema.turma.Turma;
import sistema.util.Validar;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class TurmaDialogController implements Initializable {
    @FXML
    private ComboBox<Disciplina> disciplinaTurmaField;    
    @FXML
    private ComboBox<String> turnoField;
    @FXML
    private ComboBox<String> diaField;    
    @FXML
    private ComboBox<Professor> CPField;
    @FXML
    private ComboBox<Integer> qtdMaxField;
    @FXML
    private ComboBox<String> periodoField;    

    private Stage dialogStage;
    private Turma turma;
    private boolean okClicked = false; 
    
    private LinkedList<Disciplina> listaDisciplina = new LinkedList<>(); 
    private LinkedList<Professor> listaProfessores = new LinkedList<>();
    
    private ObservableList<Disciplina> categoriasDisciplina;
    private ObservableList<Professor> categoriasProfessor;
    private ObservableList<String> categoriasHorario;
    private ObservableList<String> categoriasDia;    
    private ObservableList<Integer> categoriasQtdMax;
    private ObservableList<String> categoriasPeriodoLetivo;
           
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {         
        carregarCategorias();
    }   
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }     
    
    public void setTurma(Turma turma) { 
        this.turma = turma;
               
        if(turma.getCodigoDisciplina() != null){
            turnoField.setValue(Validar.converterHorarioParaTurno(turma.getHorarios()));
            diaField.setValue(Validar.converterHorarioParaDia(turma.getHorarios()));
            disciplinaTurmaField.setValue(turma.getCodigoDisciplina());
            qtdMaxField.setValue(turma.getQtdMaxAluno());
            CPField.setValue(turma.getCpfProfessor());
            periodoField.setValue(turma.getPeriodoLetivo());             
            disciplinaTurmaField.setDisable(true);
            periodoField.setDisable(true);   
            qtdMaxField.setDisable(true);
            turnoField.setDisable(true);
            diaField.setDisable(true);
        }
    }   
    
    public boolean isOkClicked() {
        return okClicked;
    }       
    
    @FXML
    void cancela(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void ok(ActionEvent event) {
        if (isInputValid()) { 
            Disciplina disciplina = disciplinaTurmaField.getSelectionModel().getSelectedItem(); 
            Professor professor = CPField.getSelectionModel().getSelectedItem();
            String turno = turnoField.getSelectionModel().getSelectedItem();
            Integer qtdMax = qtdMaxField.getSelectionModel().getSelectedItem();
            String dia = diaField.getSelectionModel().getSelectedItem();
            String periodoLetivo = periodoField.getSelectionModel().getSelectedItem();
            
            turma.setCodigoDisciplina(disciplina);
            turma.setCodigoTurma(Validar.imprimeCodigoTurma(turno, disciplina.getPeriodo(), 0)); 
            turma.setHorarios(Validar.imprimeHorario(dia, turno));
            turma.setCpfProfessor(professor);
            turma.setQtdMaxAluno(qtdMax);
            turma.setPeriodoLetivo(periodoLetivo);
            if(turma.getQtdMaxAluno() != 0){
                turma.CpfAlunos();    
            }        
           
            okClicked = true;                 
            dialogStage.close();
        }
    }   
    
    private boolean isInputValid() {    
        String errorMessage = ""; 
        
        if(periodoField.getSelectionModel().getSelectedItem() == null){
            errorMessage = "Por favor selecionar período letivo.";
        }        
        if(qtdMaxField.getSelectionModel().getSelectedItem() == null){
            errorMessage = "Por favor selecionar qtd máxima dos alunos.";
        }   
        if(CPField.getSelectionModel().getSelectedItem() == null){
            errorMessage = "Por favor selecionar professor.";
        }   
        if(disciplinaTurmaField.getSelectionModel().getSelectedItem() != null){
            if (!Validar.isHorarioDia(diaField.getSelectionModel().getSelectedItem(), disciplinaTurmaField.getSelectionModel().getSelectedItem().getCargaHoraria())) {
                errorMessage = "Lembra-se 30h somente 1 dia e 60h 2 dias\n";
            }            
        }  
        if(turnoField.getSelectionModel().getSelectedItem() == null){
            errorMessage = "Por favor selecionar turno.";
        }          
        if(disciplinaTurmaField.getSelectionModel().getSelectedItem() == null) {
            errorMessage = "Por favor selecionar disciplina.";
        }      
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favor inserir o campo válido!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }            
    }    

    public void carregarCategorias(){
        carregar(); 
        categoriasDisciplina = FXCollections.observableArrayList(listaDisciplina);
        categoriasProfessor = FXCollections.observableArrayList(listaProfessores);
        categoriasHorario = FXCollections.observableArrayList("Manhã", "Tarde", "Noite");
        categoriasQtdMax = FXCollections.observableArrayList(30, 60);
        categoriasDia = FXCollections.observableArrayList("Segunda-Feira", "Terça-Feira", "Quarta-Feira", "Quinta-Feira", "Sexta-Feira", "Sábado", "Segunda - Quarta", "Segunda - Quinta", "Terça - Quinta", "Terça - Sexta", "Quarta - Sexta");
        categoriasPeriodoLetivo = FXCollections.observableArrayList(Validar.imprimePeriodoLetivo());         
        
        disciplinaTurmaField.setItems(categoriasDisciplina);
        CPField.setItems(categoriasProfessor);
        turnoField.setItems(categoriasHorario);
        qtdMaxField.setItems(categoriasQtdMax);
        diaField.setItems(categoriasDia);
        periodoField.setItems(categoriasPeriodoLetivo);   
    }    
    
    public void carregar() {
        carregarDisciplina();
        carregarProfessor();
    }
    
    public void carregarDisciplina() {
        System.out.println("Lendo o arquivos disciplina.txt..."); 
        
        try {
            FileInputStream fin = new FileInputStream("disciplina.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
            
            listaDisciplina = (LinkedList<Disciplina>) ois.readObject();   

            ois.close();
        }
        catch(EOFException e) {
            System.out.println("\n Fim de arquivo.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }           
    }    
    
    public void carregarProfessor() {
        System.out.println("Lendo o arquivos professor.txt..."); 
        
        try {
            FileInputStream fin2 = new FileInputStream("professor.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fin2);
   
            listaProfessores = (LinkedList<Professor>) ois2.readObject(); 
            
            ois2.close();
        }
        catch(EOFException e) {
            System.out.println("\n Fim de arquivo.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }           
    }     
}
