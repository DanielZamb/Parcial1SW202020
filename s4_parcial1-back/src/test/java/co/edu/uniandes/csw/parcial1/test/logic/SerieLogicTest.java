/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.test.logic;

import co.edu.uniandes.csw.parcial1.ejb.SerieLogic;
import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.SeriePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class SerieLogicTest {
    @Inject
    private SerieLogic serieLogic;
    @Inject
    private SeriePersistence seriePeristence;
    @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;
    
    @Deployment
    public static JavaArchive createDeployment() {
    return ShrinkWrap.create(JavaArchive.class)
            .addPackage(SerieEntity.class.getPackage())
            .addPackage(SeriePersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Before
    public void configTest(){
        try {
        utx.begin();
        em.joinTransaction();
        clearData();
        utx.commit();
    } catch (Exception e) {
        e.printStackTrace();
        try {
            utx.rollback();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
     }
    }
    private void clearData() {
        em.createQuery("delete from SerieEntity").executeUpdate();
    }
    @Test
    public void createSerieTest()throws BusinessLogicException{
       PodamFactory factory = new PodamFactoryImpl();
       SerieEntity newEntity = factory.manufacturePojo(SerieEntity.class);
       newEntity.setName("Hoal pepito");
       newEntity.setRating(new Float(7.012));  
       List<CapituloEntity> capitulos = newEntity.getCapitulos();
       for (CapituloEntity cap : capitulos ){
           cap.isAptoMenores(true);
       }
       SerieEntity result = serieLogic.createSerie(newEntity);
       Assert.assertNotNull(result);
        
        SerieEntity entity = em.find(SerieEntity.class, result.getId());
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getCategory(), entity.getCategory());
        Assert.assertEquals(result.getDescription(), entity.getDescription());
        Assert.assertEquals(result.getName(), entity.getName());
        Assert.assertEquals(result.getRating(), entity.getRating());
        Assert.assertEquals(result.getReleaseYear(), entity.getReleaseYear()); 
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createSerieTestFailure() throws BusinessLogicException{
        SerieEntity entity;
    }
    
}
