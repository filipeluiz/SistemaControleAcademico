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
import sistema.gui.SistemaControleAcademico;
import sistema.turma.CadastroTurma;
import sistema.turma.*;

/**
 * FXML Controller class
 *
 * @author filipe
 */
public class TurmaController implements Initializable {
    @FXML
    private Button buttonEditar;
    @FXML
    private Button buttonRemover;    
    @FXML
    private Button buttonMatricular;    
    @FXML
    private TableView<Turma> tabela;
    @FXML
    private TableColumn<Turma,String> turmaCol;
    @FXML
    private TableColumn<Turma,String> disciplinaCol;
    @FXML
    private TableColumn<Turma,String> horariosCol;
    @FXML
    private TableColumn<Turma,String> professorCol;
    @FXML
    private Label codigoTurmaFixo;
    @FXML
    private Label codigoDisciplinaFIxo;
    @FXML
    private Label horarioFixo;
    @FXML
    private Label professorFixo;
    @FXML
    private Label periodoFixo;
    @FXML
    private Label qtdMaxFixo;
    @FXML
    private Button buttonMatriculados;    
    @FXML
    private Label codigoTurmaLabel;
    @FXML
    private Label CodigoDisciplinaLabel;
    @FXML
    private Label horarioLabel;
    @FXML
    private Label professorLabel;
    @FXML
    private Label periodoLetivoLabel;
    @FXML
    private Label qtdMaxLabel;  

    private SistemaControleAcademico sistema;

    private LinkedList<Turma> listaTurma = new LinkedList<>();   
    private CadastroTurma cadastroTurma = new CadastroTurma(listaTurma);  
    private ObservableList<Turma> observableList =  FXCollections.observableArrayList(); 
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        turmaCol.setCellValueFactory(turma -> new SimpleStringProperty(turma.getValue().getCodigoTurma()));
        disciplinaCol.setCellValueFactory((disciplina) -> new SimpleStringProperty(disciplina.getValue().getCodigoDisciplina().getCodigoDisciplina()));
        horariosCol.setCellValueFactory((horario) -> new SimpleStringProperty(horario.getValue().getHorarios()));
        professorCol.setCellValueFactory((professor) -> new SimpleStringProperty(professor.getValue().getCpfProfessor().getCpf()));
        
        buttonEditar.setDisable(true);
        buttonRemover.setDisable(true);   
        buttonMatriculados.setVisible(false);
        buttonMatricular.setVisible(false);
        
        turmaCol.setSortable(false);
        disciplinaCol.setSortable(false);
        horariosCol.setSortable(false);
        professorCol.setSortable(false);
  
        listaTurma = cadastroTurma.getListaTurma();       
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
    
    private void mostraDetalhes(Turma turma) {
        if(turma != null) {
            buttonEditar.setDisable(false);
            buttonRemover.setDisable(false);
            buttonMatricular.setVisible(true);
            buttonMatriculados.setVisible(true);
            
            codigoTurmaFixo.setText("Código da Turma:");
            codigoDisciplinaFIxo.setText("Código da disciplina:");
            horarioFixo.setText("Horário:");
            professorFixo.setText("Professor:");
            periodoFixo.setText("Período Letivo:");
            qtdMaxFixo.setText("Quantidade máxima:");          
            
            codigoTurmaLabel.setText(turma.getCodigoTurma());
            CodigoDisciplinaLabel.setText(turma.getCodigoDisciplina().getCodigoDisciplina() + " - " + turma.getCodigoDisciplina().getNomeDisciplina());
            horarioLabel.setText(turma.getHorarios());
            professorLabel.setText(turma.getCpfProfessor().getCpf() + " - " + turma.getCpfProfessor().getNome());
            periodoLetivoLabel.setText(turma.getPeriodoLetivo());    
            qtdMaxLabel.setText(Integer.toString(turma.getQtdMaxAluno()));    
        }
        else {
            codigoTurmaFixo.setText("");
            codigoDisciplinaFIxo.setText("");
            horarioFixo.setText("");
            professorFixo.setText("");
            periodoFixo.setText("");
            qtdMaxFixo.setText("");           
            codigoTurmaLabel.setText("");
            CodigoDisciplinaLabel.setText("");
            horarioLabel.setText("");
            professorLabel.setText("");         
            periodoLetivoLabel.setText(""); 
            qtdMaxLabel.setText(""); 
            buttonMatricular.setVisible(false);
            buttonMatriculados.setVisible(false);            
        }
    }    
    
    @FXML
    void cadastrar(ActionEvent event) {
        Turma novo = new Turma();
        boolean okClicked = sistema.mostraDialogTurma(novo);
        if(okClicked){
            try {         
                cadastroTurma.cadastrar(novo);
                atualizarTabela(); 
                gravar();                
            } catch (TurmaJaCadastradaException e) {
                Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, e);
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Turma já existe");
                alert.setHeaderText(e.getMessage());
                alert.setContentText("Por favor cadastrar outra Turma.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void editar(ActionEvent event) {
        Turma selectedTurma = tabela.getSelectionModel().getSelectedItem();
        if(selectedTurma != null) {
            boolean okClicked = sistema.mostraDialogTurma(selectedTurma);
            if(okClicked){              
                atualizarTabela();
//                mostraDetalhes(selectedTurma);
                gravar();
            }
        } else{    
            // Nada seleciondo.
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Turma Selecionada");
                alert.setContentText("Por favor, selecione uma turma na tabela.");
                alert.showAndWait();
        }            
    }

    @FXML
    void info(ActionEvent event) {
        Turma selectedTurma = tabela.getSelectionModel().getSelectedItem();
        if(selectedTurma != null) {
            boolean okClicked = sistema.mostraAlunoMatriculado(selectedTurma);
            if(okClicked){              
                gravar();
            }
        } else{    
            // Nada seleciondo.
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");
                alert.showAndWait();
        } 
    }
    
    @FXML
    void matriculaAlunos(ActionEvent event) {
        Turma selectedTurma = tabela.getSelectionModel().getSelectedItem();
        if(selectedTurma != null) {
            boolean okClicked = sistema.mostraDialogTurmaMatricular(selectedTurma);
            if(okClicked){              
                atualizarTabela();
                gravar();
            }
        } else{    
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
            cadastroTurma.remover(selectedIndex);
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
        listaTurma = cadastroTurma.getListaTurma();
        observableList.clear();
        observableList.addAll(listaTurma);            
    }   
    
    public void gravar() {
        File arquivo = new File("turma.txt");
        try {          
            FileOutputStream fout = new FileOutputStream(arquivo); 
            ObjectOutputStream oos = new ObjectOutputStream(fout);            
            oos.writeObject(listaTurma);
            oos.close();
            fout.close();  
            System.out.println("Gravou o arquivo turma.txt...");
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }        
    }
    
    public void carregar() {
        System.out.println("Lendo o arquivo turma.txt");
        try {
            FileInputStream fin = new FileInputStream("turma.txt");
            ObjectInputStream ois = new ObjectInputStream(fin);
               
            LinkedList<Turma> lista = (LinkedList<Turma>) ois.readObject();
            cadastroTurma.setListaTurma(lista);
            
            ois.close();
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
