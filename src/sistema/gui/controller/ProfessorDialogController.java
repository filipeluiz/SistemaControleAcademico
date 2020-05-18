/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import sistema.professor.Professor;
import sistema.util.Date;
import sistema.util.Validar;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class ProfessorDialogController implements Initializable {
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField cpfField;
    @FXML
    private TextField rgField;    
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField enderecoField;
    @FXML
    private ToggleGroup grupoSexo;
    @FXML
    private DatePicker nascimentoField;
    
    private Stage dialogStage;
    private Professor professor;
    private boolean okClicked = false;  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }      
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    } 
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
        
        nameField.setText(professor.getNome());
        cpfField.setText(professor.getCpf());
        if(professor.getCpf() != ""){
            cpfField.setEditable(false);
            cpfField.setBackground(Background.EMPTY);            
        }
        rgField.setText(professor.getRg());
        nascimentoField.setValue(Date.parse(professor.getDataNascimento()));
        //Como ativar radio button
        telefoneField.setText(professor.getContato().getTelefone());
        emailField.setText(professor.getContato().getEmail());  
        enderecoField.setText(professor.getContato().getEndereco());
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
            professor.setNome(nameField.getText());
            professor.setCpf(Validar.imprimeCPF(cpfField.getText()));
            professor.setRg(Validar.imprimeRG(rgField.getText()));
            professor.setDataNascimento(Date.format(nascimentoField.getValue()));
            RadioButton radio = (RadioButton) grupoSexo.getSelectedToggle();   
            professor.setSexo(radio.getText().charAt(0));
            professor.getContato().setEmail(emailField.getText());
            professor.getContato().setTelefone(Validar.imprimeTel(telefoneField.getText()));
            professor.getContato().setEndereco(enderecoField.getText());
            okClicked = true;       
            dialogStage.close();
        }
    }
    
    private boolean isInputValid() {    
        String errorMessage = "";
        RadioButton radio = (RadioButton) grupoSexo.getSelectedToggle();   

        if (Validar.isName(nameField.getText())) {
            errorMessage += "Nome inválido!\n"; 
        }
        if (!Validar.isCPF(cpfField.getText())) {
            errorMessage += "CPF inválido\n"; 
        }
        if (!Validar.isRG(rgField.getText())) {
            errorMessage += "RG inválido!\n";
        }
        if (Date.validDate(nascimentoField.getValue())) {
            errorMessage += "Data de nascimento inválido!\n"; 
        }
        if (radio == null) {
            errorMessage += "Sexo não foi selecionado!\n"; 
        }        
        if (!Validar.isEmail(emailField.getText())) {
            errorMessage += "E-mail inválido!\n"; 
        } 
        if (!Validar.isTelefone(telefoneField.getText())) {
            errorMessage += "Telefone inválido!\n"; 
        }
        if (enderecoField.getText() == null || enderecoField.getText().length() == 0) {
            errorMessage += "Endereço inválido!\n";
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
            System.out.println(Validar.imprimeTel(telefoneField.getText()));
            return false;
        }      
    }
    
}
