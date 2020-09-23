/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.ejb;

import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.SeriePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class SerieLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SerieLogic.class.getName());

    @Inject
    private SeriePersistence  persistence;
    
    public List<SerieEntity> getSeries() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los series");
        List<SerieEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los series");
        return lista;
    }
    public SerieEntity createSerie(SerieEntity entity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la serie");
        SerieEntity serie = entity;
        if (checkNombre(entity)&&checkRating(entity)&&checkAptoParaMenores(entity))
        {
            serie = persistence.create(serie);
        }
        LOGGER.log(Level.INFO, "Termina proceso de creacion de la serie");
        return serie;
    }
    public Boolean checkNombre(SerieEntity entity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Revisando el nombre de la serie con id = {0}", entity.getId());
        SerieEntity serie = persistence.findSerie(entity.getId());
        if (serie.getName().equals(""))throw new BusinessLogicException("El nombre de la serie no puede estar vacio.");
        if (serie.getName()!= null) throw new BusinessLogicException("El nombre de la serie no pude ser null.");
        return true;
    }
    public Boolean checkRating(SerieEntity entity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Revisando el rating de la serie con id = {0}", entity.getId());
        SerieEntity serie = persistence.findSerie(entity.getId());
        if(serie.getRating()>10||serie.getRating()<0) throw new BusinessLogicException("la serie no puede tener un rating <0 o >10. ");  
        return true;
    }
    public Boolean checkAptoParaMenores(SerieEntity entity)throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Revisando la clasificacion de la serie con id = {0}", entity.getId());
        SerieEntity serie = persistence.findSerie(entity.getId());
        List<CapituloEntity> capitulos = serie.getCapitulos();
        Boolean found = false;
        for (CapituloEntity cap : capitulos){
            if (cap.getAptoMenores()) found = false;
            else {
                found = true;
                break;
            }
        }
        if (found && serie.getCategory().equalsIgnoreCase("infantil")) throw new BusinessLogicException("La serie analizada no es apta para publico infantil.");
        return true;
    }
}
