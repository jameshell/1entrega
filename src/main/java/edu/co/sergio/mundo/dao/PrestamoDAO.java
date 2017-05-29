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
import edu.co.sergio.mundo.vo.Persona;
import edu.co.sergio.mundo.vo.Prestamo;
import edu.co.sergio.mundo.vo.Salon;
import edu.co.sergio.mundo.vo.Transaccion;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
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
public class PrestamoDAO implements Serializable {

    private EntityManager em=null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prestamo prestamo) throws PreexistingEntityException, Exception {
        if (prestamo.getTransaccionCollection() == null) {
            prestamo.setTransaccionCollection(new ArrayList<Transaccion>());
        }
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Administrativo idadministrativo = prestamo.getIdadministrativo();
            if (idadministrativo != null) {
                idadministrativo = em.getReference(idadministrativo.getClass(), idadministrativo.getIdadministrativo());
                prestamo.setIdadministrativo(idadministrativo);
            }
            Persona idpersona = prestamo.getIdpersona();
            if (idpersona != null) {
                idpersona = em.getReference(idpersona.getClass(), idpersona.getIdpersona());
                prestamo.setIdpersona(idpersona);
            }
            Salon idsalon = prestamo.getIdsalon();
            if (idsalon != null) {
                idsalon = em.getReference(idsalon.getClass(), idsalon.getIdsalon());
                prestamo.setIdsalon(idsalon);
            }
            Collection<Transaccion> attachedTransaccionCollection = new ArrayList<Transaccion>();
            for (Transaccion transaccionCollectionTransaccionToAttach : prestamo.getTransaccionCollection()) {
                transaccionCollectionTransaccionToAttach = em.getReference(transaccionCollectionTransaccionToAttach.getClass(), transaccionCollectionTransaccionToAttach.getIdtransaccion());
                attachedTransaccionCollection.add(transaccionCollectionTransaccionToAttach);
            }
            prestamo.setTransaccionCollection(attachedTransaccionCollection);
            em.persist(prestamo);
            if (idadministrativo != null) {
                idadministrativo.getPrestamoCollection().add(prestamo);
                idadministrativo = em.merge(idadministrativo);
            }
            if (idpersona != null) {
                idpersona.getPrestamoCollection().add(prestamo);
                idpersona = em.merge(idpersona);
            }
            if (idsalon != null) {
                idsalon.getPrestamoCollection().add(prestamo);
                idsalon = em.merge(idsalon);
            }
            for (Transaccion transaccionCollectionTransaccion : prestamo.getTransaccionCollection()) {
                Prestamo oldCodprestamoOfTransaccionCollectionTransaccion = transaccionCollectionTransaccion.getCodprestamo();
                transaccionCollectionTransaccion.setCodprestamo(prestamo);
                transaccionCollectionTransaccion = em.merge(transaccionCollectionTransaccion);
                if (oldCodprestamoOfTransaccionCollectionTransaccion != null) {
                    oldCodprestamoOfTransaccionCollectionTransaccion.getTransaccionCollection().remove(transaccionCollectionTransaccion);
                    oldCodprestamoOfTransaccionCollectionTransaccion = em.merge(oldCodprestamoOfTransaccionCollectionTransaccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrestamo(prestamo.getCodprestamo()) != null) {
                throw new PreexistingEntityException("Prestamo " + prestamo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public void edit(Prestamo prestamo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        if (prestamo.getTransaccionCollection() == null) {
            prestamo.setTransaccionCollection(new ArrayList<Transaccion>());
        }
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamo persistentPrestamo = em.find(Prestamo.class, prestamo.getCodprestamo());
            Administrativo idadministrativoOld = persistentPrestamo.getIdadministrativo();
            Administrativo idadministrativoNew = prestamo.getIdadministrativo();
            Persona idpersonaOld = persistentPrestamo.getIdpersona();
            Persona idpersonaNew = prestamo.getIdpersona();
            Salon idsalonOld = persistentPrestamo.getIdsalon();
            Salon idsalonNew = prestamo.getIdsalon();
            Collection<Transaccion> transaccionCollectionOld = persistentPrestamo.getTransaccionCollection();
            Collection<Transaccion> transaccionCollectionNew = prestamo.getTransaccionCollection();
            List<String> illegalOrphanMessages = null;
            for (Transaccion transaccionCollectionOldTransaccion : transaccionCollectionOld) {
                if (!transaccionCollectionNew.contains(transaccionCollectionOldTransaccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Transaccion " + transaccionCollectionOldTransaccion + " since its codprestamo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idadministrativoNew != null) {
                idadministrativoNew = em.getReference(idadministrativoNew.getClass(), idadministrativoNew.getIdadministrativo());
                prestamo.setIdadministrativo(idadministrativoNew);
            }
            if (idpersonaNew != null) {
                idpersonaNew = em.getReference(idpersonaNew.getClass(), idpersonaNew.getIdpersona());
                prestamo.setIdpersona(idpersonaNew);
            }
            if (idsalonNew != null) {
                idsalonNew = em.getReference(idsalonNew.getClass(), idsalonNew.getIdsalon());
                prestamo.setIdsalon(idsalonNew);
            }
            Collection<Transaccion> attachedTransaccionCollectionNew = new ArrayList<Transaccion>();
            for (Transaccion transaccionCollectionNewTransaccionToAttach : transaccionCollectionNew) {
                transaccionCollectionNewTransaccionToAttach = em.getReference(transaccionCollectionNewTransaccionToAttach.getClass(), transaccionCollectionNewTransaccionToAttach.getIdtransaccion());
                attachedTransaccionCollectionNew.add(transaccionCollectionNewTransaccionToAttach);
            }
            transaccionCollectionNew = attachedTransaccionCollectionNew;
            prestamo.setTransaccionCollection(transaccionCollectionNew);
            prestamo = em.merge(prestamo);
            if (idadministrativoOld != null && !idadministrativoOld.equals(idadministrativoNew)) {
                idadministrativoOld.getPrestamoCollection().remove(prestamo);
                idadministrativoOld = em.merge(idadministrativoOld);
            }
            if (idadministrativoNew != null && !idadministrativoNew.equals(idadministrativoOld)) {
                idadministrativoNew.getPrestamoCollection().add(prestamo);
                idadministrativoNew = em.merge(idadministrativoNew);
            }
            if (idpersonaOld != null && !idpersonaOld.equals(idpersonaNew)) {
                idpersonaOld.getPrestamoCollection().remove(prestamo);
                idpersonaOld = em.merge(idpersonaOld);
            }
            if (idpersonaNew != null && !idpersonaNew.equals(idpersonaOld)) {
                idpersonaNew.getPrestamoCollection().add(prestamo);
                idpersonaNew = em.merge(idpersonaNew);
            }
            if (idsalonOld != null && !idsalonOld.equals(idsalonNew)) {
                idsalonOld.getPrestamoCollection().remove(prestamo);
                idsalonOld = em.merge(idsalonOld);
            }
            if (idsalonNew != null && !idsalonNew.equals(idsalonOld)) {
                idsalonNew.getPrestamoCollection().add(prestamo);
                idsalonNew = em.merge(idsalonNew);
            }
            for (Transaccion transaccionCollectionNewTransaccion : transaccionCollectionNew) {
                if (!transaccionCollectionOld.contains(transaccionCollectionNewTransaccion)) {
                    Prestamo oldCodprestamoOfTransaccionCollectionNewTransaccion = transaccionCollectionNewTransaccion.getCodprestamo();
                    transaccionCollectionNewTransaccion.setCodprestamo(prestamo);
                    transaccionCollectionNewTransaccion = em.merge(transaccionCollectionNewTransaccion);
                    if (oldCodprestamoOfTransaccionCollectionNewTransaccion != null && !oldCodprestamoOfTransaccionCollectionNewTransaccion.equals(prestamo)) {
                        oldCodprestamoOfTransaccionCollectionNewTransaccion.getTransaccionCollection().remove(transaccionCollectionNewTransaccion);
                        oldCodprestamoOfTransaccionCollectionNewTransaccion = em.merge(oldCodprestamoOfTransaccionCollectionNewTransaccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = prestamo.getCodprestamo();
                if (findPrestamo(id) == null) {
                    throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.");
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        startOperation();
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prestamo prestamo;
            try {
                prestamo = em.getReference(Prestamo.class, id);
                prestamo.getCodprestamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prestamo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Transaccion> transaccionCollectionOrphanCheck = prestamo.getTransaccionCollection();
            for (Transaccion transaccionCollectionOrphanCheckTransaccion : transaccionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prestamo (" + prestamo + ") cannot be destroyed since the Transaccion " + transaccionCollectionOrphanCheckTransaccion + " in its transaccionCollection field has a non-nullable codprestamo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Administrativo idadministrativo = prestamo.getIdadministrativo();
            if (idadministrativo != null) {
                idadministrativo.getPrestamoCollection().remove(prestamo);
                idadministrativo = em.merge(idadministrativo);
            }
            Persona idpersona = prestamo.getIdpersona();
            if (idpersona != null) {
                idpersona.getPrestamoCollection().remove(prestamo);
                idpersona = em.merge(idpersona);
            }
            Salon idsalon = prestamo.getIdsalon();
            if (idsalon != null) {
                idsalon.getPrestamoCollection().remove(prestamo);
                idsalon = em.merge(idsalon);
            }
            em.remove(prestamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
                emf.close();
            }
        }
    }

    public List<Prestamo> findPrestamoEntities() {
        return findPrestamoEntities(true, -1, -1);
    }

    public List<Prestamo> findPrestamoEntities(int maxResults, int firstResult) {
        return findPrestamoEntities(false, maxResults, firstResult);
    }

    private List<Prestamo> findPrestamoEntities(boolean all, int maxResults, int firstResult) {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prestamo.class));
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

    public Prestamo findPrestamo(String id) {
        startOperation();
        try {
            return em.find(Prestamo.class, id);
        } finally {
            em.close();
            emf.close();
        }
    }

    public int getPrestamoCount() {
        startOperation();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prestamo> rt = cq.from(Prestamo.class);
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
            Logger.getLogger(PrestamoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
}
