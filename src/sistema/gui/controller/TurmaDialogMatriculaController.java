package sistema.gui.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sistema.aluno.Aluno;
import sistema.turma.Turma;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class TurmaDialogMatriculaController implements Initializable {
    
    @FXML
    private TableView<Aluno> tabelaAluno;   
    @FXML
    private TableColumn<Aluno, String> columCPF;
    @FXML
    private TableColumn<Aluno, String> columNamoAluno; 
    
    private Stage dialogStage;
    private Turma turma;
    private boolean okClicked = false;     
    private LinkedList<Aluno> listaAluno = new LinkedList<>();
    private ObservableList<Aluno> observaAluno; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }      
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }      

    public void setTurma(Turma turma) { 
        this.turma = turma;
    }    
    
    public boolean isOkClicked() {
        return okClicked;
    }           
    
    @FXML
    void actionMatricular(ActionEvent event) { 
        ObservableList<Aluno> alunoSelects = tabelaAluno.getSelectionModel().getSelectedItems();

        for(int i = 0; i < alunoSelects.size(); i++){ 
            turma.add(alunoSelects.get(i));
        }            
        
        okClicked = true;                 
        dialogStage.close();      
    }

    @FXML
    void cancela(ActionEvent event) {
        dialogStage.close();
    }      
    
    public void carregarTabela(){
        carregarAluno();
        observaAluno = FXCollections.observableArrayList(listaAluno);
        
        columCPF.setCellValueFactory((cpf) -> new SimpleStringProperty(cpf.getValue().getCpf()));
        columNamoAluno.setCellValueFactory((nome) -> new SimpleStringProperty(nome.getValue().getNome()));
        
        columCPF.setSortable(false);
        columNamoAluno.setSortable(false);         
        
        tabelaAluno.setItems(observaAluno);  
        tabelaAluno.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);  
    }     
    
    public void carregarAluno() {
        System.out.println("Lendo o arquivos aluno.txt..."); 
        
        try {
            FileInputStream fin3 = new FileInputStream("aluno.txt");
            ObjectInputStream ois3 = new ObjectInputStream(fin3);

            listaAluno = (LinkedList<Aluno>) ois3.readObject();

            ois3.close();
        }
        catch(EOFException e) {
            System.out.println("\n Fim de arquivo.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }           
    }     
    
}
