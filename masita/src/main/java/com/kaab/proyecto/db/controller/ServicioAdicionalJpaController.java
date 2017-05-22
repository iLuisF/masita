/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db.controller;

import com.kaab.proyecto.db.ServicioAdicional;
import com.kaab.proyecto.db.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author esperanzahigareda
 */
public class ServicioAdicionalJpaController implements Serializable {
    /**
     * @param pEmf EntityManagerFactory
     */
    public ServicioAdicionalJpaController(final EntityManagerFactory pEmf) {
        this.emf = pEmf;
    }
    /**
     * Interfaz para hacer consultas en la base de datos.
     */
    private EntityManagerFactory emf = null;
    /**
     * @return emf
     */
    public final EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    /**
     * @param servicioAdicional servicioAdicional a crear.
     */
    public final void create(final ServicioAdicional servicioAdicional) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(servicioAdicional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param servicioAdicional Servicio adicional a editar.
     * @throws NonexistentEntityException excepcion
     * @throws Exception excepcion
     */
    public final void edit(ServicioAdicional servicioAdicional)
            throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            servicioAdicional = em.merge(servicioAdicional);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = servicioAdicional.getIdServicio();
                if (findServicioAdicional(id) == null) {
                    throw new NonexistentEntityException(
                        "The servicioAdicional with id " + id
                                + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param id el id del ServicioAdicional
     * @throws NonexistentEntityException excepcion
     */
    public final void destroy(final Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioAdicional servicioAdicional;
            try {
                servicioAdicional
                        = em.getReference(ServicioAdicional.class, id);
                servicioAdicional.getIdServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                    "The servicioAdicional with id " + id
                        + " no longer exists.", enfe);
            }
            em.remove(servicioAdicional);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @return ServicioAdicionalEntities
     */
    public final List<ServicioAdicional> findServicioAdicionalEntities() {
        return findServicioAdicionalEntities(true, -1, -1);
    }
    /**
     * @param maxResults resultados
     * @param firstResult resultado
     * @return  ServicioAdicionalEntities
     */
    public final List<ServicioAdicional> findServicioAdicionalEntities(
            final int maxResults, final int firstResult) {
        return findServicioAdicionalEntities(false, maxResults, firstResult);
    }
    /**
     * @param all all
     * @param maxResults resultados
     * @param firstResult resultado
     * @return ResultList
     */
    private List<ServicioAdicional> findServicioAdicionalEntities(
            final boolean all, final int maxResults, final int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioAdicional.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    /**
     * @param id del servicioAdicional
     * @return em
     */
    public final ServicioAdicional findServicioAdicional(final Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioAdicional.class, id);
        } finally {
            em.close();
        }
    }
    /**
     * @return numero
     */
    public final int getServicioAdicionalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioAdicional> rt = cq.from(ServicioAdicional.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
