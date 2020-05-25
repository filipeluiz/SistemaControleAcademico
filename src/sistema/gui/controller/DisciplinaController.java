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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import sistema.disciplina.*;
import sistema.gui.SistemaControleAcademico;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class DisciplinaController implements Initializable {
    @FXML
    private Button buttonEditar;  
    @FXML
    private Button buttonRemover;    
    @FXML
    private TableView<Disciplina> tabela;
    @FXML
    private TableColumn<Disciplina, Number> periodoCol;
    @FXML
    private TableColumn<Disciplina, String> codigoCol;
    @FXML
    private TableColumn<Disciplina, String> disciplinaCol;
    @FXML
    private Label codigoFixo;
    @FXML
    private Label nomeDisciplinaFixo;
    @FXML
    private Label periodoFIxo;
    @FXML
    private Label cargaFixo;
    @FXML
    private Label creditoFixo;    
    @FXML
    private Label codigoLabel;
    @FXML
    private Label disciplinaLabel;
    @FXML
    private Label periodoLabel;
    @FXML
    private Label cargaLabel;
    @FXML
    private Label creditoLabel;
    
    private SistemaControleAcademico sistema;

    private LinkedList<Disciplina> listaDisciplina = new LinkedList<>();
    private CadastroDisciplina cadastroDisciplina = new CadastroDisciplina(listaDisciplina);  
    private ObservableList<Disciplina> observableList =  FXCollections.observableArrayList();   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        periodoCol.setCellValueFactory(periodo -> new SimpleIntegerProperty(periodo.getValue().getPeriodo()));
        codigoCol.setCellValueFactory((codigo) -> new SimpleStringProperty(codigo.getValue().getCodigoDisciplina()));
        disciplinaCol.setCellValueFactory((disciplina) -> new SimpleStringProperty(disciplina.getValue().getNomeDisciplina()));
        periodoCol.setSortable(false);
        codigoCol.setSortable(false);
        disciplinaCol.setSortable(false);
        
        buttonEditar.setDisable(true);
        buttonRemover.setDisable(true);
       
        listaDisciplina = cadastroDisciplina.getListaDisciplina();       
        mostraDetalhes(null); 
        tabela.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> mostraDetalhes(newValue));         
        // Add observable list data to the table
        tabela.setItems(observableList);           
    }     

    public LinkedList<Disciplina> getListaDisciplina() {
        return listaDisciplina;
    }  
       
    public void setSistema(SistemaControleAcademico sistema) {
        this.sistema = sistema;           
    } 

    public SistemaControleAcademico getSistema() {
        return this.sistema;
    }   

    private void mostraDetalhes(Disciplina disciplina) {
        if(disciplina != null) {
            buttonEditar.setDisable(false);
            buttonRemover.setDisable(false);
            
            codigoFixo.setText("Código da disciplina:");
            nomeDisciplinaFixo.setText("Nome da disciplina:");
            periodoFIxo.setText("Período:");
            cargaFixo.setText("Carga horária:");
            creditoFixo.setText("Crédito:");
            
            codigoLabel.setText(disciplina.getCodigoDisciplina());
            disciplinaLabel.setText(disciplina.getNomeDisciplina());
            periodoLabel.setText(Integer.toString(disciplina.getPeriodo()));
            cargaLabel.setText(Integer.toString(disciplina.getCargaHoraria()));
            creditoLabel.setText(Integer.toString(disciplina.getCredito()));    
        }
        else {
            codigoLabel.setText("");
            disciplinaLabel.setText("");
            periodoLabel.setText("");
            cargaLabel.setText("");         
            creditoLabel.setText("");          
        }
    }      
    
    @FXML
    void cadastrar(ActionEvent event) {
        Disciplina novo = new Disciplina();
        boolean okClicked = sistema.mostraDialogDisciplina(novo);
        if(okClicked){
            try {               
                cadastroDisciplina.cadastrar(novo);
                atualizarTabela(); 
                gravar();
            } catch (DisciplinaJaCadastradaException e) {
                Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, e);
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
        Disciplina selectedDisciplina = tabela.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
            boolean okClicked = sistema.mostraDialogDisciplina(selectedDisciplina);
            if (okClicked) {
                atualizarTabela();
                mostraDetalhes(selectedDisciplina);
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
            cadastroDisciplina.remover(selectedIndex);
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
  
    public void atualizarTabela() {
        listaDisciplina = cadastroDisciplina.getListaDisciplina();
        observableList.clear();
        observableList.addAll(listaDisciplina);            
    }      
   
    public void gravar() {
        File arquivo = new File("disciplina.txt");
        try {          
            FileOutputStream fout = new FileOutputStream(arquivo); 
            ObjectOutputStream oos = new ObjectOutputStream(fout);            
            oos.writeObject(listaDisciplina);
            oos.close();
            fout.close();       
            System.out.println("Gravou o arquivo disciplina.txt...");
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }        
    }
    
    public void carregar() {
        System.out.println("Lendo o arquivo disciplina.txt");
        try {
            FileInputStream fin = new FileInputStream("disciplina.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);

            LinkedList<Disciplina> lista = (LinkedList<Disciplina>) ois.readObject();
            cadastroDisciplina.setListaDisciplina(lista);
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
