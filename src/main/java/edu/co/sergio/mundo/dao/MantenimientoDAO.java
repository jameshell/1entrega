/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.dao.exceptions.NonexistentEntityException;
import edu.co.sergio.mundo.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.co.sergio.mundo.vo.Herramienta;
import edu.co.sergio.mundo.vo.Mantenimiento;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos
 */
public class MantenimientoDAO implements Serializable {

    private EntityManager em=null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mantenimiento mantenimiento) throws PreexistingEntityException, Exception {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Herramienta idherramienta = mantenimiento.getIdherramienta();
            if (idherramienta != null) {
                idherramienta = em.getReference(idherramienta.getClass(), idherramienta.getIdherramienta());
                mantenimiento.setIdherramienta(idherramienta);
            }
            em.persist(mantenimiento);
            if (idherramienta != null) {
                idherramienta.getMantenimientoCollection().add(mantenimiento);
                idherramienta = em.merge(idherramienta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMantenimiento(mantenimiento.getIdmantenimiento()) != null) {
                throw new PreexistingEntityException("Mantenimiento " + mantenimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void edit(Mantenimiento mantenimiento) throws NonexistentEntityException, Exception {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento persistentMantenimiento = em.find(Mantenimiento.class, mantenimiento.getIdmantenimiento());
            Herramienta idherramientaOld = persistentMantenimiento.getIdherramienta();
            Herramienta idherramientaNew = mantenimiento.getIdherramienta();
            if (idherramientaNew != null) {
                idherramientaNew = em.getReference(idherramientaNew.getClass(), idherramientaNew.getIdherramienta());
                mantenimiento.setIdherramienta(idherramientaNew);
            }
            mantenimiento = em.merge(mantenimiento);
            if (idherramientaOld != null && !idherramientaOld.equals(idherramientaNew)) {
                idherramientaOld.getMantenimientoCollection().remove(mantenimiento);
                idherramientaOld = em.merge(idherramientaOld);
            }
            if (idherramientaNew != null && !idherramientaNew.equals(idherramientaOld)) {
                idherramientaNew.getMantenimientoCollection().add(mantenimiento);
                idherramientaNew = em.merge(idherramientaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mantenimiento.getIdmantenimiento();
                if (findMantenimiento(id) == null) {
                    throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mantenimiento mantenimiento;
            try {
                mantenimiento = em.getReference(Mantenimiento.class, id);
                mantenimiento.getIdmantenimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mantenimiento with id " + id + " no longer exists.", enfe);
            }
            Herramienta idherramienta = mantenimiento.getIdherramienta();
            if (idherramienta != null) {
                idherramienta.getMantenimientoCollection().remove(mantenimiento);
                idherramienta = em.merge(idherramienta);
            }
            em.remove(mantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public List<Mantenimiento> findMantenimientoEntities() {
        return findMantenimientoEntities(true, -1, -1);
    }

    public List<Mantenimiento> findMantenimientoEntities(int maxResults, int firstResult) {
        return findMantenimientoEntities(false, maxResults, firstResult);
    }

    private List<Mantenimiento> findMantenimientoEntities(boolean all, int maxResults, int firstResult) {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mantenimiento.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
            emf.close();
        }
    }

    public Mantenimiento findMantenimiento(Integer id) {
        startOperation();
        try {
            return em.find(Mantenimiento.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getMantenimientoCount() {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mantenimiento> rt = cq.from(Mantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
            emf.close();
        }
    }
    protected void startOperation() { 
        URI dbUri = null;
        try {
            dbUri = new URI(System.getenv("DATABASE_URL")); 
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            Map<String, String> properties = new HashMap<String, String>();
            properties.put("javax.persistence.jdbc.url", dbUrl);
            properties.put("javax.persistence.jdbc.user", username );
            properties.put("javax.persistence.jdbc.password", password );
            properties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            this.emf = Persistence.createEntityManagerFactory("LABUSA",properties);
            this.em = emf.createEntityManager();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MantenimientoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
