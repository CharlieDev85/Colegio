/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ugalileo.manejador;

import org.ugalileo.bean.Alumno;
import org.ugalileo.bean.Gradoseccion;
import org.ugalileo.db.AsistenteConsulta;

/**
 *
 * @author Claudio Danilo Canel
 */
public class ManejadorAlumno {
    private static final ManejadorAlumno MANEJADOR_ALUMNO = new ManejadorAlumno();
    private Alumno alumno;

    public static ManejadorAlumno getMANEJADOR_ALUMNO() {
        return MANEJADOR_ALUMNO;
    }
    
    public void agregar(Gradoseccion gradoseccion, String nombre, String apellido) {
        Alumno nuevoAlumno = new Alumno(gradoseccion, nombre, apellido);
        AsistenteConsulta.getASISTENTE_CONSULTA().agregarObjeto(nuevoAlumno);
    }
    
    public void modificar(Alumno alumnoModificar) {
        AsistenteConsulta.getASISTENTE_CONSULTA().modificarObjeto(alumno);
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    
    
}
