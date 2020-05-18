/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sistema.aluno.Aluno;
import sistema.gui.controller.AlunoDialogController;
import sistema.gui.controller.MainController;
import sistema.gui.controller.ProfessorDialogController;
import sistema.professor.Professor;

/**
 *
 * @author filipe
 */
public class SistemaControleAcademico extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;   
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema Controle Acadêmico");
       
        initHome();
        procedimentoSistema();
    }
    
    public void initHome() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SistemaControleAcademico.class.getResource("view/Home.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();           
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    public void procedimentoSistema() {
        try {
            // Load person overview.
            FXMLLoader loaderMain = new FXMLLoader();
            loaderMain.setLocation(SistemaControleAcademico.class.getResource("view/MainView.fxml")); 
            AnchorPane principal = (AnchorPane) loaderMain.load();
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(principal);

            // Give the controller access to the main app.
            MainController controllerMain = loaderMain.getController();
            controllerMain.setSistema(this);
            controllerMain.carregarSistema();
            primaryStage.setOnCloseRequest(event -> controllerMain.gravarSistema()); 
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
    public boolean mostraDialogAluno(Aluno aluno) {        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SistemaControleAcademico.class.getResource("view/AlunoDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();          
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro aluno");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);            
            
            AlunoDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAluno(aluno);  
            
            dialogStage.showAndWait();

            return controller.isOkClicked();            
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean mostraDialogProfessor(Professor professor) {        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SistemaControleAcademico.class.getResource("view/ProfessorDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();          
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro professor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);            
            
            ProfessorDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProfessor(professor);  
            
            dialogStage.showAndWait();

            return controller.isOkClicked();            
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }    
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    } 
}
