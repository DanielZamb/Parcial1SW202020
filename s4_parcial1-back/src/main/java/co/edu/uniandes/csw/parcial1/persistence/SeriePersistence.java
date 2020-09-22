/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.persistence;

import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author estudiante
 */
@Stateless
public class SeriePersistence {
    
    private static final Logger LOGGER = Logger.getLogger(SeriePersistence.class.getName());
     
    @PersistenceContext(unitName = "parcial1PU")
    protected EntityManager em;
    
    public List<SerieEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las series");
        Query q = em.createQuery("select u from SerieEntity u");
        return q.getResultList();
    }
    
}
