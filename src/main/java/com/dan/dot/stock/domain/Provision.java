package com.dan.dot.stock.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "provision")
public class Provision {

    //region Getters & Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getFechaProvision() {
        return fechaProvision;
    }
    public void setFechaProvision(Date fechaProvision) {
        this.fechaProvision = fechaProvision;
    }
    public boolean isHabilitado() {
        return habilitado;
    }
    public void setHabilitado(boolean anulada) {
        this.habilitado = anulada;
    }
    //endregion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_provision")
    private Date fechaProvision;

    private boolean habilitado;
}