/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.ulatina.controller;

import com.cci.manage.DetallesProyecto;
import com.cci.manage.DetallesProyectoServicio;
import com.cci.manage.EmpleadoService;
import com.cci.manage.Empleados;
import com.cci.manage.ProyectoServicio;
import com.cci.ulatina.servicio.Servicio;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.cci.manage.Projecto;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Jose
 */
@ManagedBean(name = "projectController")
@SessionScoped
public class ProjectControler {

    private Projecto em;
    private final ProyectoServicio service = new ProyectoServicio();
    private Projecto selectedProject = new Projecto();
    private boolean esNuevo = false;
    Servicio test = new Servicio();
    private DetallesProyecto selectedDetalle = new DetallesProyecto();
    private EmpleadoService se = new EmpleadoService();
    private String rol;
    private int idprol;
    
    public DetallesProyecto getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(DetallesProyecto selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    

    public Projecto getEm() {
        return em;
    }

    public void setEm(Projecto em) {
        this.em = em;
    }

    public Projecto getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Projecto selectedProject) {
        this.selectedProject = selectedProject;
    }

    public boolean isEsNuevo() {
        return esNuevo;
    }

    public void setEsNuevo(boolean esNuevo) {
        this.esNuevo = esNuevo;
    }

    public Servicio getTest() {
        return test;
    }

    public void setTest(Servicio test) {
        this.test = test;
    }

    public void openNew() {
        this.esNuevo = true;
        this.selectedProject = new Projecto();
    }
    
    public void idPr(Projecto pr){
        idprol = pr.getId();
        
        
    }

    public void saveProject() throws Exception {
        test.startEntityManagerFactory();
        System.out.println("Proyecto Aqui estas" + this.selectedProject.getNombreProyecto() + this.selectedProject.getDescripcion() + this.selectedProject.getId());
        

        boolean flag = true;
        if (this.selectedProject.getNombreProyecto() == null || this.selectedProject.getNombreProyecto().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar nombre"));
            flag = false;

            
        }
        if (flag) {
                idprol = this.selectedProject.getId();

                System.out.println("Estoy salvando al projecto");
                this.service.insertar(test.em, this.selectedProject);
                this.esNuevo = false;
                this.selectedProject = new Projecto();

                PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
            }

            test.stopEntityManagerFactory();
            System.out.println("Se salvo el projecto");

    }
    public void deleteProject(int id) throws Exception {

        test.startEntityManagerFactory();
        test.em = test.entityManagerFactory.createEntityManager();
        test.em.getTransaction().begin();

        Projecto localizado = test.em.find(Projecto.class, new Integer(id));
        if (localizado != null) {
            System.out.println("Se localizo el proyecto: " + localizado.getNombreProyecto());
        } else {
            System.out.println("No se encontro empleado");

        }

        localizado.setEstado("Inactivo");

        test.em.merge(localizado);

        this.esNuevo = false;
        this.selectedProject = new Projecto();
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");

        test.em.getTransaction().commit();
        test.stopEntityManagerFactory();

    }
    
    public List<Projecto> getEmployees() {
        try {

            test.startEntityManagerFactory();

            test.stopEntityManagerFactory();
            System.out.println("Done");

            return service.listar(test.em);

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Error in retriving th list of employees"));

        }
        List<Projecto> list = new ArrayList<>();
        return list;
    }
    
    public void updateColaborador() throws Exception {

        test.startEntityManagerFactory();

        boolean flag = true;

        if (this.selectedProject.getNombreProyecto() == null || this.selectedProject.getNombreProyecto().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar nombre"));
            flag = false;
        }      

        if (flag) {

            this.service.modificar(test.em, this.selectedProject);

            System.out.println("Estoy actualizando al proyecto");
            //this.service.modificar(test.em, this.selectedEmployee);
            this.esNuevo = false;
            this.selectedProject = new Projecto();

            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

        //test.em.getTransaction().commit();
        test.stopEntityManagerFactory();
        System.out.println("YAAAA");
    }
    
    public void agregarColab(Empleados es) throws Exception {
        test.startEntityManagerFactory();
        int idEmp = es.getId();
       
       DetallesProyectoServicio servicioDetalle = new DetallesProyectoServicio();
       
        test.stopEntityManagerFactory();
        try {
             servicioDetalle.insert(test.em, idEmp, idprol, "Recursos Humanos");
             

        } catch (Exception ex) {
            // Maneja el error si la inserción falla
            ex.printStackTrace();
        }

    }
    
     public List<Empleados> getColaboradores() {
        List<Empleados> colaboradores = null;

        try {
            test.startEntityManagerFactory();

            // Llamar al método de tu servicio para obtener empleados por permiso
            colaboradores = se.obtenerEmpleadosPorPermiso(test.em, "Colaborador");

            test.stopEntityManagerFactory();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al obtener colaboradores", "Ha ocurrido un error al obtener la lista de colaboradores"));
        }

        if (colaboradores == null) {
            colaboradores = new ArrayList<>(); // Manejar el caso de que no se obtengan colaboradores
        }

        return colaboradores;
    }
     
     
     
    
    

}
