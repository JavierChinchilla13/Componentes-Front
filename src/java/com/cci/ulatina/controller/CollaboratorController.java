/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.ulatina.controller;


import com.cci.manage.EmpleadoService;
import com.cci.manage.Empleados;
import com.cci.ulatina.servicio.Servicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;




/**
 *
 * @author javi
 */
@ManagedBean(name = "employeeController")
@SessionScoped
public class CollaboratorController implements Serializable {
    
     private Empleados em;
    private final EmpleadoService service = new EmpleadoService();
    private Empleados selectedEmpleado = new Empleados();
    Servicio test = new Servicio();
    
    public List<Empleados> getEmployees() {
        try {
            
             test.startEntityManagerFactory();
            
            for(Empleados p: service.listar(test.em)) {
				System.out.println("Nombre: " + p.getNombre());
			}
            
                
            test.stopEntityManagerFactory();
            System.out.println("Done");
            
            return service.listar(test.em);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving th list of employees"));

        }
        List<Empleados> list = new ArrayList<>();
        return list;
    }
    
}
