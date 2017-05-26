/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.dao;

import edu.co.sergio.mundo.dao.exceptions.IllegalOrphanException;
import edu.co.sergio.mundo.dao.exceptions.NonexistentEntityException;
import edu.co.sergio.mundo.dao.exceptions.PreexistingEntityException;
import edu.co.sergio.mundo.vo.Persona;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import edu.co.sergio.mundo.vo.Prestamosalon;
import java.util.ArrayList;
import java.util.Collection;
import edu.co.sergio.mundo.vo.Prestamo;
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
public class PersonaDAO implements Serializable {
    private EntityManager em=null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) throws PreexistingEntityException, Exception {
        if (persona.getPrestamosalonCollection() == null) {
            persona.setPrestamosalonCollection(new ArrayList<Prestamosalon>());
        }
        if (persona.getPrestamoCollection() == null) {
            persona.setPrestamoCollection(new ArrayList<Prestamo>());
        }
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Prestamosalon> attachedPrestamosalonCollection = new ArrayList<Prestamosalon>();
            for (Prestamosalon prestamosalonCollectionPrestamosalonToAttach : persona.getPrestamosalonCollection()) {
                prestamosalonCollectionPrestamosalonToAttach = em.getReference(prestamosalonCollectionPrestamosalonToAttach.getClass(), prestamosalonCollectionPrestamosalonToAttach.getIdprestamosalon());
                attachedPrestamosalonCollection.add(prestamosalonCollectionPrestamosalonToAttach);
            }
            persona.setPrestamosalonCollection(attachedPrestamosalonCollection);
            Collection<Prestamo> attachedPrestamoCollection = new ArrayList<Prestamo>();
            for (Prestamo prestamoCollectionPrestamoToAttach : persona.getPrestamoCollection()) {
                prestamoCollectionPrestamoToAttach = em.getReference(prestamoCollectionPrestamoToAttach.getClass(), prestamoCollectionPrestamoToAttach.getCodprestamo());
                attachedPrestamoCollection.add(prestamoCollectionPrestamoToAttach);
            }
            persona.setPrestamoCollection(attachedPrestamoCollection);
            em.persist(persona);
            for (Prestamosalon prestamosalonCollectionPrestamosalon : persona.getPrestamosalonCollection()) {
                Persona oldIdpersonaOfPrestamosalonCollectionPrestamosalon = prestamosalonCollectionPrestamosalon.getIdpersona();
                prestamosalonCollectionPrestamosalon.setIdpersona(persona);
                prestamosalonCollectionPrestamosalon = em.merge(prestamosalonCollectionPrestamosalon);
                if (oldIdpersonaOfPrestamosalonCollectionPrestamosalon != null) {
                    oldIdpersonaOfPrestamosalonCollectionPrestamosalon.getPrestamosalonCollection().remove(prestamosalonCollectionPrestamosalon);
                    oldIdpersonaOfPrestamosalonCollectionPrestamosalon = em.merge(oldIdpersonaOfPrestamosalonCollectionPrestamosalon);
                }
            }
            for (Prestamo prestamoCollectionPrestamo : persona.getPrestamoCollection()) {
                Persona oldIdpersonaOfPrestamoCollectionPrestamo = prestamoCollectionPrestamo.getIdpersona();
                prestamoCollectionPrestamo.setIdpersona(persona);
                prestamoCollectionPrestamo = em.merge(prestamoCollectionPrestamo);
                if (oldIdpersonaOfPrestamoCollectionPrestamo != null) {
                    oldIdpersonaOfPrestamoCollectionPrestamo.getPrestamoCollection().remove(prestamoCollectionPrestamo);
                    oldIdpersonaOfPrestamoCollectionPrestamo = em.merge(oldIdpersonaOfPrestamoCollectionPrestamo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPersona(persona.getIdpersona()) != null) {
                throw new PreexistingEntityException("Persona " + persona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        startOperation();
        try {
            em= getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getIdpersona());
            Collection<Prestamosalon> prestamosalonCollectionOld = persistentPersona.getPrestamosalonCollection();
            Collection<Prestamosalon> prestamosalonCollectionNew = persona.getPrestamosalonCollection();
            Collection<Prestamo> prestamoCollectionOld = persistentPersona.getPrestamoCollection();
            Collection<Prestamo> prestamoCollectionNew = persona.getPrestamoCollection();
            List<String> illegalOrphanMessages = null;
            for (Prestamosalon prestamosalonCollectionOldPrestamosalon : prestamosalonCollectionOld) {
                if (!prestamosalonCollectionNew.contains(prestamosalonCollectionOldPrestamosalon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prestamosalon " + prestamosalonCollectionOldPrestamosalon + " since its idpersona field is not nullable.");
                }
            }
            for (Prestamo prestamoCollectionOldPrestamo : prestamoCollectionOld) {
                if (!prestamoCollectionNew.contains(prestamoCollectionOldPrestamo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Prestamo " + prestamoCollectionOldPrestamo + " since its idpersona field is not nullable.");
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
            persona.setPrestamosalonCollection(prestamosalonCollectionNew);
            Collection<Prestamo> attachedPrestamoCollectionNew = new ArrayList<Prestamo>();
            for (Prestamo prestamoCollectionNewPrestamoToAttach : prestamoCollectionNew) {
                prestamoCollectionNewPrestamoToAttach = em.getReference(prestamoCollectionNewPrestamoToAttach.getClass(), prestamoCollectionNewPrestamoToAttach.getCodprestamo());
                attachedPrestamoCollectionNew.add(prestamoCollectionNewPrestamoToAttach);
            }
            prestamoCollectionNew = attachedPrestamoCollectionNew;
            persona.setPrestamoCollection(prestamoCollectionNew);
            persona = em.merge(persona);
            for (Prestamosalon prestamosalonCollectionNewPrestamosalon : prestamosalonCollectionNew) {
                if (!prestamosalonCollectionOld.contains(prestamosalonCollectionNewPrestamosalon)) {
                    Persona oldIdpersonaOfPrestamosalonCollectionNewPrestamosalon = prestamosalonCollectionNewPrestamosalon.getIdpersona();
                    prestamosalonCollectionNewPrestamosalon.setIdpersona(persona);
                    prestamosalonCollectionNewPrestamosalon = em.merge(prestamosalonCollectionNewPrestamosalon);
                    if (oldIdpersonaOfPrestamosalonCollectionNewPrestamosalon != null && !oldIdpersonaOfPrestamosalonCollectionNewPrestamosalon.equals(persona)) {
                        oldIdpersonaOfPrestamosalonCollectionNewPrestamosalon.getPrestamosalonCollection().remove(prestamosalonCollectionNewPrestamosalon);
                        oldIdpersonaOfPrestamosalonCollectionNewPrestamosalon = em.merge(oldIdpersonaOfPrestamosalonCollectionNewPrestamosalon);
                    }
                }
            }
            for (Prestamo prestamoCollectionNewPrestamo : prestamoCollectionNew) {
                if (!prestamoCollectionOld.contains(prestamoCollectionNewPrestamo)) {
                    Persona oldIdpersonaOfPrestamoCollectionNewPrestamo = prestamoCollectionNewPrestamo.getIdpersona();
                    prestamoCollectionNewPrestamo.setIdpersona(persona);
                    prestamoCollectionNewPrestamo = em.merge(prestamoCollectionNewPrestamo);
                    if (oldIdpersonaOfPrestamoCollectionNewPrestamo != null && !oldIdpersonaOfPrestamoCollectionNewPrestamo.equals(persona)) {
                        oldIdpersonaOfPrestamoCollectionNewPrestamo.getPrestamoCollection().remove(prestamoCollectionNewPrestamo);
                        oldIdpersonaOfPrestamoCollectionNewPrestamo = em.merge(oldIdpersonaOfPrestamoCollectionNewPrestamo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getIdpersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getIdpersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Prestamosalon> prestamosalonCollectionOrphanCheck = persona.getPrestamosalonCollection();
            for (Prestamosalon prestamosalonCollectionOrphanCheckPrestamosalon : prestamosalonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Prestamosalon " + prestamosalonCollectionOrphanCheckPrestamosalon + " in its prestamosalonCollection field has a non-nullable idpersona field.");
            }
            Collection<Prestamo> prestamoCollectionOrphanCheck = persona.getPrestamoCollection();
            for (Prestamo prestamoCollectionOrphanCheckPrestamo : prestamoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Prestamo " + prestamoCollectionOrphanCheckPrestamo + " in its prestamoCollection field has a non-nullable idpersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        startOperation();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getPersonaCount() {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
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
            Logger.getLogger(PersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
