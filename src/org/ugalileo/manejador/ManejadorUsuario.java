/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ugalileo.manejador;

import java.util.List;
import org.ugalileo.bean.Usuario;
import org.ugalileo.db.AsistenteConsulta;

/**
 *
 * @author Carlos Marroquin
 */
public class ManejadorUsuario {
    private static final ManejadorUsuario MANEJADOR_USUARIO = new ManejadorUsuario();
    private Usuario usuario;

    public static ManejadorUsuario getMANEJADOR_USUARIO() {
        return MANEJADOR_USUARIO;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean autenticar(String usuario, String contrasena) {
        List listaUsuario = AsistenteConsulta.getASISTENTE_CONSULTA().consulta("FROM Usuario WHERE nombreUsuario='" + usuario +
                "' AND contrasena='" + contrasena + "'");
        if (!listaUsuario.isEmpty()) {
            this.usuario = ((Usuario) listaUsuario.get(0));
            return true;
        }
        return false;
    }
    
    
}
