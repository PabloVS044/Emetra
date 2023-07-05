/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pablovasquez.bean;

/**
 *
 * @author pvasq
 */
public class Vecino {
    
    private String NIT;
    private int DPI;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String municipalidad;
    private int codigoPostal;
    private String telefono;
    
    public Vecino(){
        
    }

    public Vecino(String NIT, int DPI, String nombres, String apellidos, String direccion, String municipalidad, int codigoPostal, String telefono) {
        this.NIT = NIT;
        this.DPI = DPI;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.municipalidad = municipalidad;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
    }

    public Vecino(String string, int aInt, String string0, String string1, String string2, String string3, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public int getDPI() {
        return DPI;
    }

    public void setDPI(int DPI) {
        this.DPI = DPI;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipalidad() {
        return municipalidad;
    }

    public void setMunicipalidad(String municipalidad) {
        this.municipalidad = municipalidad;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
}
