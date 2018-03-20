/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ugalileo.ui;

import org.ugalileo.manejador.ManejadorUsuario;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author Carlos Marroquin
 */
public class VentanaPrincipal extends Application {
    private Scene scene;
    private VBox vBox;
    private HBox hBox;
    private MenuBar menuBar;
    private GridPane gridPane;
    private TabPane tabPane;
    private Tab tabPrincipal;
    private Tab tabAlumno;
    private Tab tabCitas;
    private Tab tabReportes;
    private MenuItem menuItemConectar;
    private MenuItem menuItemDesconectar;
    private MenuItem menuItemSalir;
    
    private MenuBar menuBarIngreso;
    
    private VBox vBoxIngreso;
    private RadioMenuItem radioMenuItemIngreso;
    private RadioMenuItem radioMenuItemIncidente;
    private ToggleGroup toogleGroupIngresoIncidente;
    
    @Override
    public void start(Stage primaryStage) {
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuArchivo());
        
        tabPrincipal = new Tab("Principal");
        
        tabAlumno = new Tab("Alumno");
        tabAlumno.setContent(CRUDAlumno.getCRUD_ALUMNO().getContent());
        tabAlumno.getContent().setDisable(true);
        
        tabCitas = new Tab("Citas");
        tabCitas.setContent(new Label("PRUEBA"));
        
        
        tabReportes = new Tab("Reportes");
        
        tabPane = new TabPane();
        tabPane.getTabs().addAll(tabPrincipal,  
                tabAlumno, tabCitas, tabReportes);
        
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setPrefWidth(1000);
        
        hBox = new HBox();
        hBox.getChildren().add(gridPane);
        
        vBox = new VBox();
        vBox.getChildren().addAll(menuBar, tabPane, hBox);
        
        scene = new Scene(vBox, 1000, 700);
        primaryStage.setTitle("Recepción – " + new Date());
        primaryStage.setScene(scene);
        primaryStage.show();
        
        VentanaIngreso.getINSTANCE().initModality(Modality.APPLICATION_MODAL);
        VentanaIngreso.getINSTANCE().initStyle(StageStyle.UTILITY);
        VentanaIngreso.getINSTANCE().addEventHandler(WindowEvent.WINDOW_HIDING, (Event event) -> {
            if (ManejadorUsuario.getMANEJADOR_USUARIO().getUsuario() != null) {
                conectar();
            }
        });
        VentanaIngreso.getINSTANCE().mostrarStage();
    }
    
    private void desconectar() {
        menuItemDesconectar.setVisible(false);
        menuItemConectar.setVisible(true);
        tabAlumno.getContent().setDisable(true);
        ManejadorUsuario.getMANEJADOR_USUARIO().setUsuario(null);
    }
    
    private void conectar() {
        tabAlumno.getContent().setDisable(false);
        menuItemConectar.setVisible(false);
        menuItemDesconectar.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private VBox vBoxIngresoIncidente() {     
        
        toogleGroupIngresoIncidente = new ToggleGroup();
        
        radioMenuItemIngreso = new RadioMenuItem("Ingreso");
        radioMenuItemIngreso.setSelected(true);
        radioMenuItemIngreso.setDisable(true);
        radioMenuItemIngreso.setToggleGroup(toogleGroupIngresoIncidente);
        radioMenuItemIngreso.setOnAction((ActionEvent) -> {
            vBoxIngreso.getChildren().clear();
            vBoxIngreso.getChildren().add(menuBarIngreso);
            //vBoxIngreso.getChildren().add(CRUDIngreso.getINGRESO().getContent());
            radioMenuItemIngreso.setDisable(true);
            radioMenuItemIncidente.setDisable(false);
        });
        
        radioMenuItemIncidente = new RadioMenuItem("Incidente");
        radioMenuItemIncidente.setToggleGroup(toogleGroupIngresoIncidente);
        radioMenuItemIncidente.setOnAction((ActionEvent) -> {
            vBoxIngreso.getChildren().clear();
            vBoxIngreso.getChildren().add(menuBarIngreso);
            //vBoxIngreso.getChildren().add(CRUDIncidente.getINCIDENTE().getContent());
            radioMenuItemIngreso.setDisable(false);
            radioMenuItemIncidente.setDisable(true);
        });
        
        Menu menuVer = new Menu("Ver");
        menuVer.getItems().addAll(radioMenuItemIngreso, radioMenuItemIncidente);
        
        menuBarIngreso = new MenuBar();
        menuBarIngreso.getMenus().add(menuVer);
        
        vBoxIngreso = new VBox();
        vBoxIngreso.getChildren().addAll(menuBarIngreso);

        return vBoxIngreso;
    }
    
    private Menu menuArchivo() {
        
        menuItemConectar = new MenuItem("Conectar");
        menuItemConectar.setId("menuItemConectar");
        menuItemConectar.setAccelerator(KeyCombination.keyCombination("alt + c"));
        menuItemConectar.setOnAction((ActionEvent t) -> {
            if (ManejadorUsuario.getMANEJADOR_USUARIO().getUsuario() == null) {
                VentanaIngreso.getINSTANCE().mostrarStage();
            }
            
        });
        
        menuItemDesconectar = new MenuItem("Desconectar");
        menuItemDesconectar.setId("menuItemDesconectar");
        menuItemDesconectar.setVisible(false);
        menuItemDesconectar.setAccelerator(KeyCombination.keyCombination("alt + d"));
        menuItemDesconectar.setOnAction((ActionEvent t) -> {
            if (ManejadorUsuario.getMANEJADOR_USUARIO().getUsuario() != null) {
                desconectar();
            }
            
        });
        
        menuItemSalir = new MenuItem("Salir");
        menuItemSalir.setId("menuItemSalir");
        menuItemSalir.setAccelerator(KeyCombination.keyCombination("alt + s"));
        menuItemSalir.setOnAction((ActionEvent t) -> {
            System.exit(0);
            /*try {
                switch (Mensaje.mensajeDePreguntaSalida(HandlerUsuario.getInstance().getUsuario().getNombreUsuario())) {
                    case 0:
                        System.exit(0);
                        break;
                }
            } catch (NullPointerException ex) {                
                switch (Mensaje.mensajeDePreguntaSalida(null)) {
                    case 0:
                        System.exit(0);
                        break;
                }
            }*/
        });
        
        Menu submenuUsuario = new Menu("Usuario");
        submenuUsuario.getItems().addAll(menuItemConectar, menuItemDesconectar);
        
        Menu menuArchivo = new Menu("_Archivo");
        menuArchivo.setId("menuArchivo");
        menuArchivo.getItems().addAll(submenuUsuario, menuItemSalir);
        return menuArchivo;
    }
    
    
    
}
