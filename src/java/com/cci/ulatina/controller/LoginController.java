/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.ulatina.controller;

import com.cci.manage.Empleados;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.cci.manage.*;
import com.cci.ulatina.servicio.Servicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "loginController")
@SessionScoped

public class LoginController {
    
    //HOLA GENTEEEEE
    private int correo;
    private int clave;
    
    
    public void login() {
        try {
          
            Servicio test = new Servicio();
            test.startEntityManagerFactory();
            
            
            
            /*Empleados empleado = new Empleados();
            empleado.setApellido("Gay");
            empleado.setCedulaa(1323);
            empleado.setNombre("ARTURO");
            empleado.setDireccion("Santa Ana");
            empleado.setTelefono(12345);
            empleado.setVacaciones(123);
            empleado.setId(2);
            EmpleadoService test1 = new EmpleadoService();
           // test1.insertar(test.em, empleado);*/
            
            test.stopEntityManagerFactory();
            System.out.println("Done");
            
            this.redirect("/faces/collaborator.xhtml");
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    public int getCorreo() {
        return correo;
    }

    public void setCorreo(int correo) {
        this.correo = correo;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
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
