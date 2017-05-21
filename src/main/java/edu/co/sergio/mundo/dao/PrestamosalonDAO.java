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
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamosalon;
import edu.co.sergio.mundo.vo.Salon;
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
public class PrestamosalonDAO implements Serializable {
    private EntityManager em=null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestamosalon prestamosalon) throws PreexistingEntityException, Exception {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idpersona = prestamosalon.getIdpersona();
            if (idpersona != null) {
                idpersona = em.getReference(idpersona.getClass(), idpersona.getIdpersona());
                prestamosalon.setIdpersona(idpersona);
            }
            Salon idsalon = prestamosalon.getIdsalon();
            if (idsalon != null) {
                idsalon = em.getReference(idsalon.getClass(), idsalon.getIdsalon());
                prestamosalon.setIdsalon(idsalon);
            }
            em.persist(prestamosalon);
            if (idpersona != null) {
                idpersona.getPrestamosalonCollection().add(prestamosalon);
                idpersona = em.merge(idpersona);
            }
            if (idsalon != null) {
                idsalon.getPrestamosalonCollection().add(prestamosalon);
                idsalon = em.merge(idsalon);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrestamosalon(prestamosalon.getIdprestamosalon()) != null) {
                throw new PreexistingEntityException("Prestamosalon " + prestamosalon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void edit(Prestamosalon prestamosalon) throws NonexistentEntityException, Exception {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamosalon persistentPrestamosalon = em.find(Prestamosalon.class, prestamosalon.getIdprestamosalon());
            Persona idpersonaOld = persistentPrestamosalon.getIdpersona();
            Persona idpersonaNew = prestamosalon.getIdpersona();
            Salon idsalonOld = persistentPrestamosalon.getIdsalon();
            Salon idsalonNew = prestamosalon.getIdsalon();
            if (idpersonaNew != null) {
                idpersonaNew = em.getReference(idpersonaNew.getClass(), idpersonaNew.getIdpersona());
                prestamosalon.setIdpersona(idpersonaNew);
            }
            if (idsalonNew != null) {
                idsalonNew = em.getReference(idsalonNew.getClass(), idsalonNew.getIdsalon());
                prestamosalon.setIdsalon(idsalonNew);
            }
            prestamosalon = em.merge(prestamosalon);
            if (idpersonaOld != null && !idpersonaOld.equals(idpersonaNew)) {
                idpersonaOld.getPrestamosalonCollection().remove(prestamosalon);
                idpersonaOld = em.merge(idpersonaOld);
            }
            if (idpersonaNew != null && !idpersonaNew.equals(idpersonaOld)) {
                idpersonaNew.getPrestamosalonCollection().add(prestamosalon);
                idpersonaNew = em.merge(idpersonaNew);
            }
            if (idsalonOld != null && !idsalonOld.equals(idsalonNew)) {
                idsalonOld.getPrestamosalonCollection().remove(prestamosalon);
                idsalonOld = em.merge(idsalonOld);
            }
            if (idsalonNew != null && !idsalonNew.equals(idsalonOld)) {
                idsalonNew.getPrestamosalonCollection().add(prestamosalon);
                idsalonNew = em.merge(idsalonNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = prestamosalon.getIdprestamosalon();
                if (findPrestamosalon(id) == null) {
                    throw new NonexistentEntityException("The prestamosalon with id " + id + " no longer exists.");
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
            Prestamosalon prestamosalon;
            try {
                prestamosalon = em.getReference(Prestamosalon.class, id);
                prestamosalon.getIdprestamosalon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamosalon with id " + id + " no longer exists.", enfe);
            }
            Persona idpersona = prestamosalon.getIdpersona();
            if (idpersona != null) {
                idpersona.getPrestamosalonCollection().remove(prestamosalon);
                idpersona = em.merge(idpersona);
            }
            Salon idsalon = prestamosalon.getIdsalon();
            if (idsalon != null) {
                idsalon.getPrestamosalonCollection().remove(prestamosalon);
                idsalon = em.merge(idsalon);
            }
            em.remove(prestamosalon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public List<Prestamosalon> findPrestamosalonEntities() {
        return findPrestamosalonEntities(true, -1, -1);
    }

    public List<Prestamosalon> findPrestamosalonEntities(int maxResults, int firstResult) {
        return findPrestamosalonEntities(false, maxResults, firstResult);
    }

    private List<Prestamosalon> findPrestamosalonEntities(boolean all, int maxResults, int firstResult) {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestamosalon.class));
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

    public Prestamosalon findPrestamosalon(Integer id) {
        startOperation();
        try {
            return em.find(Prestamosalon.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getPrestamosalonCount() {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestamosalon> rt = cq.from(Prestamosalon.class);
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
            Logger.getLogger(PrestamosalonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
