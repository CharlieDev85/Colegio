package org.ugalileo.bean;
// Generated 12/02/2015 09:33:19 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Alumno generated by hbm2java
 */
public class Alumno  implements java.io.Serializable {


     private Integer idAlumno;
     private Gradoseccion gradoseccion;
     private String nombre;
     private String apellido;
     private Set auxmateriaalumnos = new HashSet(0);

    public Alumno() {
    }

	
    public Alumno(Gradoseccion gradoseccion, String nombre, String apellido) {
        this.gradoseccion = gradoseccion;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public Alumno(Gradoseccion gradoseccion, String nombre, String apellido, Set auxmateriaalumnos) {
       this.gradoseccion = gradoseccion;
       this.nombre = nombre;
       this.apellido = apellido;
       this.auxmateriaalumnos = auxmateriaalumnos;
    }
   
    public Integer getIdAlumno() {
        return this.idAlumno;
    }
    
    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }
    public Gradoseccion getGradoseccion() {
        return this.gradoseccion;
    }
    
    public void setGradoseccion(Gradoseccion gradoseccion) {
        this.gradoseccion = gradoseccion;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public Set getAuxmateriaalumnos() {
        return this.auxmateriaalumnos;
    }
    
    public void setAuxmateriaalumnos(Set auxmateriaalumnos) {
        this.auxmateriaalumnos = auxmateriaalumnos;
    }




}

