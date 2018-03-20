/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ugalileo.ui;

import org.ugalileo.manejador.ManejadorUsuario;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Carlos Marroquin
 */
public class VentanaIngreso extends Stage {
    private static final VentanaIngreso INSTANCE = new VentanaIngreso();
    private static Scene scene;
    private static GridPane gridPane;
    private Text text;
    private TextField textFieldNombreUsuario;
    private PasswordField passwordFieldContrasenia;
    private Button buttonIngresar;
    private Text textInformacion;

    private VentanaIngreso() {
    }

    public static VentanaIngreso getINSTANCE() {
        return INSTANCE;
    }
    
    public void mostrarStage() {
        gridPane = new GridPane();
        gridPane.setId("gridPaneLoginWindow");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        text = new Text("Ingreso"); //Cargar lenguaje
        text.setId("textVentanaIngreso");
        gridPane.add(text, 0, 0, 2, 1);
        
        textFieldNombreUsuario = new TextField();
        textFieldNombreUsuario.setPromptText("Usuario");
        gridPane.add(textFieldNombreUsuario, 1, 1);
        
        passwordFieldContrasenia = new PasswordField();
        passwordFieldContrasenia.setPromptText("Contraseña");
        gridPane.add(passwordFieldContrasenia, 1, 2);
        
        buttonIngresar = new Button("Ingresar");
        buttonIngresar.setDefaultButton(true);
        buttonIngresar.setOnAction((ActionEvent event) -> {
            if (ManejadorUsuario.getMANEJADOR_USUARIO().autenticar(textFieldNombreUsuario.getText(), passwordFieldContrasenia.getText())) {
                this.close();
            }
        });
        gridPane.add(buttonIngresar, 1, 3);
        
        textInformacion = new Text();
        gridPane.add(textInformacion, 0, 4, 2, 1);
        
        gridPane.setMinSize(300, 325);
        scene = new Scene(gridPane);
        
        this.setScene(scene);
        this.show();
        
    }
    
}
