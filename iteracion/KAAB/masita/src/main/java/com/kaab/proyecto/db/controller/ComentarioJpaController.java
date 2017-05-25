/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kaab.proyecto.db.controller;

import com.kaab.proyecto.db.Comentario;
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
public class ComentarioJpaController implements Serializable {
    /**
     * Interfaz para hacer consultas en la base de datos.
     * @param pEmf EntityManagerFactory.
     */
    public ComentarioJpaController(final EntityManagerFactory pEmf) {
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
     * @param comentario comentario que se creará.
     */
    public final void create(final Comentario comentario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @param comentario el comentario a editar.
     * @throws NonexistentEntityException excepcion.
     * @throws Exception excepcion.
     */
    public final void edit(Comentario comentario) throws
            NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            comentario = em.merge(comentario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = comentario.getIdComentario();
                if (findComentario(id) == null) {
                    throw new NonexistentEntityException(
                        "The comentario with id " + id + " no longer exists.");
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
     * @param id id del comentario
     * @throws NonexistentEntityException excepcion
     */
    public final void destroy(final Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentario comentario;
            try {
                comentario = em.getReference(Comentario.class, id);
                comentario.getIdComentario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentario with id "
                        + id + " no longer exists.", enfe);
            }
            em.remove(comentario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * @return ComentarioEntities
     */
    public final List<Comentario> findComentarioEntities() {
        return findComentarioEntities(true, -1, -1);
    }
    /**
     * @param maxResults numero.
     * @param firstResult resultado.
     * @return ComentarioEntities.
     */
    public final List<Comentario> findComentarioEntities(final int maxResults,
            final int firstResult) {
        return findComentarioEntities(false, maxResults, firstResult);
    }
    /**
     * @param all all.
     * @param maxResults numero.
     * @param firstResult resultado.
     * @return ResultList
     */
    private List<Comentario> findComentarioEntities(final boolean all,
            final int maxResults, final int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentario.class));
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
     * @param id id del comentario
     * @return comentario
     */
    public final Comentario findComentario(final Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentario.class, id);
        } finally {
            em.close();
        }
    }
    /**
     * getComentarioCount.
     * @return numero
     */
    public final int getComentarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentario> rt = cq.from(Comentario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    /**
     * Regresa el promedio de las calificaciones de un puesto.
     * @param idPuesto el id del puesto a calcular la calificación
     * @return el promedio de las calificaciones de un puesto
     */
    public Double getPromedio(Integer idPuesto) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select AVG(c.calificacion) from Comentario c where c.calificacion IS NOT NULL and c.idPuesto.idPuesto = " + idPuesto);
            Double result = (Double) q.getSingleResult();
            return result;
        } finally {
            em.close();
        }
    }
}