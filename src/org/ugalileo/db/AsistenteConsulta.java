/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ugalileo.db;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.ugalileo.util.HibernateUtil;

/**
 *
 * @author Carlos Marroquin
 */
public class AsistenteConsulta {
    private static final AsistenteConsulta ASISTENTE_CONSULTA = new AsistenteConsulta();

    public synchronized static AsistenteConsulta getASISTENTE_CONSULTA() {
        return ASISTENTE_CONSULTA;
    }
    
    public boolean agregarObjeto(Object objeto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(objeto);
            session.getTransaction().commit();
            session.close();
        } catch(HibernateException ex) {
            return false;
        }
        return true;
    }
    
    public boolean modificarObjeto(Object objeto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(objeto);
            session.getTransaction().commit();
            session.close();
        } catch(HibernateException ex) {
            return false;
        }
        return true;
    }
    
    public List consulta(String consulta) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List lista = session.createQuery(consulta).list();
        return lista;
    }
    
}
