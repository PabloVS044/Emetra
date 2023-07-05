/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pablovasquez.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.pablovasquez.bean.Vecino;
import org.pablovasquez.db.Conexion;
import org.pablovasquez.system.Principal;


public class VecinosController implements Initializable{
    private Principal escenarioPrincipal;
    private enum operaciones{NUEVO, GUARDAR, ELIMINAR, EDITAR, ACTUALIZAR, NINGUNO};
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    private ObservableList<Vecino> listaVecino;
    
    
    @FXML private Button btnAgregar;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;
    @FXML private TableColumn colNIT;
    @FXML private TableColumn colDPI;
    @FXML private TableColumn colNombres;
    @FXML private TableColumn colApellidos;
    @FXML private TableColumn colDireccion;
    @FXML private TableColumn colMunicipalidad;
    @FXML private TableColumn colCodigoPostal;
    @FXML private TableColumn colTelefono;
    @FXML private TableView tblVecinos;
    @FXML private TextField txtNIT;
    @FXML private TextField txtDPI;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtDireccion;
    @FXML private TextField txtMunicipalidad;
    @FXML private TextField txtCodigoPostal;
    @FXML private TextField txtTelefono;

    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void cargarDatos(){
        tblVecinos.setItems(getVecino());
        colNIT.setCellValueFactory(new PropertyValueFactory<Vecino, String>("NIT"));
        colDPI.setCellValueFactory(new PropertyValueFactory<Vecino, Integer>("DPI"));
        colNombres.setCellValueFactory(new PropertyValueFactory<Vecino, String>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Vecino, String>("apellidos"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Vecino, String>("direccion"));
        colMunicipalidad.setCellValueFactory(new PropertyValueFactory<Vecino, String>("municipalidad"));
        colCodigoPostal.setCellValueFactory(new PropertyValueFactory<Vecino, Integer>("codigoPostal"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Vecino, String>("telefono"));
    }
    
    public ObservableList<Vecino> getVecino(){
        ArrayList<Vecino> lista = new ArrayList<Vecino>();
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarVecinos}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Vecino(resultado.getString("NIT"),
                                     resultado.getInt("DPI"),
                                     resultado.getString("nombres"),
                                     resultado.getString("apellidos"),
                                     resultado.getString("direccion"),
                                     resultado.getString("municipalidad"),
                                     resultado.getInt("codigoPostal"),
                                     resultado.getString("telefono")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaVecino = FXCollections.observableArrayList(lista);
    }
    
    
    public void seleccionarElemento(){
        if (tblVecinos.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "No hay datos disponibles");
        }else{
            txtNIT.setText(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getNIT());
            txtDPI.setText(String.valueOf(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getDPI()));
            txtNombres.setText(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getNombres());
            txtApellidos.setText(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getApellidos());
            txtDireccion.setText(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getDireccion());
            txtMunicipalidad.setText(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getMunicipalidad());
            txtCodigoPostal.setText(String.valueOf(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getCodigoPostal()));
            txtTelefono.setText(((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getTelefono());
        }
    }
    
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                btnAgregar.setText("Guardar");
                btnEliminar.setText("Cancelar");
                tipoDeOperacion = operaciones.GUARDAR;
            break;
            
            case GUARDAR:
                guardar();
                btnAgregar.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
            break;
        }
    }
    
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                btnAgregar.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
            break;
            
            default:
                if(tblVecinos.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null, "Está seguro de elminar el registro?", "Eliminar Vecino",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respuesta == JOptionPane.YES_OPTION){
                        try{
                            com.mysql.jdbc.PreparedStatement procedimiento = (com.mysql.jdbc.PreparedStatement) Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarVecino(?)}");
                            procedimiento.setString(1, ((Vecino)tblVecinos.getSelectionModel().getSelectedItem()).getNIT());
                            procedimiento.execute();
                            listaVecino.remove(tblVecinos.getSelectionModel().getSelectedIndex());
                            //limpiarControles();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    } else{
                        //limpiarControles();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
                }
                break;
        }
    }
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if(tblVecinos.getSelectionModel() != null){
                    btnEditar.setText("Actualizar");
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                }else{
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado un elemento valido");
                }
                break;
                
            case ACTUALIZAR:
                actualizar();
                btnEditar.setText("Editar");
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void guardar(){
        Vecino registro = new Vecino();
        registro.setNIT(txtNIT.getText());
        registro.setDPI(Integer.parseInt(txtDPI.getText()));
        registro.setNombres(txtNombres.getText());
        registro.setApellidos(txtApellidos.getText());
        registro.setDireccion(txtDireccion.getText());
        registro.setMunicipalidad(txtMunicipalidad.getText());
        registro.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
        registro.setTelefono(txtTelefono.getText());
        try{
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarVecino(?, ?, ?, ?, ?, ?, ?, ?)}");
            procedimiento.setString(1, registro.getNIT());
            procedimiento.setInt(2, registro.getDPI());
            procedimiento.setString(3, registro.getNombres());
            procedimiento.setString(4, registro.getApellidos());
            procedimiento.setString(5, registro.getDireccion());
            procedimiento.setString(6, registro.getMunicipalidad());
            procedimiento.setInt(7, registro.getCodigoPostal());
            procedimiento.setString(8, registro.getTelefono());
            procedimiento.execute();
            listaVecino.add(registro);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstance().getConexion().prepareCall("{call sp_EditarVecino(?, ?, ?, ?, ?, ?, ?, ?)}");
            Vecino registro = (Vecino)tblVecinos.getSelectionModel().getSelectedItem();
            registro.setDPI(Integer.parseInt(txtDPI.getText()));
            registro.setNombres(txtNombres.getText());
            registro.setApellidos(txtApellidos.getText());
            registro.setDireccion(txtDireccion.getText());
            registro.setMunicipalidad(txtMunicipalidad.getText());
            registro.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText()));
            registro.setTelefono(txtTelefono.getText());
            procedimiento.setString(1, registro.getNIT());
            procedimiento.setInt(2, registro.getDPI());
            procedimiento.setString(3, registro.getNombres());
            procedimiento.setString(4, registro.getApellidos());
            procedimiento.setString(5, registro.getDireccion());
            procedimiento.setString(6, registro.getMunicipalidad());
            procedimiento.setInt(7, registro.getCodigoPostal());
            procedimiento.setString(8, registro.getTelefono());
            procedimiento.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }

    
    
}
