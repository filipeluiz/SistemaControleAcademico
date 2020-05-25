package sistema.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sistema.disciplina.Disciplina;
import sistema.util.Validar;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class DisciplinaDialogController implements Initializable {
    
    @FXML
    private TextField codigoField;
    @FXML
    private TextField disciplinaField;
    @FXML
    private ComboBox<Integer> periodoField;
    @FXML
    private ComboBox<Integer> cargaField;
    @FXML
    private ComboBox<Integer> creditoField;    
    
    private Stage dialogStage;
    private Disciplina disciplina;
    private boolean okClicked = false;    
    
    private ObservableList<Integer> categoriasPeriodo;    
    private ObservableList<Integer> categoriasCarga;   
    private ObservableList<Integer> categoriasCredito;      
    
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

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
        
        if(disciplina.getCodigoDisciplina() != null){        
            codigoField.setText(disciplina.getCodigoDisciplina());
            disciplinaField.setText(disciplina.getNomeDisciplina());
            periodoField.setValue(disciplina.getPeriodo());
            cargaField.setValue(disciplina.getCargaHoraria());
            creditoField.setValue(disciplina.getCredito());
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
            Integer periodo = periodoField.getSelectionModel().getSelectedItem();            
            Integer carga = cargaField.getSelectionModel().getSelectedItem();
            Integer credito = creditoField.getSelectionModel().getSelectedItem();            
            
            disciplina.setCodigoDisciplina(Validar.imprimeCodigo(codigoField.getText()));
            disciplina.setNomeDisciplina(disciplinaField.getText());
            disciplina.setPeriodo(periodo);
            disciplina.setCargaHoraria(carga);
            disciplina.setCredito(credito);

            okClicked = true;       
            dialogStage.close();
        }
    } 
    
    private boolean isInputValid() {    
        String errorMessage = "";  
        
        if(!Validar.isCodigo(codigoField.getText())){
            errorMessage += "Código inválido!\n"; 
        }        
        if (disciplinaField.getText() == null || disciplinaField.getText().length() == 0) {
            errorMessage += "Disciplina inválida!\n";
        }
        if (periodoField.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Por favor selecionar o período.\n";
        }        
        if(cargaField.getSelectionModel().getSelectedItem() == null){
             errorMessage += "Por favor selecionar carga horária.\n";
        }
        if(creditoField.getSelectionModel().getSelectedItem() == null){
             errorMessage += "Por favor selecionar credito.\n";
        }        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campo inválido");
            alert.setHeaderText("Por favr inserir os campo válido!");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }      
    }    
    
    public void carregarCategorias() {
        categoriasPeriodo = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        categoriasCarga = FXCollections.observableArrayList(30,60);
        categoriasCredito = FXCollections.observableArrayList(2, 4);
        
        periodoField.setItems(categoriasPeriodo);
        cargaField.setItems(categoriasCarga);     
        creditoField.setItems(categoriasCredito);
    }
}
