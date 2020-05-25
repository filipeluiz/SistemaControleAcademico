package sistema.gui.controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sistema.professor.*;
import sistema.gui.SistemaControleAcademico;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class ProfessorController implements Initializable {
    @FXML
    private Button buttonEditar;
    @FXML
    private Button buttonRemover;    
    @FXML
    private TableView<Professor> tabela;
    @FXML
    private TableColumn<Professor, String> cpfCol;
    @FXML
    private TableColumn<Professor, String> nomeCol;
    @FXML
    private Label nomeFixo;
    @FXML
    private Label cpfFixo;
    @FXML
    private Label nascimentoFixo;
    @FXML
    private Label emailFixo;
    @FXML
    private Label telFixo;
    @FXML
    private Label sexoFixo;
    @FXML
    private Label enderecoFixo;      
    @FXML
    private Label nomeLabel;
    @FXML
    private Label cpfLabel;
    @FXML
    private Label nascimentoLabel;
    @FXML
    private Label sexoLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label telLabel;
    @FXML
    private Label enderecoLabel;
    
    private SistemaControleAcademico sistema;

    private LinkedList<Professor> listaProfessor = new LinkedList<>();
    private CadastroProfessor cadastroProfessor = new CadastroProfessor(listaProfessor);  
    private ObservableList<Professor> observableList =  FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpfCol.setCellValueFactory((cpf) -> new SimpleStringProperty(cpf.getValue().getCpf()));
        nomeCol.setCellValueFactory((nome) -> new SimpleStringProperty(nome.getValue().getNome()));
        cpfCol.setSortable(false);
        nomeCol.setSortable(false);
        
        buttonEditar.setDisable(true);
        buttonRemover.setDisable(true);        
  
        listaProfessor = cadastroProfessor.getListaProfessor();       
        mostraDetalhes(null); 
        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostraDetalhes(newValue));         
        // Add observable list data to the table
        tabela.setItems(observableList);         
    }  
    
    public void setSistema(SistemaControleAcademico sistema) {
        this.sistema = sistema;           
    }   
    
    public SistemaControleAcademico getSistema() {
        return this.sistema;
    }    
    
    private void mostraDetalhes(Professor professor) {
        if(professor != null) {
            buttonEditar.setDisable(false);
            buttonRemover.setDisable(false); 
            
            nomeFixo.setText("Nome:");
            cpfFixo.setText("CPF:");
            nascimentoFixo.setText("Data de Nascimento:");
            sexoFixo.setText("Sexo:");
            emailFixo.setText("E-mail");
            telFixo.setText("Telefone");
            enderecoFixo.setText("Endereço:");
            
            nomeLabel.setText(professor.getNome());
            cpfLabel.setText(professor.getCpf());
            nascimentoLabel.setText(professor.getDataNascimento());
            sexoLabel.setText(professor.getSexoString());         
            emailLabel.setText(professor.contato.getEmail());
            telLabel.setText(professor.contato.getTelefone());        
            enderecoLabel.setText(professor.contato.getEndereco());              
        }
        else {
            nomeLabel.setText("");
            cpfLabel.setText("");
            nascimentoLabel.setText("");
            sexoLabel.setText("");         
            emailLabel.setText("");
            telLabel.setText("");        
            enderecoLabel.setText("");            
        }
    }     
    
    @FXML
    void cadastrar(ActionEvent event) {
        Professor novo = new Professor();
        boolean okClicked = sistema.mostraDialogProfessor(novo);
        if(okClicked){
            try {               
                cadastroProfessor.cadastrar(novo);
                atualizarTabela(); 
                gravar();
            } catch (ProfessorJaCadastradoException e) {
                Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, e);
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("CPF já existe");
                alert.setHeaderText(e.getMessage());
                alert.setContentText("Por favor cadastrar outro CPF.");
                alert.showAndWait();
            }
        }   
    }

    @FXML
    void editar(ActionEvent event) {
        Professor selectedProfessor = tabela.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            boolean okClicked = sistema.mostraDialogProfessor(selectedProfessor);
            if (okClicked) {
                atualizarTabela();
                mostraDetalhes(selectedProfessor);
                gravar();
            }
        } else {
            // Nada seleciondo.
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");
                alert.showAndWait();
        }
    }

    @FXML
    void remover(ActionEvent event) {
        int selectedIndex = tabela.getSelectionModel().getSelectedIndex();
        if(selectedIndex != -1) {
            cadastroProfessor.remover(selectedIndex);
            atualizarTabela();
            gravar();
        } 
        else {
            // Nada seleciondo.
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");
                alert.showAndWait();            
        }
    }

    @FXML
    void system(ActionEvent event) {
        System.out.println(sistema);        
        System.out.println(listaProfessor);
        System.out.println(cadastroProfessor.getListaProfessor());
    }    
    
    public void atualizarTabela() {
        listaProfessor = cadastroProfessor.getListaProfessor();
        observableList.clear();
        observableList.addAll(listaProfessor);            
    }      
    
    public void gravar() {
        File arquivo = new File("professor.txt");
        try {          
            FileOutputStream fout = new FileOutputStream(arquivo); 
            ObjectOutputStream oos = new ObjectOutputStream(fout);            
            oos.writeObject(listaProfessor);
            oos.close();
            fout.close();
            System.out.println("Gravou o arquivo professor.txt...");            
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }        
    }
    
    public void carregar() {
        System.out.println("Lendo o arquivo professor.txt");
        try {
            FileInputStream fin = new FileInputStream("professor.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);

            LinkedList<Professor> lista = (LinkedList<Professor>) ois.readObject();
            cadastroProfessor.setListaProfessor(lista);
            atualizarTabela();             
        }
        catch(EOFException e) {
            System.out.println("\n Fim de arquivo.");
        }
        catch(IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }        
    }    
}
