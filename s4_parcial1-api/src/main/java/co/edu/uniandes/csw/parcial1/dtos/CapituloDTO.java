/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.dtos;

import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class CapituloDTO {
    private String nombre;
    private Long id;
    private Date duracion;
    private String descripcionCorta;
    private Boolean aptoMenores;

    public CapituloDTO() {
    }
    public CapituloDTO(CapituloEntity capituloEntity) {
        if (capituloEntity != null) {
            this.id = capituloEntity.getId();
            this.nombre = capituloEntity.getNombre();
        }
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

    public void setAptoMenores(Boolean aptoMenores) {
        this.aptoMenores = aptoMenores;
    }
    
}
