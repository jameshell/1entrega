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
import edu.co.sergio.mundo.vo.Administrativo;
import edu.co.sergio.mundo.vo.Herramienta;
import edu.co.sergio.mundo.vo.Transaccion;
import java.util.ArrayList;
import java.util.Collection;
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
public class HerramientaDAO implements Serializable {

    private EntityManager em;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Herramienta herramienta) throws PreexistingEntityException, Exception {
        if (herramienta.getTransaccionCollection() == null) {
            herramienta.setTransaccionCollection(new ArrayList<Transaccion>());
        }
        if (herramienta.getMantenimientoCollection() == null) {
            herramienta.setMantenimientoCollection(new ArrayList<Mantenimiento>());
        }
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo idadministrativo = herramienta.getIdadministrativo();
            if (idadministrativo != null) {
                idadministrativo = em.getReference(idadministrativo.getClass(), idadministrativo.getIdadministrativo());
                herramienta.setIdadministrativo(idadministrativo);
            }
            Collection<Transaccion> attachedTransaccionCollection = new ArrayList<Transaccion>();
            for (Transaccion transaccionCollectionTransaccionToAttach : herramienta.getTransaccionCollection()) {
                transaccionCollectionTransaccionToAttach = em.getReference(transaccionCollectionTransaccionToAttach.getClass(), transaccionCollectionTransaccionToAttach.getIdtransaccion());
                attachedTransaccionCollection.add(transaccionCollectionTransaccionToAttach);
            }
            herramienta.setTransaccionCollection(attachedTransaccionCollection);
            Collection<Mantenimiento> attachedMantenimientoCollection = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoCollectionMantenimientoToAttach : herramienta.getMantenimientoCollection()) {
                mantenimientoCollectionMantenimientoToAttach = em.getReference(mantenimientoCollectionMantenimientoToAttach.getClass(), mantenimientoCollectionMantenimientoToAttach.getIdmantenimiento());
                attachedMantenimientoCollection.add(mantenimientoCollectionMantenimientoToAttach);
            }
            herramienta.setMantenimientoCollection(attachedMantenimientoCollection);
            em.persist(herramienta);
            if (idadministrativo != null) {
                idadministrativo.getHerramientaCollection().add(herramienta);
                idadministrativo = em.merge(idadministrativo);
            }
            for (Transaccion transaccionCollectionTransaccion : herramienta.getTransaccionCollection()) {
                Herramienta oldIdherramientaOfTransaccionCollectionTransaccion = transaccionCollectionTransaccion.getIdherramienta();
                transaccionCollectionTransaccion.setIdherramienta(herramienta);
                transaccionCollectionTransaccion = em.merge(transaccionCollectionTransaccion);
                if (oldIdherramientaOfTransaccionCollectionTransaccion != null) {
                    oldIdherramientaOfTransaccionCollectionTransaccion.getTransaccionCollection().remove(transaccionCollectionTransaccion);
                    oldIdherramientaOfTransaccionCollectionTransaccion = em.merge(oldIdherramientaOfTransaccionCollectionTransaccion);
                }
            }
            for (Mantenimiento mantenimientoCollectionMantenimiento : herramienta.getMantenimientoCollection()) {
                Herramienta oldIdherramientaOfMantenimientoCollectionMantenimiento = mantenimientoCollectionMantenimiento.getIdherramienta();
                mantenimientoCollectionMantenimiento.setIdherramienta(herramienta);
                mantenimientoCollectionMantenimiento = em.merge(mantenimientoCollectionMantenimiento);
                if (oldIdherramientaOfMantenimientoCollectionMantenimiento != null) {
                    oldIdherramientaOfMantenimientoCollectionMantenimiento.getMantenimientoCollection().remove(mantenimientoCollectionMantenimiento);
                    oldIdherramientaOfMantenimientoCollectionMantenimiento = em.merge(oldIdherramientaOfMantenimientoCollectionMantenimiento);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHerramienta(herramienta.getIdherramienta()) != null) {
                throw new PreexistingEntityException("Herramienta " + herramienta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void edit(Herramienta herramienta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Herramienta persistentHerramienta = em.find(Herramienta.class, herramienta.getIdherramienta());
            Administrativo idadministrativoOld = persistentHerramienta.getIdadministrativo();
            Administrativo idadministrativoNew = herramienta.getIdadministrativo();
            Collection<Transaccion> transaccionCollectionOld = persistentHerramienta.getTransaccionCollection();
            Collection<Transaccion> transaccionCollectionNew = herramienta.getTransaccionCollection();
            Collection<Mantenimiento> mantenimientoCollectionOld = persistentHerramienta.getMantenimientoCollection();
            Collection<Mantenimiento> mantenimientoCollectionNew = herramienta.getMantenimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (Transaccion transaccionCollectionOldTransaccion : transaccionCollectionOld) {
                if (!transaccionCollectionNew.contains(transaccionCollectionOldTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transaccion " + transaccionCollectionOldTransaccion + " since its idherramienta field is not nullable.");
                }
            }
            for (Mantenimiento mantenimientoCollectionOldMantenimiento : mantenimientoCollectionOld) {
                if (!mantenimientoCollectionNew.contains(mantenimientoCollectionOldMantenimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Mantenimiento " + mantenimientoCollectionOldMantenimiento + " since its idherramienta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idadministrativoNew != null) {
                idadministrativoNew = em.getReference(idadministrativoNew.getClass(), idadministrativoNew.getIdadministrativo());
                herramienta.setIdadministrativo(idadministrativoNew);
            }
            Collection<Transaccion> attachedTransaccionCollectionNew = new ArrayList<Transaccion>();
            for (Transaccion transaccionCollectionNewTransaccionToAttach : transaccionCollectionNew) {
                transaccionCollectionNewTransaccionToAttach = em.getReference(transaccionCollectionNewTransaccionToAttach.getClass(), transaccionCollectionNewTransaccionToAttach.getIdtransaccion());
                attachedTransaccionCollectionNew.add(transaccionCollectionNewTransaccionToAttach);
            }
            transaccionCollectionNew = attachedTransaccionCollectionNew;
            herramienta.setTransaccionCollection(transaccionCollectionNew);
            Collection<Mantenimiento> attachedMantenimientoCollectionNew = new ArrayList<Mantenimiento>();
            for (Mantenimiento mantenimientoCollectionNewMantenimientoToAttach : mantenimientoCollectionNew) {
                mantenimientoCollectionNewMantenimientoToAttach = em.getReference(mantenimientoCollectionNewMantenimientoToAttach.getClass(), mantenimientoCollectionNewMantenimientoToAttach.getIdmantenimiento());
                attachedMantenimientoCollectionNew.add(mantenimientoCollectionNewMantenimientoToAttach);
            }
            mantenimientoCollectionNew = attachedMantenimientoCollectionNew;
            herramienta.setMantenimientoCollection(mantenimientoCollectionNew);
            herramienta = em.merge(herramienta);
            if (idadministrativoOld != null && !idadministrativoOld.equals(idadministrativoNew)) {
                idadministrativoOld.getHerramientaCollection().remove(herramienta);
                idadministrativoOld = em.merge(idadministrativoOld);
            }
            if (idadministrativoNew != null && !idadministrativoNew.equals(idadministrativoOld)) {
                idadministrativoNew.getHerramientaCollection().add(herramienta);
                idadministrativoNew = em.merge(idadministrativoNew);
            }
            for (Transaccion transaccionCollectionNewTransaccion : transaccionCollectionNew) {
                if (!transaccionCollectionOld.contains(transaccionCollectionNewTransaccion)) {
                    Herramienta oldIdherramientaOfTransaccionCollectionNewTransaccion = transaccionCollectionNewTransaccion.getIdherramienta();
                    transaccionCollectionNewTransaccion.setIdherramienta(herramienta);
                    transaccionCollectionNewTransaccion = em.merge(transaccionCollectionNewTransaccion);
                    if (oldIdherramientaOfTransaccionCollectionNewTransaccion != null && !oldIdherramientaOfTransaccionCollectionNewTransaccion.equals(herramienta)) {
                        oldIdherramientaOfTransaccionCollectionNewTransaccion.getTransaccionCollection().remove(transaccionCollectionNewTransaccion);
                        oldIdherramientaOfTransaccionCollectionNewTransaccion = em.merge(oldIdherramientaOfTransaccionCollectionNewTransaccion);
                    }
                }
            }
            for (Mantenimiento mantenimientoCollectionNewMantenimiento : mantenimientoCollectionNew) {
                if (!mantenimientoCollectionOld.contains(mantenimientoCollectionNewMantenimiento)) {
                    Herramienta oldIdherramientaOfMantenimientoCollectionNewMantenimiento = mantenimientoCollectionNewMantenimiento.getIdherramienta();
                    mantenimientoCollectionNewMantenimiento.setIdherramienta(herramienta);
                    mantenimientoCollectionNewMantenimiento = em.merge(mantenimientoCollectionNewMantenimiento);
                    if (oldIdherramientaOfMantenimientoCollectionNewMantenimiento != null && !oldIdherramientaOfMantenimientoCollectionNewMantenimiento.equals(herramienta)) {
                        oldIdherramientaOfMantenimientoCollectionNewMantenimiento.getMantenimientoCollection().remove(mantenimientoCollectionNewMantenimiento);
                        oldIdherramientaOfMantenimientoCollectionNewMantenimiento = em.merge(oldIdherramientaOfMantenimientoCollectionNewMantenimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = herramienta.getIdherramienta();
                if (findHerramienta(id) == null) {
                    throw new NonexistentEntityException("The herramienta with id " + id + " no longer exists.");
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
            Herramienta herramienta;
            try {
                herramienta = em.getReference(Herramienta.class, id);
                herramienta.getIdherramienta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The herramienta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Transaccion> transaccionCollectionOrphanCheck = herramienta.getTransaccionCollection();
            for (Transaccion transaccionCollectionOrphanCheckTransaccion : transaccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Herramienta (" + herramienta + ") cannot be destroyed since the Transaccion " + transaccionCollectionOrphanCheckTransaccion + " in its transaccionCollection field has a non-nullable idherramienta field.");
            }
            Collection<Mantenimiento> mantenimientoCollectionOrphanCheck = herramienta.getMantenimientoCollection();
            for (Mantenimiento mantenimientoCollectionOrphanCheckMantenimiento : mantenimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Herramienta (" + herramienta + ") cannot be destroyed since the Mantenimiento " + mantenimientoCollectionOrphanCheckMantenimiento + " in its mantenimientoCollection field has a non-nullable idherramienta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Administrativo idadministrativo = herramienta.getIdadministrativo();
            if (idadministrativo != null) {
                idadministrativo.getHerramientaCollection().remove(herramienta);
                idadministrativo = em.merge(idadministrativo);
            }
            em.remove(herramienta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public List<Herramienta> findHerramientaEntities() {
        return findHerramientaEntities(true, -1, -1);
    }

    public List<Herramienta> findHerramientaEntities(int maxResults, int firstResult) {
        return findHerramientaEntities(false, maxResults, firstResult);
    }

    private List<Herramienta> findHerramientaEntities(boolean all, int maxResults, int firstResult) {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Herramienta.class));
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

    public Herramienta findHerramienta(Integer id) {
        startOperation();
        try {
            return em.find(Herramienta.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getHerramientaCount() {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Herramienta> rt = cq.from(Herramienta.class);
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
            Logger.getLogger(HerramientaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
