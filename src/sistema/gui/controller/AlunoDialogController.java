package sistema.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import sistema.aluno.Aluno;
import sistema.util.*;

/**
 *
 * @author filipe
 */
public class AlunoDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField cpfField;
    @FXML
    private DatePicker nascimentoField;
    @FXML
    private ToggleGroup grupoSexo;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField enderecoField;
    
    private Stage dialogStage;
    private Aluno aluno;
    private boolean okClicked = false;  
    
    @FXML
    public void initialize() {}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        
        nameField.setText(aluno.getNome());
        cpfField.setText(aluno.getCpf());
        if(!"".equals(aluno.getCpf())){
            cpfField.setEditable(false);
            cpfField.setBackground(Background.EMPTY);            
        }
        nascimentoField.setValue(Date.parse(aluno.getDataNascimento()));
        //Como ativar radio button
        telefoneField.setText(aluno.getContato().getTelefone());
        emailField.setText(aluno.getContato().getEmail());  
        enderecoField.setText(aluno.getContato().getEndereco());
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
            aluno.setNome(nameField.getText());
            aluno.setCpf(Validar.imprimeCPF(cpfField.getText()));
            aluno.setDataNascimento(Date.format(nascimentoField.getValue()));
            RadioButton radio = (RadioButton) grupoSexo.getSelectedToggle();   
            aluno.setSexo(radio.getText().charAt(0));
            aluno.getContato().setEmail(emailField.getText());
            aluno.getContato().setTelefone(Validar.imprimeTel(telefoneField.getText()));
            aluno.getContato().setEndereco(enderecoField.getText());
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
