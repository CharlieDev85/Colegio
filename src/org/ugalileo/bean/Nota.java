package org.ugalileo.bean;
// Generated 12/02/2015 09:33:19 AM by Hibernate Tools 4.3.1



/**
 * Nota generated by hbm2java
 */
public class Nota  implements java.io.Serializable {


     private Integer idNota;
     private Auxmateriaalumno auxmateriaalumno;
     private byte valor;

    public Nota() {
    }

    public Nota(Auxmateriaalumno auxmateriaalumno, byte valor) {
       this.auxmateriaalumno = auxmateriaalumno;
       this.valor = valor;
    }
   
    public Integer getIdNota() {
        return this.idNota;
    }
    
    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }
    public Auxmateriaalumno getAuxmateriaalumno() {
        return this.auxmateriaalumno;
    }
    
    public void setAuxmateriaalumno(Auxmateriaalumno auxmateriaalumno) {
        this.auxmateriaalumno = auxmateriaalumno;
    }
    public byte getValor() {
        return this.valor;
    }
    
    public void setValor(byte valor) {
        this.valor = valor;
    }




}


