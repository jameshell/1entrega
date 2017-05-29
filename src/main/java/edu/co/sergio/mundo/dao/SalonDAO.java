/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.dao.exceptions.IllegalOrphanException;
import edu.co.sergio.mundo.dao.exceptions.NonexistentEntityException;
import edu.co.sergio.mundo.dao.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.co.sergio.mundo.vo.Prestamosalon;
import java.util.ArrayList;
import java.util.Collection;
import edu.co.sergio.mundo.vo.Prestamo;
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
public class SalonDAO implements Serializable {
    private EntityManager em=null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Salon salon) throws PreexistingEntityException, Exception {
        if (salon.getPrestamosalonCollection() == null) {
            salon.setPrestamosalonCollection(new ArrayList<Prestamosalon>());
        }
        if (salon.getPrestamoCollection() == null) {
            salon.setPrestamoCollection(new ArrayList<Prestamo>());
        }
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Prestamosalon> attachedPrestamosalonCollection = new ArrayList<Prestamosalon>();
            for (Prestamosalon prestamosalonCollectionPrestamosalonToAttach : salon.getPrestamosalonCollection()) {
                prestamosalonCollectionPrestamosalonToAttach = em.getReference(prestamosalonCollectionPrestamosalonToAttach.getClass(), prestamosalonCollectionPrestamosalonToAttach.getIdprestamosalon());
                attachedPrestamosalonCollection.add(prestamosalonCollectionPrestamosalonToAttach);
            }
            salon.setPrestamosalonCollection(attachedPrestamosalonCollection);
            Collection<Prestamo> attachedPrestamoCollection = new ArrayList<Prestamo>();
            for (Prestamo prestamoCollectionPrestamoToAttach : salon.getPrestamoCollection()) {
                prestamoCollectionPrestamoToAttach = em.getReference(prestamoCollectionPrestamoToAttach.getClass(), prestamoCollectionPrestamoToAttach.getCodprestamo());
                attachedPrestamoCollection.add(prestamoCollectionPrestamoToAttach);
            }
            salon.setPrestamoCollection(attachedPrestamoCollection);
            em.persist(salon);
            for (Prestamosalon prestamosalonCollectionPrestamosalon : salon.getPrestamosalonCollection()) {
                Salon oldIdsalonOfPrestamosalonCollectionPrestamosalon = prestamosalonCollectionPrestamosalon.getIdsalon();
                prestamosalonCollectionPrestamosalon.setIdsalon(salon);
                prestamosalonCollectionPrestamosalon = em.merge(prestamosalonCollectionPrestamosalon);
                if (oldIdsalonOfPrestamosalonCollectionPrestamosalon != null) {
                    oldIdsalonOfPrestamosalonCollectionPrestamosalon.getPrestamosalonCollection().remove(prestamosalonCollectionPrestamosalon);
                    oldIdsalonOfPrestamosalonCollectionPrestamosalon = em.merge(oldIdsalonOfPrestamosalonCollectionPrestamosalon);
                }
            }
            for (Prestamo prestamoCollectionPrestamo : salon.getPrestamoCollection()) {
                Salon oldIdsalonOfPrestamoCollectionPrestamo = prestamoCollectionPrestamo.getIdsalon();
                prestamoCollectionPrestamo.setIdsalon(salon);
                prestamoCollectionPrestamo = em.merge(prestamoCollectionPrestamo);
                if (oldIdsalonOfPrestamoCollectionPrestamo != null) {
                    oldIdsalonOfPrestamoCollectionPrestamo.getPrestamoCollection().remove(prestamoCollectionPrestamo);
                    oldIdsalonOfPrestamoCollectionPrestamo = em.merge(oldIdsalonOfPrestamoCollectionPrestamo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSalon(salon.getIdsalon()) != null) {
                throw new PreexistingEntityException("Salon " + salon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void edit(Salon salon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        if (salon.getPrestamosalonCollection() == null) {
            salon.setPrestamosalonCollection(new ArrayList<Prestamosalon>());
        }
        if (salon.getPrestamoCollection() == null) {
            salon.setPrestamoCollection(new ArrayList<Prestamo>());
        }
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon persistentSalon = em.find(Salon.class, salon.getIdsalon());
            Collection<Prestamosalon> prestamosalonCollectionOld = persistentSalon.getPrestamosalonCollection();
            Collection<Prestamosalon> prestamosalonCollectionNew = salon.getPrestamosalonCollection();
            Collection<Prestamo> prestamoCollectionOld = persistentSalon.getPrestamoCollection();
            Collection<Prestamo> prestamoCollectionNew = salon.getPrestamoCollection();
            List<String> illegalOrphanMessages = null;
            for (Prestamosalon prestamosalonCollectionOldPrestamosalon : prestamosalonCollectionOld) {
                if (!prestamosalonCollectionNew.contains(prestamosalonCollectionOldPrestamosalon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prestamosalon " + prestamosalonCollectionOldPrestamosalon + " since its idsalon field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Prestamosalon> attachedPrestamosalonCollectionNew = new ArrayList<Prestamosalon>();
            for (Prestamosalon prestamosalonCollectionNewPrestamosalonToAttach : prestamosalonCollectionNew) {
                prestamosalonCollectionNewPrestamosalonToAttach = em.getReference(prestamosalonCollectionNewPrestamosalonToAttach.getClass(), prestamosalonCollectionNewPrestamosalonToAttach.getIdprestamosalon());
                attachedPrestamosalonCollectionNew.add(prestamosalonCollectionNewPrestamosalonToAttach);
            }
            prestamosalonCollectionNew = attachedPrestamosalonCollectionNew;
            salon.setPrestamosalonCollection(prestamosalonCollectionNew);
            Collection<Prestamo> attachedPrestamoCollectionNew = new ArrayList<Prestamo>();
            for (Prestamo prestamoCollectionNewPrestamoToAttach : prestamoCollectionNew) {
                prestamoCollectionNewPrestamoToAttach = em.getReference(prestamoCollectionNewPrestamoToAttach.getClass(), prestamoCollectionNewPrestamoToAttach.getCodprestamo());
                attachedPrestamoCollectionNew.add(prestamoCollectionNewPrestamoToAttach);
            }
            prestamoCollectionNew = attachedPrestamoCollectionNew;
            salon.setPrestamoCollection(prestamoCollectionNew);
            salon = em.merge(salon);
            for (Prestamosalon prestamosalonCollectionNewPrestamosalon : prestamosalonCollectionNew) {
                if (!prestamosalonCollectionOld.contains(prestamosalonCollectionNewPrestamosalon)) {
                    Salon oldIdsalonOfPrestamosalonCollectionNewPrestamosalon = prestamosalonCollectionNewPrestamosalon.getIdsalon();
                    prestamosalonCollectionNewPrestamosalon.setIdsalon(salon);
                    prestamosalonCollectionNewPrestamosalon = em.merge(prestamosalonCollectionNewPrestamosalon);
                    if (oldIdsalonOfPrestamosalonCollectionNewPrestamosalon != null && !oldIdsalonOfPrestamosalonCollectionNewPrestamosalon.equals(salon)) {
                        oldIdsalonOfPrestamosalonCollectionNewPrestamosalon.getPrestamosalonCollection().remove(prestamosalonCollectionNewPrestamosalon);
                        oldIdsalonOfPrestamosalonCollectionNewPrestamosalon = em.merge(oldIdsalonOfPrestamosalonCollectionNewPrestamosalon);
                    }
                }
            }
            for (Prestamo prestamoCollectionOldPrestamo : prestamoCollectionOld) {
                if (!prestamoCollectionNew.contains(prestamoCollectionOldPrestamo)) {
                    prestamoCollectionOldPrestamo.setIdsalon(null);
                    prestamoCollectionOldPrestamo = em.merge(prestamoCollectionOldPrestamo);
                }
            }
            for (Prestamo prestamoCollectionNewPrestamo : prestamoCollectionNew) {
                if (!prestamoCollectionOld.contains(prestamoCollectionNewPrestamo)) {
                    Salon oldIdsalonOfPrestamoCollectionNewPrestamo = prestamoCollectionNewPrestamo.getIdsalon();
                    prestamoCollectionNewPrestamo.setIdsalon(salon);
                    prestamoCollectionNewPrestamo = em.merge(prestamoCollectionNewPrestamo);
                    if (oldIdsalonOfPrestamoCollectionNewPrestamo != null && !oldIdsalonOfPrestamoCollectionNewPrestamo.equals(salon)) {
                        oldIdsalonOfPrestamoCollectionNewPrestamo.getPrestamoCollection().remove(prestamoCollectionNewPrestamo);
                        oldIdsalonOfPrestamoCollectionNewPrestamo = em.merge(oldIdsalonOfPrestamoCollectionNewPrestamo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = salon.getIdsalon();
                if (findSalon(id) == null) {
                    throw new NonexistentEntityException("The salon with id " + id + " no longer exists.");
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Salon salon;
            try {
                salon = em.getReference(Salon.class, id);
                salon.getIdsalon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salon with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Prestamosalon> prestamosalonCollectionOrphanCheck = salon.getPrestamosalonCollection();
            for (Prestamosalon prestamosalonCollectionOrphanCheckPrestamosalon : prestamosalonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Salon (" + salon + ") cannot be destroyed since the Prestamosalon " + prestamosalonCollectionOrphanCheckPrestamosalon + " in its prestamosalonCollection field has a non-nullable idsalon field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Prestamo> prestamoCollection = salon.getPrestamoCollection();
            for (Prestamo prestamoCollectionPrestamo : prestamoCollection) {
                prestamoCollectionPrestamo.setIdsalon(null);
                prestamoCollectionPrestamo = em.merge(prestamoCollectionPrestamo);
            }
            em.remove(salon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public List<Salon> findSalonEntities() {
        return findSalonEntities(true, -1, -1);
    }

    public List<Salon> findSalonEntities(int maxResults, int firstResult) {
        return findSalonEntities(false, maxResults, firstResult);
    }

    private List<Salon> findSalonEntities(boolean all, int maxResults, int firstResult) {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Salon.class));
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

    public Salon findSalon(Integer id) {
        startOperation();
        try {
            return em.find(Salon.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getSalonCount() {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Salon> rt = cq.from(Salon.class);
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
            Logger.getLogger(SalonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
