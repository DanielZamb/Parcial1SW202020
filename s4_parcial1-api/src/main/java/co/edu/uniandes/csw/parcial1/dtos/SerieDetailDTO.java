/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.dtos;

import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class SerieDetailDTO extends SerieDTO implements Serializable{
    private List<CapituloDTO> capitulos;

    public SerieDetailDTO() {
    }

    public SerieDetailDTO(SerieEntity serieEntity) {
        super(serieEntity);
        if (serieEntity != null){
            capitulos = new ArrayList<>();
            for (CapituloEntity cap : serieEntity.getCapitulos()){
                capitulos.add(new CapituloDTO(cap));
            }
        }
    }
    @Override
    public SerieEntity toEntity(){
        SerieEntity serieEntity = super.toEntity();
        if (capitulos != null){
        List<CapituloEntity> capitulosEntity = new ArrayList<>();
        for (CapituloDTO cap: getCapitulos()){
            capitulosEntity.add(CapituloDTO.toEntity());
            }
        }
    }

    public List<CapituloDTO> getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(List<CapituloDTO> capitulos) {
        this.capitulos = capitulos;
    }
    
    
}
