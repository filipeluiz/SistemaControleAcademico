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
    @FXML
    private DisciplinaController disciplinaController;
    @FXML
    private TurmaController turmaController;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {}
    
    public void setSistema(SistemaControleAcademico sistema) {
        this.sistema = sistema;           
    } 

    public void carregarSistema(){       
        alunoController.setSistema(sistema); 
        professorController.setSistema(sistema);  
        disciplinaController.setSistema(sistema);
        turmaController.setSistema(sistema);
        
        alunoController.carregar();
        professorController.carregar();         
        disciplinaController.carregar();
        turmaController.carregar();
        
    }
    
/*    Não tem necessidade quando o aplicativo fechar e salvar os dados, 
      a minha preferencia é quando cadastrar, editar ou remover e salvar na hora.  */
    
//    public void gravarSistema() {
//        alunoController.gravar();
//        professorController.gravar();
//        disciplinaController.gravar();
//        turmaController.gravar();
//    }  
}
