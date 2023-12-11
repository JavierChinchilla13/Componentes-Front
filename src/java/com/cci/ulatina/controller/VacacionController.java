/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.ulatina.controller;

import com.cci.manage.Empleados;
import com.cci.manage.TesterTablas;
import com.cci.manage.Vacaciones;
import com.cci.manage.VacacionesService;
import com.cci.ulatina.servicio.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author javi
 */

@ManagedBean(name = "vacacionController")
@SessionScoped
public class VacacionController {
    
    private Vacaciones vacacionSelected = new Vacaciones();
    private final VacacionesService sVService = new VacacionesService();
    Servicio test = new Servicio();
    private int EmpleadoId; 
    
    private boolean esNuevo = false;

    public Vacaciones getVacacionSelected() {
        return vacacionSelected;
    }
    
     public void openNew() {
        this.esNuevo = true;
        this.vacacionSelected = new Vacaciones();
    }

    public void setVacacionSelected(Vacaciones vacacionSelected) {
        this.vacacionSelected = vacacionSelected;
    }

    public Servicio getTest() {
        return test;
    }

    public void setTest(Servicio test) {
        this.test = test;
    }

    public int getEmpleadoId() {
        return EmpleadoId;
    }

    public void setEmpleadoId(int EmpleadoId) {
        this.EmpleadoId = EmpleadoId;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }


    
    public List<Vacaciones> getVacaciones() {
        try {
            Empleados p =  new Empleados();
			p.setId(new Integer("11"));
			p.setNombre("CCI");

            test.startEntityManagerFactory();
            
                  
			
			System.out.println("-----------------------");
                       //testers.findPK1(p);
                       for(Vacaciones pro:  VacacionesService.findPK1(test.em, p)) {
				System.out.println("Nombre: " + pro.getIdVacaciones());
			}
            test.stopEntityManagerFactory();
            System.out.println("Done");

            return VacacionesService.findPK1(test.em, p);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving th list of employees"));

        }
        List<Vacaciones> list = new ArrayList<>();
        return list;
    }
    
    
    public void saveVacacion() throws Exception {
        
        Empleados p = new Empleados();
            p.setId(new Integer("11"));
            p.setNombre("CCI");
            
        
        vacacionSelected.setPersona(p);
        test.startEntityManagerFactory();

        boolean flag = true;
        if (this.vacacionSelected.getFech_Inicio() == null || this.vacacionSelected.getFech_Inicio().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar fecha inicial"));
            flag = false;
        }
        if (this.vacacionSelected.getFech_Final() == null || this.vacacionSelected.getFech_Final().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar fecha final"));
            flag = false;
        }
        

        if (flag) {

            

            System.out.println("Estoy salvando al usuario");
            
            test.em.getTransaction().begin();
            test.em.persist(this.vacacionSelected);
            test.em.getTransaction().commit();
            
            //this.sVService.insertar(test.em, this.vacacionSelected);
            this.esNuevo = false;
            this.vacacionSelected = new Vacaciones();
            

            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }
 
        test.stopEntityManagerFactory();
        System.out.println("YAAAA");
    }
    
    
    public void reviewVacacion() throws Exception {
     
        test.startEntityManagerFactory();

        boolean flag = true;
       
        
        

        if (flag) {


            
            this.sVService.modificar(test.em, this.vacacionSelected);

            System.out.println("Estoy salvando al usuario");

            this.esNuevo = false;
            this.vacacionSelected = new Vacaciones();
            

            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }
 
        test.stopEntityManagerFactory();
        System.out.println("YAAAA");
    }


    public java.util.Date getCalendarInicio() {
        return (java.util.Date) this.vacacionSelected.getFech_Inicio();
    }

    public void setCalendarInicio(java.util.Date fireDate) {
        if (fireDate != null) {
            this.vacacionSelected.setFech_Inicio(new java.sql.Date(fireDate.getTime()));
        }
    }
    
    public java.util.Date getCalendarFinal() {
        return (java.util.Date) this.vacacionSelected.getFech_Final();
    }

    public void setCalendarFinal(java.util.Date fireDate) {
        if (fireDate != null) {
            this.vacacionSelected.setFech_Final(new java.sql.Date(fireDate.getTime()));
        }
    } 
    
}
