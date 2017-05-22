/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db.controller;

import com.kaab.proyecto.db.TipoComida;
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
public class TipoComidaJpaController implements Serializable {
    /**
     * @param pEmf Entity Manager Factory
     */
    public TipoComidaJpaController(final EntityManagerFactory pEmf) {
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
     * @param tipoComida tipoComida a crear.
     */
    public final void create(final TipoComida tipoComida) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipoComida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param tipoComida tipoComida a editar
     * @throws NonexistentEntityException excepcion
     * @throws Exception excepcion
     */
    public final void edit(TipoComida tipoComida)
            throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipoComida = em.merge(tipoComida);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tipoComida.getIdTipoComida();
                if (findTipoComida(id) == null) {
                    throw new NonexistentEntityException(
                        "The tipoComida with id " + id + " no longer exists.");
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
     * @param id id del Tipo de Comida
     * @throws NonexistentEntityException excepcion
     */
    public final void destroy(final Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoComida tipoComida;
            try {
                tipoComida = em.getReference(TipoComida.class, id);
                tipoComida.getIdTipoComida();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoComida with id "
                    + id + " no longer exists.", enfe);
            }
            em.remove(tipoComida);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @return TipoComidaEntities.
     */
    public final List<TipoComida> findTipoComidaEntities() {
        return findTipoComidaEntities(true, -1, -1);
    }
    /**
     * @param maxResults resultados
     * @param firstResult resultado
     * @return TipoComidaEntities
     */
    public final List<TipoComida> findTipoComidaEntities(final int maxResults,
        final int firstResult) {
        return findTipoComidaEntities(false, maxResults, firstResult);
    }
    /**
     * @param all all
     * @param maxResults resultados
     * @param firstResult resultado
     * @return ResultList
     */
    private List<TipoComida> findTipoComidaEntities(final boolean all,
            final int maxResults, final int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoComida.class));
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
     * @param id id del tipo de comida
     * @return  em
     */
    public final TipoComida findTipoComida(final Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoComida.class, id);
        } finally {
            em.close();
        }
    }
    /**
     * @return numero
     */
    public final int getTipoComidaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoComida> rt = cq.from(TipoComida.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
