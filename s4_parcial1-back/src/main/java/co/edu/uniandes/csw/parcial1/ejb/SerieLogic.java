/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.ejb;

import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
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

}
