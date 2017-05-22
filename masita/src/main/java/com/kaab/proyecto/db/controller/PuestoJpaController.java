/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db.controller;

import com.kaab.proyecto.db.Puesto;
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
public class PuestoJpaController implements Serializable {
    /**
     * @param pEmf EntityManagerFactory
     */
    public PuestoJpaController(final EntityManagerFactory pEmf) {
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
     * @param puesto puesto a crear
     */
    public final void create(final Puesto puesto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(puesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param puesto el puesto a editar.
     * @throws NonexistentEntityException excepcion
     * @throws Exception excepcion
     */
    public final void edit(Puesto puesto) throws NonexistentEntityException,
        Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            puesto = em.merge(puesto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = puesto.getIdPuesto();
                if (findPuesto(id) == null) {
                    throw new NonexistentEntityException("The puesto with id "
                        + id + " no longer exists.");
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
     * @param id id del puesto
     * @throws NonexistentEntityException excepcion
     */
    public final void destroy(final Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Puesto puesto;
            try {
                puesto = em.getReference(Puesto.class, id);
                puesto.getIdPuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puesto with id "
                    + id + " no longer exists.", enfe);
            }
            em.remove(puesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @return PuestoEntities
     */
    public final List<Puesto> findPuestoEntities() {
        return findPuestoEntities(true, -1, -1);
    }
    /**
     * @param maxResults resultados
     * @param firstResult resultado
     * @return PuestoEntities
     */
    public final List<Puesto> findPuestoEntities(final int maxResults,
        final int firstResult) {
        return findPuestoEntities(false, maxResults, firstResult);
    }
    /**
     * @param all all
     * @param maxResults resultados
     * @param firstResult resultado
     * @return ResultList
     */
    private List<Puesto> findPuestoEntities(final boolean all,
            final int maxResults, final int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Puesto.class));
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
     * @param id id del puesto.
     * @return em.
     */
    public final Puesto findPuesto(final Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Puesto.class, id);
        } finally {
            em.close();
        }
    }
    /**
     * @return numero
     */
    public final int getPuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Puesto> rt = cq.from(Puesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
