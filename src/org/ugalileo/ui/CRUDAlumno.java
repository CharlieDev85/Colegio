/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ugalileo.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.ugalileo.bean.Alumno;
import org.ugalileo.bean.Gradoseccion;
import org.ugalileo.db.AsistenteConsulta;
import org.ugalileo.manejador.ManejadorAlumno;

/**
 *
 * @author Claudio Danilo Canel
 */
public class CRUDAlumno {
    private static final CRUDAlumno CRUD_ALUMNO = new CRUDAlumno();
    private GridPane gridPane;
    private HBox hBox;
    private Text text;
    private TableColumn<Alumno, Integer> tableColumnIdAlumno;
    private TableColumn<Alumno, Gradoseccion> tableColumnGradoSeccion;
    private TableColumn<Alumno, String> tableColumnNombre;
    private TableColumn<Alumno, String> tableColumnApellido;
    private TableView<Alumno> tableViewAlumno;
    private ObservableList<Alumno> observableListAlumno;
    
    private HBox hBoxPrimerGrupo;
    private HBox hBoxSegundoGrupo;
    
    private ToggleButton toggleButtonAgregar;
    private ToggleButton toggleButtonModificar;
    private ToggleGroup toggleGroup;

    public static CRUDAlumno getCRUD_ALUMNO() {
        return CRUD_ALUMNO;
    }

    public CRUDAlumno() {
    }
    

