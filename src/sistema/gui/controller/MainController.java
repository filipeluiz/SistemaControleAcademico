/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sistema.gui.SistemaControleAcademico;

/**
 *
 * @author filipe
 */
public class MainController implements Initializable {
    private SistemaControleAcademico sistema;  
    
    @FXML
    private AlunoController alunoController;
    
    @FXML
    private ProfessorController professorController;    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
    public void setSistema(SistemaControleAcademico sistema) {
        this.sistema = sistema;           
    } 

    public void carregarSistema(){       
        alunoController.setSistema(sistema); 
        professorController.setSistema(sistema);          
        alunoController.carregar();
        professorController.carregar();         
    }
    
    public void gravarSistema() {
        alunoController.gravar();
        professorController.gravar();
    }  
}
