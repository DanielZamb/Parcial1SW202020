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
import javax.persistence.TypedQuery;

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
    public SerieEntity findSerie(Long serieID){
        LOGGER.log(Level.INFO,"Consultando la serie con id={0} y mascota con id = "+serieID);
        String queryString = "SELECT s FROM SerieEntity s WHERE s.id = :serieID)";
        TypedQuery query= em.createQuery(queryString, SerieEntity.class);
        query.setParameter("serieID", serieID);
        List<SerieEntity> list = query.getResultList();
        SerieEntity result;
        if (list == null|| list.isEmpty()) result = null;
        else result = list.get(0);
        return result;
    }
    public SerieEntity create(SerieEntity serie){
        LOGGER.log(Level.INFO,"Creando una serie nueva");
        em.persist(serie);
        LOGGER.log(Level.INFO,"Factura creada");
        return serie;
    }
}