    public Node getContent() {
        observableListAlumno = FXCollections.observableArrayList(AsistenteConsulta.getASISTENTE_CONSULTA().consulta("FROM Alumno"));
        gridPane = new GridPane();
        gridPane.setId("gridPaneAlumno");
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
        
        
        hBoxPrimerGrupo = new HBox(10);
        hBoxPrimerGrupo.setAlignment(Pos.CENTER_LEFT);
        
        gridPane.add(hBoxPrimerGrupo, 2, 1);
        
        toggleGroup = new ToggleGroup();
        
        toggleButtonAgregar = new ToggleButton("Agregar");
        toggleButtonAgregar.setSelected(true);
        toggleButtonAgregar.setToggleGroup(toggleGroup);
        
        toggleButtonModificar = new ToggleButton("Modificar");
        toggleButtonModificar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (tableViewAlumno.getSelectionModel().getSelectedItem() != null) {
                    UpdateAlumno updateAlumno = new UpdateAlumno();
                    hBox.getChildren().clear();
                    hBox.getChildren().addAll(gridPane, updateAlumno.getContent());
                }
                
            }
        });
        toggleButtonModificar.setToggleGroup(toggleGroup);
        
        hBoxSegundoGrupo = new HBox(10);
        hBoxSegundoGrupo.setAlignment(Pos.BOTTOM_CENTER);
        hBoxSegundoGrupo.getChildren().addAll(toggleButtonAgregar, toggleButtonModificar);
        gridPane.add(hBoxSegundoGrupo, 1, 2, 2, 3);
        
        tableColumnIdAlumno = new TableColumn<>("ID");
        tableColumnIdAlumno.setCellValueFactory(new PropertyValueFactory<>("idAlumno"));

        tableColumnGradoSeccion = new TableColumn<>("GradoSeccion");
        tableColumnGradoSeccion.setCellValueFactory(new PropertyValueFactory<>("gradoseccion"));
        
        tableColumnNombre = new TableColumn<>("Nombre");
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        tableColumnApellido = new TableColumn<>("Apellido");
        tableColumnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        
        tableViewAlumno = new TableView<>(observableListAlumno);
        tableViewAlumno.getColumns().addAll(tableColumnIdAlumno, tableColumnGradoSeccion,
                tableColumnNombre, tableColumnApellido);
        tableViewAlumno.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (tableViewAlumno.getSelectionModel().getSelectedItem() != null) {
                    ManejadorAlumno.getMANEJADOR_ALUMNO().setAlumno(tableViewAlumno.getSelectionModel().getSelectedItem());
                }
            }
        });
        gridPane.add(tableViewAlumno, 3, 5);
        
        CreateAlumno createAlumno = new CreateAlumno();
        
        hBox = new HBox();
        hBox.getChildren().addAll(gridPane, createAlumno.getContent());
        
        return hBox;
    }
    
    public void refrescarLista() {
        observableListAlumno.clear();
        observableListAlumno = FXCollections.observableArrayList(AsistenteConsulta.getASISTENTE_CONSULTA().consulta("FROM Alumno"));
        tableViewAlumno.getItems().clear();
        tableViewAlumno.setItems(observableListAlumno);
    }
    
    public class UpdateAlumno {
        private GridPane gridPaneCreate;
        
        private Label labelGradoSeccion;
        private TextField textFieldGradoSeccion;
        private Label labelNombre;
        private TextField textFieldNombre;
        private Label labelApellido;
        private TextField textFieldApellido;
        private ComboBox<Gradoseccion> comboBoxGradoSeccion;
        
        private Button buttonModificar;

        public UpdateAlumno() {
        }
        
        public Node getContent() {
            gridPaneCreate = new GridPane();
            gridPaneCreate.setId("gridPaneCatalogo");
            gridPaneCreate.setHgap(10);
            gridPaneCreate.setVgap(10);
            //gridPane.setGridLinesVisible(true);
            gridPaneCreate.setPadding(new Insets(25, 25, 25, 25));
            
            text = new Text("Agregar Alumno");
            text.setId("textTitulo");
            gridPaneCreate.add(text, 0, 0, 2, 1);
            
            labelGradoSeccion = new Label("Grado sección:");
            gridPaneCreate.add(labelGradoSeccion, 1, 1);
            
            ObservableList<Gradoseccion> observableList = FXCollections.observableArrayList(AsistenteConsulta.getASISTENTE_CONSULTA().consulta("From Gradoseccion"));
            
            comboBoxGradoSeccion = new ComboBox<>(observableList);
            comboBoxGradoSeccion.getSelectionModel().select(ManejadorAlumno.getMANEJADOR_ALUMNO().getAlumno().getGradoseccion());
            gridPaneCreate.add(comboBoxGradoSeccion, 2, 1);
            
            labelNombre = new Label("Nombre:");
            gridPaneCreate.add(labelNombre, 1, 2);
            
            textFieldNombre = new TextField(ManejadorAlumno.getMANEJADOR_ALUMNO().getAlumno().getNombre());
            gridPaneCreate.add(textFieldNombre, 2, 2);
            
            labelApellido = new Label("Apellido:");
            gridPaneCreate.add(labelApellido, 1, 3);
            
            textFieldApellido = new TextField(ManejadorAlumno.getMANEJADOR_ALUMNO().getAlumno().getApellido());
            gridPaneCreate.add(textFieldApellido, 2, 3);
            
            buttonModificar = new Button("Modificar");
            buttonModificar.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (textFieldNombre.getText() != null && textFieldApellido.getText() != null) {
                        Alumno obj = ManejadorAlumno.getMANEJADOR_ALUMNO().getAlumno();
                        obj.setGradoseccion(comboBoxGradoSeccion.getValue());
                        obj.setNombre(textFieldNombre.getText());
                        obj.setApellido(textFieldApellido.getText());
                        ManejadorAlumno.getMANEJADOR_ALUMNO().modificar(obj);
                        refrescarLista();
                    }
                    
                }
            });
            
            gridPaneCreate.add(buttonModificar, 1, 4);
            
            return gridPaneCreate;
            
        }
    
    }
    
    public class CreateAlumno {
        private GridPane gridPaneCreate;
        
        private Label labelGradoSeccion;
        private TextField textFieldGradoSeccion;
        private Label labelNombre;
        private TextField textFieldNombre;
        private Label labelApellido;
        private TextField textFieldApellido;
        private ComboBox<Gradoseccion> comboBoxGradoSeccion;
        
        private Button buttonAgregar;

        public CreateAlumno() {
        }
        
        public Node getContent() {
            gridPaneCreate = new GridPane();
            gridPaneCreate.setId("gridPaneCatalogo");
            gridPaneCreate.setHgap(10);
            gridPaneCreate.setVgap(10);
            //gridPane.setGridLinesVisible(true);
            gridPaneCreate.setPadding(new Insets(25, 25, 25, 25));
            
            text = new Text("Agregar Alumno");
            text.setId("textTitulo");
            gridPaneCreate.add(text, 0, 0, 2, 1);
            
            labelGradoSeccion = new Label("Grado sección:");
            gridPaneCreate.add(labelGradoSeccion, 1, 1);
            
            ObservableList<Gradoseccion> observableList = FXCollections.observableArrayList(AsistenteConsulta.getASISTENTE_CONSULTA().consulta("From Gradoseccion"));
            
            comboBoxGradoSeccion = new ComboBox<>(observableList);
            comboBoxGradoSeccion.getSelectionModel().selectFirst();
            gridPaneCreate.add(comboBoxGradoSeccion, 2, 1);
            
            labelNombre = new Label("Nombre:");
            gridPaneCreate.add(labelNombre, 1, 2);
            
            textFieldNombre = new TextField();
            gridPaneCreate.add(textFieldNombre, 2, 2);
            
            labelApellido = new Label("Apellido:");
            gridPaneCreate.add(labelApellido, 1, 3);
            
            textFieldApellido = new TextField();
            gridPaneCreate.add(textFieldApellido, 2, 3);
            
            buttonAgregar = new Button("Agregar");
            buttonAgregar.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if (textFieldNombre.getText() != null && textFieldApellido.getText() != null) {
                        ManejadorAlumno.getMANEJADOR_ALUMNO().agregar(comboBoxGradoSeccion.getSelectionModel().getSelectedItem(),
                            textFieldNombre.getText(), textFieldApellido.getText());
                        refrescarLista();
                    }
                    
                }
            });
            
            gridPaneCreate.add(buttonAgregar, 1, 4);
            
            return gridPaneCreate;
            
        }
        
    }
    
}
