/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.ulatina.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.cci.manage.*;
import com.cci.ulatina.servicio.Servicio;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController {

    //Atributos
    private int contraseña;
    private int clave;
    private int telefono;
    private String nombre;
    private String apellido;
    private String tipo;
    private Date cumpleaños;
    private String dirección;
    

    private Empleados selectedUsuario;

    public LoginController() {
    }
    
    public void openNew() {
        this.selectedUsuario = new Empleados();
    }

    public void login() {
        try {

            Servicio test = new Servicio();
            test.startEntityManagerFactory();

            Empleados empleado = new Empleados();
            empleado.setApellido("Gay");

            empleado.setNombre("ARTURO");

            empleado.setTelefono(12345);
            empleado.setVacaciones(123);
            empleado.setId(2);
            EmpleadoService test1 = new EmpleadoService();
            test1.insertar(test.em, empleado);

            test.stopEntityManagerFactory();
            System.out.println("Done");

            this.redirect("/faces/collaborator.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveTrabajador() throws Exception {
        Servicio test = new Servicio();
        TrabajadorService u = new TrabajadorService();
        
        test.startEntityManagerFactory();
        u.insertar(test.em,selectedUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Trabajador agregado"));

    }

    public int getContraseña() {
        return contraseña;
    }

    public void setContraseña(int contraseña) {
        this.contraseña = contraseña;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getCumpleaños() {
        return cumpleaños;
    }

    public void setCumpleaños(Date cumpleaños) {
        this.cumpleaños = cumpleaños;
    }

    public String getDirección() {
        return dirección;
    }

    public void setDirección(String dirección) {
        this.dirección = dirección;
    }

    public Empleados getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Empleados selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public void redirect(String rute) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + rute);
        } catch (Exception e) {

        }
    }

}
