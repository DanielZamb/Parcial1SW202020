/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
/**
 *
 * @author de.zambrano
 */
@Entity
public class CapituloEntity extends BaseEntity {
    private String nombre;
    private Date duracion;
    private String descripcionCorta;
    private Boolean aptoMenores;
    
    @PodamExclude
    @ManyToOne
    private SerieEntity serie;
            
    public CapituloEntity() {
    }

    public SerieEntity getSerie() {
        return serie;
    }

    public void setSerie(SerieEntity serie) {
        this.serie = serie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getDuracion() {
        return duracion;
    }

    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public Boolean getAptoMenores() {
        return aptoMenores;
    }

    public void isAptoMenores(Boolean aptoMenores) {
        this.aptoMenores = aptoMenores;
    }
    
}
