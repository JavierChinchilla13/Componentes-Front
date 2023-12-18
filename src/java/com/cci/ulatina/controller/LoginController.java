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
    private String correo;
    private String clave;
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

    public void ingresar() {
        try {
            Servicio test = new Servicio();
            EmpleadoService servEmp = new EmpleadoService();

            test.startEntityManagerFactory(); // Inicia el EntityManager
            String permisos = servEmp.Credenciales(test.em, this.correo, this.clave);
            test.stopEntityManagerFactory(); // Detiene el EntityManager después de su uso

            boolean flag = true;
            if (this.getCorreo() == null || this.getCorreo().equals("")) {
                //ERROR
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The Email is empty"));
                flag = false;
            }
            if (this.getClave() == null || this.getClave().equals("")) {
                //ERROR
                FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "The password is empty"));
                flag = false;
            }

            // Aquí puedes manejar el valor de 'permisos' según tu lógica de frontend
            if (permisos.equals(false)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales incorrectas"));
            } else if (permisos.equals("Administrador")) {
                this.redirect("/faces/collaborator.xhtml");
            } else {
                this.redirect("/faces/projects.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Manejo adecuado del error según la lógica de tu aplicación
        }
    }

    public void saveTrabajador() throws Exception {
        Servicio test = new Servicio();
        EmpleadoService u = new EmpleadoService();

        test.startEntityManagerFactory();
        u.insertar(test.em, selectedUsuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Trabajador agregado"));

    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
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
