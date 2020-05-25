package sistema.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sistema.aluno.Aluno;
import sistema.turma.Turma;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class AlunoMatriculadoDialogController implements Initializable {
    @FXML
    private ListView<Aluno> listView;
    @FXML
    private Label qtdLabel;
    @FXML
    private Label qtdMaxLabel;        
    
    private Stage dialogStage;
    private Turma turma;
    private boolean okClicked = false;  
    private ObservableList<Aluno> observaAluno;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }        

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }      

    public void setTurma(Turma turma) { 
        this.turma = turma;
        
        qtdLabel.setText(Integer.toString(turma.getQtdAluno()));
        qtdMaxLabel.setText(Integer.toString(turma.getQtdMaxAluno()));
        
        observaAluno = FXCollections.observableArrayList(this.turma.getCpfAlunos());
        listView.setItems(observaAluno); 
    }     
    
    public boolean isOkClicked() {
        return okClicked;
    }   
    
    public void atualizar(){
        observaAluno.clear();
        observaAluno.addAll(turma.getCpfAlunos());

        qtdLabel.setText(Integer.toString(turma.getQtdAluno()));
        qtdMaxLabel.setText(Integer.toString(turma.getQtdMaxAluno()));        
    }

    @FXML
    void actionRemove(ActionEvent event) {
        ObservableList<Aluno> alunoSelects = listView.getSelectionModel().getSelectedItems();

        for(int i = 0; i < alunoSelects.size(); i++){
            turma.remove(alunoSelects.get(i));
        }            
        okClicked = true;   
        atualizar();
    }
    
    @FXML
    void cancela(ActionEvent event) {
        dialogStage.close();
    }       
}
