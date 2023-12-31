/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cci.ulatina.controller;

import com.cci.manage.AdminService;
import com.cci.manage.EmpleadoService;
import com.cci.manage.Empleados;
import com.cci.manage.Vacaciones;
import com.cci.manage.VacacionesService;
import com.cci.ulatina.servicio.Servicio;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.PrimeFaces;

/**
 *
 * @author javi
 */
@ManagedBean(name = "employeeController")
@SessionScoped
public class CollaboratorController implements Serializable {

    private Empleados em;
    private final EmpleadoService service = new EmpleadoService();
    private final AdminService adminService = new AdminService();
    private Empleados selectedEmployee = new Empleados();
    private boolean esNuevo = false;
    

    Servicio test = new Servicio();

    public void openNew() {
        this.esNuevo = true;
        this.selectedEmployee = new Empleados();
    }

    public Empleados getEm() {
        return em;
    }

    public void setEm(Empleados em) {
        this.em = em;
    }

    public Empleados getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Empleados selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
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


   
    

    public List<Empleados> getEmployees() {
        try {

            test.startEntityManagerFactory();

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

    public void saveColaborador() throws Exception {

        test.startEntityManagerFactory();

        boolean flag = true;
        if (this.selectedEmployee.getNombre() == null || this.selectedEmployee.getNombre().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar nombre"));
            flag = false;
        }
        if (this.selectedEmployee.getApellido() == null || this.selectedEmployee.getApellido().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar apellido"));
            flag = false;
        }
        /*if (this.selectedEmployee.getCumpleaños() == null || this.selectedEmployee.getCumpleaños().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar cumpleaños"));
            flag = false;
        }*/
 /*if (this.selectedEmployee.getTelefono() == null || this.selectedEmployee.getTelefono().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar telefono"));
            flag = false;
        }
        if (this.selectedEmployee.getDireccion() == null || this.selectedEmployee.getDireccion().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar direccion"));
            flag = false;
        }*/
        if (this.selectedEmployee.getCedula() == null || this.selectedEmployee.getCedula().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar cedula"));
            flag = false;
        }

        if (this.selectedEmployee.getCorreo() == null || this.selectedEmployee.getCorreo().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar correo"));
            flag = false;
        }

        if (!checkEmail(this.selectedEmployee.getCorreo())) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar correo"));
            flag = false;
        }
        if (this.selectedEmployee.getPassword() == null || this.selectedEmployee.getPassword().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar password"));
            flag = false;
        }
        if (checkPassword(this.selectedEmployee.getPassword())) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Password format is incorrect", "The password must contain 4 to 8 characters and must contain numbers, lowercase and uppercase letters."));
            flag = false;
        }
        if (this.selectedEmployee.getFechaIngreso() == null || this.selectedEmployee.getFechaIngreso().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar fecha de ingreso"));
            flag = false;
        }
        /*if (this.selectedEmployee.getFechaRetiro() == null || this.selectedEmployee.getFechaRetiro().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar fecha de retiro"));
            flag = false;
        }*/
        if (this.selectedEmployee.getEstado() == null || this.selectedEmployee.getEstado().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar estado"));
            flag = false;
        }
        if (this.selectedEmployee.getTipo() == null || this.selectedEmployee.getTipo().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar tipo"));
            flag = false;
        }

        if (flag) {

            System.out.println("Estoy salvando al usuario");

            if (this.selectedEmployee.getTipo().equals("Administrador")) {
                this.adminService.insertar(test.em, this.selectedEmployee);
                this.esNuevo = false;
            } else {
                this.service.insertar(test.em, this.selectedEmployee);
                this.esNuevo = false;
            }
            this.selectedEmployee = new Empleados();

            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

        test.stopEntityManagerFactory();
        System.out.println("YAAAA");
    }

    public void updateColaborador() throws Exception {

        test.startEntityManagerFactory();

        boolean flag = true;

        if (this.selectedEmployee.getNombre() == null || this.selectedEmployee.getNombre().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar nombre"));
            flag = false;
        }
        if (this.selectedEmployee.getApellido() == null || this.selectedEmployee.getApellido().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar apellido"));
            flag = false;
        }
        /*if (this.selectedEmployee.getCumpleaños() == null || this.selectedEmployee.getCumpleaños().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar cumpleaños"));
            flag = false;
        }*/
 /*if (this.selectedEmployee.getTelefono() == null || this.selectedEmployee.getTelefono().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar telefono"));
            flag = false;
        }
        if (this.selectedEmployee.getDireccion() == null || this.selectedEmployee.getDireccion().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar direccion"));
            flag = false;
        }*/
        if (this.selectedEmployee.getCedula() == null || this.selectedEmployee.getCedula().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar cedula"));
            flag = false;
        }

        if (this.selectedEmployee.getCorreo() == null || this.selectedEmployee.getCorreo().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar correo"));
            flag = false;
        }

        if (!checkEmail(this.selectedEmployee.getCorreo())) {
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar correo"));
            flag = false;
        }
        if (this.selectedEmployee.getPassword() == null || this.selectedEmployee.getPassword().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar password"));
            flag = false;
        }
        if (checkPassword(this.selectedEmployee.getPassword())) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: Password format is incorrect", "The password must contain 4 to 8 characters and must contain numbers, lowercase and uppercase letters."));
            flag = false;
        }
        if (this.selectedEmployee.getFechaIngreso() == null || this.selectedEmployee.getFechaIngreso().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar fecha de ingreso"));
            flag = false;
        }
        /*if (this.selectedEmployee.getFechaRetiro() == null || this.selectedEmployee.getFechaRetiro().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar fecha de retiro"));
            flag = false;
        }*/
        if (this.selectedEmployee.getEstado() == null || this.selectedEmployee.getEstado().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar estado"));
            flag = false;
        }
        if (this.selectedEmployee.getTipo() == null || this.selectedEmployee.getTipo().equals("")) {
            //ERROR
            FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "Insertar tipo"));
            flag = false;
        }

        if (flag) {

            this.service.modificar(test.em, this.selectedEmployee);

            System.out.println("Estoy salvando al usuario");
            //this.service.modificar(test.em, this.selectedEmployee);
            this.esNuevo = false;
            this.selectedEmployee = new Empleados();

            PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        }

        //test.em.getTransaction().commit();
        test.stopEntityManagerFactory();
        System.out.println("YAAAA");
    }

    public void deleteUser(int id) throws Exception {

        test.startEntityManagerFactory();
        test.em = test.entityManagerFactory.createEntityManager();
        test.em.getTransaction().begin();

        Empleados localizado = test.em.find(Empleados.class, new Integer(id));
        if (localizado != null) {
            System.out.println("Se localizo el empleado: " + localizado.getNombre());
        } else {
            System.out.println("No se encontro empleado");

        }

        localizado.setEstado("Inactivo");

        test.em.merge(localizado);

        this.esNuevo = false;
        this.selectedEmployee = new Empleados();
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");

        test.em.getTransaction().commit();
        test.stopEntityManagerFactory();

    }

    public boolean checkEmail(String email) {
        String regex = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean checkPassword(String pass) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }

    /*public java.util.Date getCalendarCumple() {
        return (java.util.Date) this.selectedEmployee.getCumpleaños();
    }

    public void setCalendarCumple(java.util.Date fireDate) {
        if (fireDate != null) {
            this.selectedEmployee.setCumpleaños(new java.sql.Date(fireDate.getTime()));
        }
    }*/
    public java.util.Date getCalendarIngreso() {
        return (java.util.Date) this.selectedEmployee.getFechaIngreso();
    }

    public void setCalendarIngreso(java.util.Date fireDate) {
        if (fireDate != null) {
            this.selectedEmployee.setFechaIngreso(new java.sql.Date(fireDate.getTime()));
        }
    }

    public java.util.Date getCalendarRetiro() {
        return (java.util.Date) this.selectedEmployee.getFechaRetiro();
    }

    public void setCalendarretiro(java.util.Date fireDate) {
        if (fireDate != null) {
            this.selectedEmployee.setFechaRetiro(new java.sql.Date(fireDate.getTime()));
        }
    }

    public void redireccionar(String ruta) {
        HttpServletRequest request;
        try {
            request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + ruta);
        } catch (Exception e) {
        }
    }

    public String idToStrStatus(int id) throws Exception {

        EmpleadoService serv = new EmpleadoService();
        Empleados test2 = serv.buscarPK(test.em, id);
        this.esNuevo = false;
        this.selectedEmployee = new Empleados();
        String result = (test2.getNombre() + " " + test2.getApellido());
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");

        return result;
    }

    public int Vacaciones(int id) throws Exception {

        test.startEntityManagerFactory();
        test.em = test.entityManagerFactory.createEntityManager();
        test.em.getTransaction().begin();

        Empleados localizado = test.em.find(Empleados.class, new Integer(id));
        if (localizado != null) {
            System.out.println("Se localizo el empleado: " + localizado.getNombre());
        } else {
            System.out.println("No se encontro empleado");

        }
        VacacionesService v = new VacacionesService();

        localizado.setVacaciones(v.calculateVacationDays(test.em, id));
        test.em.merge(localizado);
        int dias = v.calculateVacationDays(test.em, 13);
        this.esNuevo = false;
        this.selectedEmployee = new Empleados();
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");

        test.em.getTransaction().commit();
        test.stopEntityManagerFactory();

        return dias;

    }

    public int Vacaciones2(int id) throws Exception {

        test.startEntityManagerFactory();
        test.em = test.entityManagerFactory.createEntityManager();
        test.em.getTransaction().begin();

        int totalVacationDays = 0;

        Empleados localizado = test.em.find(Empleados.class, new Integer(id));
        if (localizado != null) {
            System.out.println("Se localizo el empleado: " + localizado.getNombre());
            
        } else {
            System.out.println("No se encontro empleado");

        }
        VacacionesService v = new VacacionesService();
        //Vacaciones va = new Vacaciones();

        List<Vacaciones> lista = v.findVacacion(test.em, localizado);
        for (Vacaciones pro : lista) {
            //System.out.println("Nombre: " + pro.getFech_Inicio());		
            LocalDate startLocalDate = pro.getFech_Inicio().toLocalDate();
            LocalDate endLocalDate = pro.getFech_Final().toLocalDate();
            long days = ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
            System.out.println("+++++" + days);
            totalVacationDays += days;
        }

        int days = v.calculateVacationDays(test.em, id);

        localizado.setVacaciones(days - totalVacationDays);
        test.em.merge(localizado);
        int totalVacaciones = days - totalVacationDays;
        //int dias = v.calculateVacationDays(test.em, 13);
        this.esNuevo = false;
        this.selectedEmployee = new Empleados();
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");

        test.em.getTransaction().commit();
        test.stopEntityManagerFactory();

        return totalVacaciones;

    }
}
