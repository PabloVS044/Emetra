/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pablovasquez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.pablovasquez.system.Principal;

/**
 *
 * @author informatica
 */
public class MenuPrincipalController implements Initializable {
    private Principal escenarioPrincipal;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaVecinos(){
        escenarioPrincipal.ventanaVecinos();
    }
    
    public void ventanaVehiculos(){
        escenarioPrincipal.ventanaVehiculos();
    }
    
    public void close(){
        System.exit(0);
    }
    
}    